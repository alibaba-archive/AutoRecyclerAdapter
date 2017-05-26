
AutoRecyclerAdapter
===============

>I'm writing a lot of repetition RecyclerView.Adapter，I feel most of the time write repeat **if and else**，like getItemViewType，onCreateViewHolder，onBindViewHolder and setSpanSizeLookup getSpanSize，exist a lot of **if and else**

>Write repeat **if and else** become nightmare...

>One day，I decided RecyclerView.Adapter use all **if and else** wipe out，let RecyclerView.Adapter Automated configuration.



## [中文文档](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/README_CH.md)

* Use ViewHolder.class and reflect dynamic create ViewHolder
* Use ViewHolder.class.hashCode()  as RecyclerVew.Adapter ViewType
* Use package class to ViewType，spanSize build connection，Is no longer create field or extend class
* Use AutoHolder，dynamic set model
* Create ViewHolder can set parameter，stand by key and value
* Stand by GridLayoutManager setSpanSizeLookup，create complex arrangement

---

**AutoRecyclerAdapter let Recycler. Adapter all methods Automated configuration.  developer need outside config ViewHolder and Data. No need custom Recycler.Adapter. Stand by create complex arrangement and fast create like shopping app home page layout**

### Purpose：help developer no custom Recycler.Adapter


Screenshots
------

![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/autos.gif)
![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/tb.png)
![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/jd.png)






Usage
-----

To one exist **seven** different ViewHolder page as example，like shopping app home page layout

Seven different List set up to seven different ViewHolder

-----

![](https://github.com/ruzhan123/AutoRecyclerAdapter/raw/master/gif/auto.png)



**1, Set up seven different ViewHolder，use Holder.class.hashCode() as ViewType**

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

**2, Network reuqest seven different list**

```java

	List<ZhaoBean> zhaoList = ModelHelper.getZhaoList(1);
	List<QianBean> qianList = ModelHelper.getQianList(10);
	List<SunBean> sunList = ModelHelper.getSunList(1);
	List<LiBean> liList = ModelHelper.getLiList(4);
	List<ZhouBean> zhouList = ModelHelper.getZhouList(10);
	List<WuBean> wuList = ModelHelper.getWuList(1);
	List<ZhengBean> zhengList = ModelHelper.getZhengList(30);
```

**3,  Set up seven different List to ViewHolder，use Holder.class.hashCode() as ViewType**

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

**1, if only use one List and one ViewHolder**

```java

	autoRecyclerAdapter = new AutoRecyclerAdapter();
	.setHolder(AutoTypeAHolder.class, R.layout.item_type_a);

	autoRecyclerAdapter.setDataList(AutoTypeAHolder.class, qianList)
						.notifyDataSetChanged();
```

**2, if use two List and two ViewHolder**

```java

	autoRecyclerAdapter = new AutoRecyclerAdapter();
	.setHolder(AutoTypeAHolder.class, R.layout.item_type_a)
	.setHolder(AutoTypeBHolder.class, R.layout.item_type_b);

	autoRecyclerAdapter.setDataList(AutoTypeAHolder.class, qianList)
						.setDataList(AutoTypeBHolder.class, sunList)
						.notifyDataSetChanged();
```

**3, if use GridLayoutManager setSpanSizeLookup**

```java
	autoRecyclerAdapter = new AutoRecyclerAdapter();
	manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
	@Override public int getSpanSize(int position) {
	return autoRecyclerAdapter.getSpanSize(position);
	}
	});

	autoRecyclerAdapter = new AutoRecyclerAdapter();
	.setHolder(AutoTypeAHolder.class, R.layout.item_type_a)
	.setHolder(AutoTypeBHolder.class, R.layout.item_type_b);

	int spanSizeA = 2;
	int spanSizeB = spanSizeA / 2;

	autoRecyclerAdapter.setDataList(AutoTypeAHolder.class, qianList, spanSizeA)
						.setDataList(AutoTypeBHolder.class, sunList, spanSizeB)
						.notifyDataSetChanged();
```

Expand
------

**1，Stand by to ViewHolder transfer parameter: key and value like map**

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

**2，Stand by use GridLayoutManager setSpanSizeLookup，create complex arrangement**

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

**One core class，four auxiliary class**


| name                                     | description                              |
| :--------------------------------------- | :--------------------------------------- |
| [AutoRecyclerAdapter](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoRecyclerAdapter.java) | Automated configuration Recycler.Adapter core class |
| [AutoHolder](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoHolder.java) | Automated configuration need ViewHolder  |
| [AutoPackage](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoPackage.java) | AutoRecyclerAdapter need model           |
| [AutoHolderPackage](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/AutoHolderPackage.java) | Automated create VewHolder need model    |
| [OnAutoHolderListener](https://github.com/ruzhan123/AutoRecyclerAdapter/blob/master/auto-adapter/src/main/java/zhan/auto_adapter/OnAutoHolderListener.java) | AutoHolder outside build connection example |


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
	        compile 'com.github.teambition:AutoRecyclerAdapter:v1.5'
	}
```

Proguard
------

```java

-keep public class * extends zhan.auto_adapter.AutoHolder {
    public <init>(android.view.View, java.util.Map);
}
```

### Want to learn about details to [Blog](https://ruzhan123.github.io/2017/05/05/2017-05-05-23-AutoRecyclerAdapter/)

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
