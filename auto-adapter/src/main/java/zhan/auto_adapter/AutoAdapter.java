package zhan.auto_adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoAdapter extends RecyclerView.Adapter {

  protected List<AutoPackage> packageList = new ArrayList<>();
  protected SparseArray<AutoHolderPackage> holderPackageMap = new SparseArray<>();
  protected List<AutoHolder> holderList = new ArrayList<>();

  @Override public int getItemViewType(int position) {
    return packageList.get(position).getType();
  }

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

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof AutoHolder) {
      AutoHolder autoHolder = (AutoHolder) holder;
      Object bean = packageList.get(position).getAutoPackage();
      autoHolder.bind(position, bean);
    }
  }

  @Override public int getItemCount() {
    return packageList.size();
  }

  public int getSpanSize(int position) {
    return packageList.get(position).getSpanSize();
  }

  /////////////////////////////////////////////////////////////////////////
  //                               set holder
  /////////////////////////////////////////////////////////////////////////

  public <H extends AutoHolder> AutoAdapter setHolder(Integer key, Class<H> holderClass,
      int layoutRes) {
    holderPackageMap.put(key, new AutoHolderPackage<>(holderClass, layoutRes));
    return this;
  }

  public <H extends AutoHolder> AutoAdapter setHolder(Integer key, Class<H> holderClass,
      int layoutRes, Object obj1, Object obj2, Object obj3) {
    holderPackageMap.put(key, new AutoHolderPackage<>(holderClass, layoutRes, obj1, obj2, obj3));
    return this;
  }

  public <H extends AutoHolder> AutoAdapter setHolder(Class<H> holderClass, int layoutRes) {
    return setHolder(holderClass.hashCode(), holderClass, layoutRes);
  }

  public <H extends AutoHolder> AutoAdapter setHolder(Class<H> holderClass, int layoutRes,
      Object obj1) {
    return setHolder(holderClass.hashCode(), holderClass, layoutRes, obj1, null, null);
  }

  public <H extends AutoHolder> AutoAdapter setHolder(Class<H> holderClass, int layoutRes,
      Object obj1, Object obj2) {
    return setHolder(holderClass.hashCode(), holderClass, layoutRes, obj1, obj2, null);
  }

  public <H extends AutoHolder> AutoAdapter setHolder(Class<H> holderClass, int layoutRes,
      Object obj1, Object obj2, Object obj3) {
    return setHolder(holderClass.hashCode(), holderClass, layoutRes, obj1, obj2, obj3);
  }

  /////////////////////////////////////////////////////////////////////////
  //                               set data bean
  /////////////////////////////////////////////////////////////////////////

  private <M> AutoAdapter setDataObject(int index, int type, M bean, int spanSize) {
    if (index < 0) {
      index = 0;
    }
    AutoPackage autoPackage = new AutoPackage(type, bean, spanSize);
    packageList.add(index, autoPackage);
    return this;
  }

  public <H extends AutoHolder, M> AutoAdapter setDataObject(Class<H> holderClass, M bean) {
    return setDataObject(packageList.size() - 1, holderClass.hashCode(), bean, 0);
  }

  public <H extends AutoHolder, M> AutoAdapter setDataObjectIndex(int index, Class<H> holderClass,
      M bean) {
    return setDataObject(index, holderClass.hashCode(), bean, 0);
  }

  public <H extends AutoHolder, M> AutoAdapter setDataObjectSpan(Class<H> holderClass, M bean,
      int spanSize) {
    return setDataObject(packageList.size() - 1, holderClass.hashCode(), bean, spanSize);
  }

  public <H extends AutoHolder, M> AutoAdapter setDataObjectSpanIndex(int index,
      Class<H> holderClass, M bean, int spanSize) {
    return setDataObject(index, holderClass.hashCode(), bean, spanSize);
  }

  public <M> AutoAdapter setDataObject(int key, M bean) {
    return setDataObject(packageList.size() - 1, key, bean, 0);
  }

  public <M> AutoAdapter setDataObjectIndex(int index, int key, M bean) {
    return setDataObject(index, key, bean, 0);
  }

  public <M> AutoAdapter setDataObjectSpan(int key, M bean, int spanSize) {
    return setDataObject(packageList.size() - 1, key, bean, spanSize);
  }

  public <M> AutoAdapter setDataObjectSpanIndex(int index, int key, M bean, int spanSize) {
    return setDataObject(index, key, bean, spanSize);
  }

  /////////////////////////////////////////////////////////////////////////
  //                               remove data bean
  /////////////////////////////////////////////////////////////////////////

  public void removeDataObject(int index) {
    packageList.remove(index);
  }

  /////////////////////////////////////////////////////////////////////////
  //                               contains data bean
  /////////////////////////////////////////////////////////////////////////

  public <M> boolean containsDataObject(M bean) {
    boolean flag = false;
    for (AutoPackage autoPackage : packageList) {
      Object object = autoPackage.getAutoPackage();
      if (object == bean) {
        flag = true;
        break;
      }
    }
    return flag;
  }

  /////////////////////////////////////////////////////////////////////////
  //                               set data list
  /////////////////////////////////////////////////////////////////////////

  private <M> AutoAdapter setDataList(int type, List<M> list, int spanSize) {
    if (list != null) {
      for (M bean : list) {
        AutoPackage autoPackage = new AutoPackage(type, bean, spanSize);
        packageList.add(autoPackage);
      }
    }
    return this;
  }

  private <M> AutoAdapter setDataList(int index, int type, List<M> list, int spanSize) {
    if (list != null) {
      for (int x = list.size() - 1; x >= 0; x--) {
        M bean = list.get(x);
        AutoPackage autoPackage = new AutoPackage(type, bean, spanSize);
        packageList.add(index, autoPackage);
      }
    }
    return this;
  }

  public <H extends AutoHolder, M> AutoAdapter setDataList(Class<H> holderClass, List<M> list) {
    return setDataList(holderClass.hashCode(), list, 0);
  }

  public <H extends AutoHolder, M> AutoAdapter setDataListIndex(int index, Class<H> holderClass,
      List<M> list) {
    return setDataList(index, holderClass.hashCode(), list, 0);
  }

  public <H extends AutoHolder, M> AutoAdapter setDataListSpan(Class<H> holderClass, List<M> list,
      int spanSize) {
    return setDataList(holderClass.hashCode(), list, spanSize);
  }

  public <H extends AutoHolder, M> AutoAdapter setDataListSpanIndex(int index, Class<H> holderClass,
      List<M> list, int spanSize) {
    return setDataList(index, holderClass.hashCode(), list, spanSize);
  }

  public <M> AutoAdapter setDataList(int type, List<M> list) {
    return setDataList(type, list, 0);
  }

  public <M> AutoAdapter setDataListIndex(int index, int type, List<M> list) {
    return setDataList(index, type, list, 0);
  }

  public <M> AutoAdapter setDataListSpan(int type, List<M> list, int spanSize) {
    return setDataList(type, list, spanSize);
  }

  public <M> AutoAdapter setDataListSpanIndex(int index, int type, List<M> list, int spanSize) {
    return setDataList(index, type, list, spanSize);
  }

  /////////////////////////////////////////////////////////////////////////
  //                               other
  /////////////////////////////////////////////////////////////////////////

  public List<AutoHolder> getHolderList() {
    return holderList;
  }
}
