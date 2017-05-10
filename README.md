
AutoRecyclerAdapter
===============

>我在写大量重复RecyclerView.Adapter的时候，发现我的大部分时间都花在写重复，机械式的if与else，不论是getItemViewType，onCreateViewHolder，onBindViewHolder还是setSpanSizeLookup的getSpanSize，都充斥着大量的if与else

>写重复的代码一直困扰着我

>一天，我决定把关于RecyclerView.Adapter使用到的if与else都干掉，达到自动化配置的效果



AutoRecyclerAdapter是一个接近万能的Adapter，它把Recycler.Adapter里开发者需要手写的方法全部自动化，配置化。开发者只需要在外部配置Holder与model就能使用，不必重新自定义Adapter。复杂的多种类型Holder布局也不例外。能够快速的实现像淘宝，京东等首页复杂，多类型的布局。

### 设计AutoRecyclerAdapter的目的：化繁为简，化整为零，帮助开发者不再实现Recycler.Adapter


Screenshots
------

![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/autos.gif)
![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/tb.png)
![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/jd.png)



## 自动化配置Recycler.Adapter

* 使用字节码＋反射动态创建ViewHolder
* 使用ViewHolder.class.hashCode() 作为ViewType
* model与ViewType，spanSize建立联系，不再是model添加新字段或者继承的方式
* ViewHolder泛型定义，动态获取需要的数据模型（model）自动类型转换
* ViewHolder创建可设置额外参数，支持与Activity，fragment等建立通信


Usage
-----

以一个拥有**7个**不同的ViewHolder的界面为例，类似很多商城首页的布局

模拟服务器传来7种不同的List集合，需要设计7种不同的ViewHolder

-----

![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/auto.png)



**1, 设置7种ViewHolder，ViewHolder支持设置额外参数**

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

**2, 设置网络请求得到的7种不同List**

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

Other
------

**自定义的ViewHolder需要继承AutoHolder，并填写需要的model作为泛型**

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

**自动化创建ViewHolder**

```java

	@Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	AutoHolderPackage holderPackage = holderPackageMap.get(viewType);
	
	int holderLayoutRes = holderPackage.getHolderLayoutRes();
	View itemView =
	LayoutInflater.from(parent.getContext()).inflate(holderLayoutRes, parent, false);
	Class holderClass = holderPackage.getHolderClass();
	
	Constructor constructor = holderClass.getConstructor(View.class);
	AutoHolder autoHolder = (AutoHolder) constructor.newInstance(itemView);
	
	return autoHolder;
```

**自动化bind ViewHolder**

```java

	@Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof AutoHolder) {
			AutoHolder autoHolder = (AutoHolder) holder;
			Object bean = packageList.get(position).getAutoPackage();
			autoHolder.bind(position, bean);
		}
	}
```

**ViewType与SpanSize**

```java

	@Override public int getItemViewType(int position) {
		return packageList.get(position).getType();
	}
	
	
	public int getSpanSize(int position) {
		return packageList.get(position).getSpanSize();
	}
	
	@Override public int getItemCount() {
		return packageList.size();
	}
```

Class
------

* [AutoRecyclerAdapter](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoRecyclerAdapter.java)
* [AutoHolder](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoHolder.java)
* [AutoPackage](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoPackage.java)
* [AutoHolderPackage](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoHolderPackage.java)


Gradle
------


[![](https://jitpack.io/v/ruzhan123/AutoRecyclerAdapter.svg)](https://jitpack.io/#ruzhan123/AutoRecyclerAdapter)

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
	        compile 'com.github.ruzhan123:AutoRecyclerAdapter:v1.4'
	}
```

Proguard-rule:

```java

-keep public class * extends zhan.auto_adapter.AutoHolder {
    public <init>(android.view.View, java.lang.Object, java.lang.Object, java.lang.Object);
}
```

### 想了解具体设计细节，请移步[wiki](https://github.com/ruzhan123/AutoRecyclerAdapter/wiki/AutoRecyclerAdapter)

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
