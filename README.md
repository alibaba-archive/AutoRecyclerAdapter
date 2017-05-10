
AutoRecyclerAdapter
===============

>我在写大量重复RecyclerView.Adapter的时候，发现我的大部分时间都花在写重复，机械式的if与else，不论是getItemViewType，onCreateViewHolder，onBindViewHolder还是setSpanSizeLookup的getSpanSize，都充斥着大量的if与else

>写重复的代码一直困扰着我

>一天，我决定把关于RecyclerView.Adapter使用到的if与else都干掉，达到自动化配置的效果


## AutoRecyclerAdapter

* 使用字节码＋反射动态创建ViewHolder
* 使用ViewHolder.class.hashCode() 作为ViewType
* model与ViewType，spanSize建立联系，不再是model添加新字段或者继承的方式
* ViewHolder泛型定义，动态获取需要的数据模型（model）自动类型转换
* ViewHolder创建可设置额外参数，支持与Activity，fragment等建立通信

---

**AutoRecyclerAdapter把Recycler.Adapter里开发者需要手写的方法全部自动化，配置化。开发者只需要在外部配置Holder与model就能使用，不必重新自定义Adapter。复杂的多种类型Holder布局也不例外。能够快速的实现像淘宝，京东等首页复杂，多类型的布局**

### 设计的目的：化繁为简，帮助开发者不再实现Recycler.Adapter


Screenshots
------

![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/autos.gif)
![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/tb.png)
![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/jd.png)






Usage
-----

以一个拥有**7个**不同的ViewHolder的界面为例，类似很多商城首页的布局

模拟服务器传来7种不同的List集合，需要设计7种不同的ViewHolder

-----

![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/auto.png)



**1, 设置7种ViewHolder，使用Holder.class.hashCode()作为Type**

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

**2, 网络请求，获取数据**

```java

	List<ZhaoBean> zhaoList = ModelHelper.getZhaoList(1);
	List<QianBean> qianList = ModelHelper.getQianList(10);
	List<SunBean> sunList = ModelHelper.getSunList(1);
	List<LiBean> liList = ModelHelper.getLiList(4);
	List<ZhouBean> zhouList = ModelHelper.getZhouList(10);
	List<WuBean> wuList = ModelHelper.getWuList(1);
	List<ZhengBean> zhengList = ModelHelper.getZhengList(30);
```

**3,  为7种不同的ViewHolder设置数据，使用Holder.class.hashCode()作为Type**

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

Expand
------

**1，支持向ViewHolder传递参数，对象或者Listener**

```java

	public <H extends AutoHolder> AutoRecyclerAdapter setHolderToData(Class<H> holderClass,
	    int layoutRes, Map<String, Object> dataMap) {
	    return setHolderToData(holderClass.hashCode(), holderClass, layoutRes, dataMap);
	}

	public <H extends AutoHolder> AutoRecyclerAdapter setHolderToListener(Class<H> holderClass,
	int layoutRes, String key, Object value, OnAutoHolderListener listener) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put(key, value);
		dataMap.put(AutoHolder.LISTENER, listener);
		return setHolderToData(holderClass.hashCode(), holderClass, layoutRes, dataMap);
	}
```

**2，支持使用GridLayoutManager setSpanSizeLookup，创建复杂的排列方式**

```java

	public <H extends AutoHolder, M> AutoRecyclerAdapter setDataObjectSpan(Class<H> holderClass,
	    M bean, int spanSize) {
	    return setDataObject(holderClass.hashCode(), bean, spanSize);
	}

	public <H extends AutoHolder, M> AutoRecyclerAdapter setDataListSpan(Class<H> holderClass,
	List<M> list, int spanSize) {
		return setDataList(holderClass.hashCode(), list, spanSize);
	}
```


Class
------

**一个核心类，4个辅助类**


| name |  description |
|:----|:----|
| [AutoRecyclerAdapter](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoRecyclerAdapter.java) | 自动化Recycler.Adapter核心类 |
| [AutoHolder](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoHolder.java) | 自动化需要集成的ViewHolder |
| [AutoPackage](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoPackage.java) | AutoRecyclerAdapter需要的model |
| [AutoHolderPackage](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoHolderPackage.java) | 自动化创建VewHolder所需要的model |
| [OnAutoHolderListener](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/OnAutoHolderListener.java)  | AutoHolder与外部通讯的Listener范例 |


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
	        compile 'com.github.ruzhan123:AutoRecyclerAdapter:v1.5'
	}
```

Proguard
------

```java

-keep public class * extends zhan.auto_adapter.AutoHolder {
    public <init>(android.view.View, java.util.Map);
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
