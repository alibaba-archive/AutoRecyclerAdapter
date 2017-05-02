#AutoRecyclerAdapter

>我在写大量重复RecyclerView.Adapter的时候，发现我的大部分时间都花在写重复，机械式的if与else，不论是getItemViewType，onCreateViewHolder，onBindViewHolder还是setSpanSizeLookup的getSpanSize，都充斥着大量的if与else。

>写重复的代码一直困扰着我。。。

>一天，我决定把关于RecyclerView.Adapter使用到的if与else都干掉，达到自动化配置的效果


## 如何让 RecyclerView.Adapter 自动化配置？


**1. 首先，RecyclerView.Adapter有哪些方法需要开发者去自己实现？**

* getItemViewType方法，为Adapter区分多种Holder类型的标记。
* onCreateViewHolder方法，为Adapter创建多种Holder对象。创建多种类型依赖getItemViewType方法。
* onBindViewHolder方法，为Adapter的多种Holder对象设置数据。
* getItemCount方法，为Adapter设置条目个数。

**一种标准的RecyclerView.Adapter作为范例:**

```java

		public class StandardMultiAdapter extends RecyclerView.Adapter {
		
		public static final int SPAN_SIZE = 10;
		
		private static final int TYPE_ZHAO = 1000;
		private static final int TYPE_QIAN = 1001;
		private static final int TYPE_SUN = 1002;
		private static final int TYPE_LI = 1003;
		private static final int TYPE_ZHOU = 1004;
		private static final int TYPE_WU = 1005;
		private static final int TYPE_ZHENG = 1006;
		
		private List<Object> data = new ArrayList<>();
```

getItemViewType:

```java

		@Override public int getItemViewType(int position) {
		Object object = data.get(position);
		if (object instanceof ZhaoBean) {
		  return TYPE_ZHAO;
		} else if (object instanceof QianBean) {
		  return TYPE_QIAN;
		} else if (object instanceof SunBean) {
		  return TYPE_SUN;
		} else if (object instanceof LiBean) {
		  return TYPE_LI;
		} else if (object instanceof ZhouBean) {
		  return TYPE_ZHOU;
		} else if (object instanceof WuBean) {
		  return TYPE_WU;
		} else if (object instanceof ZhengBean) {
		  return TYPE_ZHENG;
		}
		return TYPE_ZHENG;
		}
```

onCreateViewHolder:

```java

		@Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == TYPE_ZHAO) {
		  return new BannerHolder(
		      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false));
		} else if (viewType == TYPE_QIAN) {
		  return new TypeAHolder(
		      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_a, parent, false));
		} else if (viewType == TYPE_SUN) {
		  return new TypeBHolder(
		      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_b, parent, false));
		} else if (viewType == TYPE_LI) {
		  return new TypeCHolder(
		      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_c, parent, false));
		} else if (viewType == TYPE_ZHOU) {
		  return new TypeDHolder(
		      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_d, parent, false));
		} else if (viewType == TYPE_WU) {
		  return new TypeEHolder(
		      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_e, parent, false));
		} else if (viewType == TYPE_ZHENG) {
		  return new TypeFHolder(
		      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_f, parent, false));
		}
		return new TypeFHolder(
		    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_f, parent, false));
		}
```

onBindViewHolder:

```java

		@Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		int viewType = getItemViewType(position);
		
		if (viewType == TYPE_ZHAO) {
		  ((BannerHolder) holder).bind(position, (ZhaoBean) data.get(position));
		} else if (viewType == TYPE_QIAN) {
		  ((TypeAHolder) holder).bind(position, (QianBean) data.get(position));
		} else if (viewType == TYPE_SUN) {
		  ((TypeBHolder) holder).bind(position, (SunBean) data.get(position));
		} else if (viewType == TYPE_LI) {
		  ((TypeCHolder) holder).bind(position, (LiBean) data.get(position));
		} else if (viewType == TYPE_ZHOU) {
		  ((TypeDHolder) holder).bind(position, (ZhouBean) data.get(position));
		} else if (viewType == TYPE_WU) {
		  ((TypeEHolder) holder).bind(position, (WuBean) data.get(position));
		} else if (viewType == TYPE_ZHENG) {
		  ((TypeFHolder) holder).bind(position, (ZhengBean) data.get(position));
		}
		}
```


getItemCount:

```java

		@Override public int getItemCount() {
		return data == null ? 0 : data.size();
		}
```


setSpanSizeLookup的getSpanSize，为了方便我也写到Adapter里了:

```java

		public int getSpanSize(int position) {
		int viewType = getItemViewType(position);
		
		if (viewType == TYPE_ZHAO) {
		  return SPAN_SIZE;
		} else if (viewType == TYPE_QIAN) {
		  return SPAN_SIZE / 5;
		} else if (viewType == TYPE_SUN) {
		  return SPAN_SIZE;
		} else if (viewType == TYPE_LI) {
		  return SPAN_SIZE / 2;
		} else if (viewType == TYPE_ZHOU) {
		  return SPAN_SIZE / 5;
		} else if (viewType == TYPE_WU) {
		  return SPAN_SIZE;
		} else if (viewType == TYPE_ZHENG) {
		  return SPAN_SIZE / 2;
		}
		return SPAN_SIZE;
		}
```

**PS：可能有上面写法的一些疑问：**

1.为什么Adapter使用的List泛型为Object？


>为了让Adapter代码达到简洁，这里使用了万物皆对象的形式，position与model一一对应，需要时在类型转换。


2.Adapter使用了List泛型为Object，会出现类型转换异常吗？


>只要add的顺序正确，类型转换时根据position进行的，应该都不会出现错误。**数据存储顺序决定视图呈现顺序。**


3.在getItemViewType中使用instanceof进行获取viewType，若出现相同的model而viewType不同呢？

>是的，上面代码是有这个问题。解决的话只能在model里添加区分viewType的字段，**添加新字段作为区分类型的形式。**

4.为什么要在Adapter中实现getSpanSize这样的方法？

>setSpanSizeLookup的getSpanSize本身是对position对应的Holder进行类似权重的设置，Adapter是控制Holder的地方，写在Adapter很符合逻辑。



如果对上面较为标准的RecyclerView.Adapter写法不存在疑问了，接下来我们来**尝试干掉一些if与else**


**2. 干掉一些RecyclerView.Adapter里的if与else**


* getItemViewType(int position)
* getSpanSize(int position)

为什么选择这两个方法？因为它们代码结构比较简单


**怎么干掉？**

看到上面两个方法里给的现成的参数了吗？对，就是position。


Adapter里的List泛型Object，保证了position就对应着一个model，这里让逻辑清晰了起来。


现在，是否可以在model里**添加两个字段**：**type与spanSize**


这样我们就可以通过data.get(position).getType()拿到type给getItemViewType，getSpanSize也是同理的。


**2-1 添加两个字段:type与spanSize**

为每一个model都添加两个字段？工作量是不是有点大。。。那用继承的形式怎么样？


MultiType:


```java

		public class MultiType {
		
		private int type;
		private int spanSize;
```


来个model集成MultiType，ZhaoMultiBean:


```java

		public class ZhaoMultiBean extends MultiType {
```


这样model里就有type与spanSize了，外面设置好就行了。


**2-2 添加两个字段:type与spanSize后的RecyclerView.Adapter**


List泛型Object变为MultiType，为了使用type与spanSize:


```java

		public class StandardMultiAdapter extends RecyclerView.Adapter {
		
		private List<MultiType> data = new ArrayList<>();
```


更新后的getItemViewType:


```java

		@Override public int getItemViewType(int position) {
		MultiType multiType = data.get(position);
		return multiType.getType();
		}
```


更新后的getSpanSizee:


```java

		public int getSpanSize(int position) {
		MultiType multiType = data.get(position);
		return multiType.getSpanSize();
		}
```

**当然，外面是需要设置好type与SpanSize的(SpanSize看需要设置，默认可不设置)**

一般Adapter的数据都是集合，为每一个model都要设置type与SpanSize

```java

		ZhaoMultiBean multiBean =
		  new ZhaoMultiBean(bean.getText(), bean.getStr(), bean.getIcon());
		multiBean.setType(type);
		multiBean.setSpanSize(spanSize);
```

酱紫getItemViewType(int position)与getSpanSize(int position)if与else被干掉了




**3. 继续干掉一些RecyclerView.Adapter里的if与else**


* onBindViewHolder(RecyclerView.ViewHolder holder, int position)


为什么选这个，因为我看到它给了position的参数，和getItemViewType给的一样。。。


**怎么干掉？**

Holder是根据不同的ViewType创建出来的，每一个Holder所需要的model都不一样。

这里是可以data.get(position)拿到对应position的MultiType对象，其实也是Holder所需要的model

每一个Holder所需要的model都不一样，这个怎么解决，该怎么自动强转呢？


**我们可以设置一个父类抽象的Holder，在类名上填写泛型，以后每一个子类都集成它，然后就可以解决强转的问题了**


```java

		public abstract class AutoHolder<M> extends RecyclerView.ViewHolder {
		
		
		public AutoHolder(View itemView) {
		super(itemView);
		}
		
		public abstract void bind(int position, M bean);
		}
```

然后在onBindViewHolder里这样做：

```java
		
		@Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if(holder instanceof MultiHolder) {
		  MultiHolder multiHolder = (MultiHolder) holder;
		  multiHolder.bind(position, data.get(position));
		}
		}
```

通过position获取到一个对象MultiType，通过Holder的bind方法传递进去，而Holder里bind参数是填写泛型的，这样就达到自动强转的目的了

酱紫onBindViewHolder的if与else被干掉了


**4. 最后，干掉onCreateViewHolder(ViewGroup parent, int viewType)里的if与else**


onCreateViewHolder方法中创建不同的对象是根据viewType来判断的，显然Holder与viewType存在键值对的关系：key:viewType，value:ViewHolder对象。


如何消灭掉if与else呢，或者说如何在外面配置根据viewType创建不同的ViewHolder呢？


批量创建不同的对象，而且创建对象的个数无法确定。


这里，我打算使用**字节码+反射**的方式解决这个问题。


**4-1. 设计反射创建Holder对象需要的model**

创建一个Holder需要哪些？

* Holder.class：Holder的字节码对象
* itemView：也就是Holder的UI布局

所以，可以这样设计model：

```java
		
		public class AutoHolderPackage<H extends AutoHolder> {
		
		private Class<H> holderClass;
		private int holderLayoutRes;
```

然后在Adapter创建一个Holder与viewType的键值对集合：


```java
		
		protected SparseArray<AutoHolderPackage> holderPackageMap = new SparseArray<>();
```

接下来，字节码+反射动态创建不同的Holder：

```java
		
		@Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		AutoHolderPackage holderPackage = holderPackageMap.get(viewType);
		if (holderPackage == null) {
		  throw new RuntimeException(
		      "not find viewType is: ( " + viewType + " )  holder, viewType error");
		}
		int holderLayoutRes = holderPackage.getHolderLayoutRes();
		View itemView =
		    LayoutInflater.from(parent.getContext()).inflate(holderLayoutRes, parent, false);
		Class holderClass = holderPackage.getHolderClass();
		
		try {
		  Constructor constructor = holderClass.getConstructor(View.class);
		  AutoHolder autoHolder = (AutoHolder) constructor.newInstance(itemView);
		
		  holderList.add(autoHolder);
		  return autoHolder;
		} catch (NoSuchMethodException e) {
		  e.printStackTrace();
		} catch (IllegalAccessException e) {
		  e.printStackTrace();
		} catch (InstantiationException e) {
		  e.printStackTrace();
		} catch (InvocationTargetException e) {
		  e.printStackTrace();
		}
		throw new RuntimeException("( " + holderClass + " )  constructor error");
  }
```

在外面只需要把Holder.class与布局资源设置到AutoHolderPackage与viewType形成键值对的形式就完成了。

**敲黑板了，注意：Holder.class的viewType要和model设置的viewType对应上**

所以，使用Holder.class.hashCode()作为viewType很合适。


**现在，已经消灭了RecyclerView.Adapter里面的if与else了。。。**


新的问题来了：


## 如何简单，优雅的使用？

消灭所有if与else之后，存在的问题:

* 对每一个model都手动设置type与spanSize，写了很多重复繁琐的代码
* Holder通过字节码动态创建，如果Holder需要额外的参数如何传递给它
* 如何简单，优雅的设置Holder.class与Holder的布局资源
* 如何简单，优雅的设置Adapter的List，也是Holder需要的model
* 解决了旧的问题，是否出现了一些新的问题


**5-1. 对每一个model都手动设置type与spanSize，写了很多重复繁琐的代码**

之前是这样的：

```java

		ZhaoMultiBean multiBean =
		  new ZhaoMultiBean(bean.getText(), bean.getStr(), bean.getIcon());
		multiBean.setType(type);
		multiBean.setSpanSize(spanSize);
```

对Adapter的每一个model都设置type与spanSize,又要集成MultiType对象，又要设置。。。

有时候，model的字段不能乱加或者不能乱继承，这样子怎么办？


**现在是这样：**

```java

public class AutoPackage {

  private int type;
  private Object autoPackage;
  private int spanSize;
```

换一种思路，把需要的model与需要的type，spanSize都包在一起，而且是代码自动包，这样解决了对原来的model的入侵。

```java

		private <M> AutoRecyclerAdapter setDataObject(int type, M bean, int spanSize) {
		AutoPackage autoPackage = new AutoPackage(type, bean, spanSize);
		packageList.add(index, autoPackage);
		return this;
		}
```

这样子，Adapter的List也要改变了：

```java

		protected List<AutoPackage> packageList = new ArrayList<>();
```

使用方式：

```java

		autoRecyclerAdapter.setDataListSpan(AutoBannerHolder.class, zhaoList, SPAN_SIZE)
```

不用在对model设置type和其他处理了，保证了model的纯正。


**5-2. Holder通过字节码动态创建，如果Holder需要额外的参数如何传递给它**

目前为父类AutoHolder增加三个参数，全部为Object类型，在Adapter设置Holder字节码时，可传递任意对象，传递到AutoHolder中。

```java

		public abstract class AutoHolder<M> extends RecyclerView.ViewHolder {
		
		protected Object obj1;
		protected Object obj2;
		protected Object obj3;
		
		public AutoHolder(View itemView, Object obj1, Object obj2, Object obj3) {
		super(itemView);
		this.obj1 = obj1;
		this.obj2 = obj2;
		this.obj3 = obj3;
		}
		
		public abstract void bind(int position, M bean);
}
```


```java

		@Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		AutoHolderPackage holderPackage = holderPackageMap.get(viewType);
		if (holderPackage == null) {
		  throw new RuntimeException(
		      "not find viewType is: ( " + viewType + " )  holder, viewType error");
		}
		int holderLayoutRes = holderPackage.getHolderLayoutRes();
		View itemView =
		    LayoutInflater.from(parent.getContext()).inflate(holderLayoutRes, parent, false);
		Class holderClass = holderPackage.getHolderClass();
		
		Object obj1 = holderPackage.getObj1();
		Object obj2 = holderPackage.getObj2();
		Object obj3 = holderPackage.getObj3();
		try {
		  Constructor constructor = holderClass.getConstructor(View.class, Object.class, Object.class, Object.class);
		  AutoHolder autoHolder = (AutoHolder) constructor.newInstance(itemView, obj1, obj2, obj3);
		
		  holderList.add(autoHolder);
		  return autoHolder;
}
```


使用方式：

```java

		autoRecyclerAdapter.setHolder(AutoBannerHolder.class, R.layout.item_banner, this)
```

**5-3. 如何简单，优雅的设置**


以一个Adapter需要多种Holder为例：


设置Holder

```java

		autoRecyclerAdapter.setHolder(AutoBannerHolder.class, R.layout.item_banner, this)
		    .setHolder(AutoTypeAHolder.class, R.layout.item_type_a)
		    .setHolder(AutoTypeBHolder.class, R.layout.item_type_b)
		    .setHolder(AutoTypeCHolder.class, R.layout.item_type_c)
		    .setHolder(AutoTypeDHolder.class, R.layout.item_type_d)
		    .setHolder(AutoTypeEHolder.class, R.layout.item_type_e)
		    .setHolder(AutoTypeFHolder.class, R.layout.item_type_f);
```


网络请求获取数据，可以无视这个。。。：

```java

		//net work request data
		List<ZhaoBean> zhaoList = ModelHelper.getZhaoList(1);
		List<QianBean> qianList = ModelHelper.getQianList(10);
		List<SunBean> sunList = ModelHelper.getSunList(1);
		List<LiBean> liList = ModelHelper.getLiList(4);
		List<ZhouBean> zhouList = ModelHelper.getZhouList(10);
		List<WuBean> wuList = ModelHelper.getWuList(1);
		List<ZhengBean> zhengList = ModelHelper.getZhengList(30);
```

设置Adapter的model：

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

尽量做到使用简单，优雅了。。。


**5-4. 解决了旧的问题，是否出现了一些新的问题**

* Adapter的List，数据存储顺序决定视图呈现的顺序。List相对封闭，目前add与remove没问题，但是想要查找List某一个model，比较其中一个，处理的还不行
* 使用上和原来的Adapter不一样了，没办法，外面配置就好了，不用自己手动写一大推if与else了
* AutoRecyclerAdapter并没有长时间的进行测试，还需要慢慢使用，不断调整。