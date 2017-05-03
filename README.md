
AutoRecyclerAdapter
===============

>我在写大量重复RecyclerView.Adapter的时候，发现我的大部分时间都花在写重复，机械式的if与else，不论是getItemViewType，onCreateViewHolder，onBindViewHolder还是setSpanSizeLookup的getSpanSize，都充斥着大量的if与else。

>写重复的代码一直困扰着我。。。

>一天，我决定把关于RecyclerView.Adapter使用到的if与else都干掉，达到自动化配置的效果



## 自动化配置Recycler.Adapter

* 使用字节码＋反射动态创建ViewHolder
* 使用ViewHolder.class.hashCode() 作为ViewType
* model与ViewType，spanSize建立联系，不再是model添加新字段或者继承这些入侵手段
* ViewHolder泛型定义，动态获取需要的数据模型（model）来设置布局
* ViewHolder创建可设置额外参数，与Activity，fragment等建立通信



**这个库到底行不行？出来走两步就知道了**

Usage
-----

现在，以一个拥有**7个**不同的ViewHolder的界面为例，很多商城首页的布局样式：

服务器传来7种不同的List集合，需要设计7种不同的ViewHolder，看下面

-----

![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/auto.png)



1, 设置7种ViewHolder，ViewHolder支持设置额外参数

```java

  autoRecyclerAdapter = new AutoRecyclerAdapter();
  manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
  @Override public int getSpanSize(int position) {
  return autoRecyclerAdapter.getSpanSize(position);
  }
  });


  autoRecyclerAdapter.setHolder(AutoBannerHolder.class, R.layout.item_banner, this)
  .setHolder(AutoTypeAHolder.class, R.layout.item_type_a)
  .setHolder(AutoTypeBHolder.class, R.layout.item_type_b)
  .setHolder(AutoTypeCHolder.class, R.layout.item_type_c)
  .setHolder(AutoTypeDHolder.class, R.layout.item_type_d)
  .setHolder(AutoTypeEHolder.class, R.layout.item_type_e)
  .setHolder(AutoTypeFHolder.class, R.layout.item_type_f);
```

2, 先网络请求数据，成功回来先

3, 设置网络请求得到的7种不同List

```java

 autoRecyclerAdapter.setDataListSpan(AutoBannerHolder.class, zhaoList, SPAN_SIZE)
  .setDataListSpan(AutoTypeAHolder.class, qianList, SPAN_SIZE / 5)
  .setDataListSpan(AutoTypeBHolder.class, sunList, SPAN_SIZE)
  .setDataListSpan(AutoTypeCHolder.class, liList, SPAN_SIZE / 2)
  .setDataListSpan(AutoTypeDHolder.class, zhouList, SPAN_SIZE / 5)
  .setDataListSpan(AutoTypeEHolder.class, wuList, SPAN_SIZE)
  .setDataListSpan(AutoTypeFHolder.class, zhengList, SPAN_SIZE / 2)
  .notifyDataSetChanged();
```

PS：自定义的ViewHolder需要继承AutoHolder，并填写需要的model作为泛型:

```java

  public class AutoBannerHolder extends AutoHolder<ZhaoBean> implements View.OnClickListener {

  private ImageView iv;

  public AutoBannerHolder(View itemView, Object obj1, Object obj2, Object obj3) {
  super(itemView, obj1, obj2, obj3);
  iv = (ImageView) itemView.findViewById(R.id.banner_iv);
  itemView.setOnClickListener(this);
  }

  @Override public void bind(int position, ZhaoBean bean) {
  iv.setImageResource(bean.getIcon());
  }

  @Override public void onClick(View v) {
  if(obj1 instanceof SendListener) {
  ((SendListener)obj1).send();
  }
  }
  }
```

### 想知道从零创建这个自动化配置的Adapter过程，请移步[wiki](https://github.com/ruzhan123/AutoRecyclerAdapter/wiki/AutoRecyclerAdapter)

**具体细节请查看工程里的例子**

[![](https://jitpack.io/v/ruzhan123/AutoRecyclerAdapter.svg)](https://jitpack.io/#ruzhan123/AutoRecyclerAdapter)

Gradle
------

Add it in your root build.gradle at the end of repositories:


```java

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency:


```java

	dependencies {
	        compile 'com.github.ruzhan123:AutoRecyclerAdapter:v1.0'
	}
```


Developed by
-------

 ruzhan - <a href='javascript:'>dev19921116@gmail.com</a>


License
-------

    Copyright 2017 ruzhan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
