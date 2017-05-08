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

public class AutoRecyclerAdapter extends RecyclerView.Adapter {

    protected List<AutoPackage> packageList = new ArrayList<>();
    protected List<Object> dataList = new ArrayList<>();
    protected SparseArray<AutoHolderPackage> holderPackageMap = new SparseArray<>();
    private SparseArray<Constructor> holderConstructorMap = new SparseArray<>();
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
            Constructor constructor = holderConstructorMap.get(viewType);
            if (constructor == null) {
                constructor = holderClass.getConstructor(View.class, Object.class, Object.class,
                    Object.class);
                holderConstructorMap.put(viewType, constructor);
            }
            AutoHolder autoHolder =
                (AutoHolder) constructor.newInstance(itemView, obj1, obj2, obj3);

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

    public <H extends AutoHolder> AutoRecyclerAdapter setHolder(Integer key, Class<H> holderClass,
        int layoutRes) {
        holderPackageMap.put(key, new AutoHolderPackage<>(holderClass, layoutRes));
        return this;
    }

    public <H extends AutoHolder> AutoRecyclerAdapter setHolder(Integer key, Class<H> holderClass,
        int layoutRes, Object obj1, Object obj2, Object obj3) {
        holderPackageMap.put(key,
            new AutoHolderPackage<>(holderClass, layoutRes, obj1, obj2, obj3));
        return this;
    }

    public <H extends AutoHolder> AutoRecyclerAdapter setHolder(Class<H> holderClass,
        int layoutRes) {
        return setHolder(holderClass.hashCode(), holderClass, layoutRes);
    }

    public <H extends AutoHolder> AutoRecyclerAdapter setHolder(Class<H> holderClass, int layoutRes,
        Object obj1) {
        return setHolder(holderClass.hashCode(), holderClass, layoutRes, obj1, null, null);
    }

    public <H extends AutoHolder> AutoRecyclerAdapter setHolder(Class<H> holderClass, int layoutRes,
        Object obj1, Object obj2) {
        return setHolder(holderClass.hashCode(), holderClass, layoutRes, obj1, obj2, null);
    }

    public <H extends AutoHolder> AutoRecyclerAdapter setHolder(Class<H> holderClass, int layoutRes,
        Object obj1, Object obj2, Object obj3) {
        return setHolder(holderClass.hashCode(), holderClass, layoutRes, obj1, obj2, obj3);
    }

    /////////////////////////////////////////////////////////////////////////
    //                               set data bean
    /////////////////////////////////////////////////////////////////////////

    private <M> AutoRecyclerAdapter setDataObject(int index, int type, M bean, int spanSize) {
        AutoPackage autoPackage = new AutoPackage(type, bean, spanSize);
        packageList.add(index, autoPackage);
        dataList.add(index, bean);
        return this;
    }

    private <M> AutoRecyclerAdapter setDataObject(int type, M bean, int spanSize) {
        AutoPackage autoPackage = new AutoPackage(type, bean, spanSize);
        packageList.add(autoPackage);
        dataList.add(bean);
        return this;
    }

    public <H extends AutoHolder, M> AutoRecyclerAdapter setDataObject(Class<H> holderClass,
        M bean) {
        return setDataObject(holderClass.hashCode(), bean, 0);
    }

    public <H extends AutoHolder, M> AutoRecyclerAdapter setDataObjectIndex(int index,
        Class<H> holderClass, M bean) {
        return setDataObject(index, holderClass.hashCode(), bean, 0);
    }

    public <H extends AutoHolder, M> AutoRecyclerAdapter setDataObjectSpan(Class<H> holderClass,
        M bean, int spanSize) {
        return setDataObject(holderClass.hashCode(), bean, spanSize);
    }

    public <H extends AutoHolder, M> AutoRecyclerAdapter setDataObjectSpanIndex(int index,
        Class<H> holderClass, M bean, int spanSize) {
        return setDataObject(index, holderClass.hashCode(), bean, spanSize);
    }

    public <M> AutoRecyclerAdapter setDataObject(int key, M bean) {
        return setDataObject(key, bean, 0);
    }

    public <M> AutoRecyclerAdapter setDataObjectIndex(int index, int key, M bean) {
        return setDataObject(index, key, bean, 0);
    }

    public <M> AutoRecyclerAdapter setDataObjectSpan(int key, M bean, int spanSize) {
        return setDataObject(key, bean, spanSize);
    }

    public <M> AutoRecyclerAdapter setDataObjectSpanIndex(int index, int key, M bean,
        int spanSize) {
        return setDataObject(index, key, bean, spanSize);
    }

    /////////////////////////////////////////////////////////////////////////
    //                               remove data bean
    /////////////////////////////////////////////////////////////////////////

    public AutoRecyclerAdapter removeDataObject(int index) {
        packageList.remove(index);
        dataList.remove(index);
        return this;
    }

    public <M> AutoRecyclerAdapter removeDataObject(M bean) {
        int index = dataList.indexOf(bean);
        if (index >= 0) {
            removeDataObject(index);
        }
        return this;
    }

    /////////////////////////////////////////////////////////////////////////
    //                               contains data bean
    /////////////////////////////////////////////////////////////////////////

    public <M> boolean containsDataObject(M bean) {
        boolean flag = false;
        for (Object object : dataList) {
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

    private <M> AutoRecyclerAdapter setDataList(int type, List<M> list, int spanSize) {
        if (list != null) {
            for (M bean : list) {
                AutoPackage autoPackage = new AutoPackage(type, bean, spanSize);
                packageList.add(autoPackage);
                dataList.add(bean);
            }
        }
        return this;
    }

    private <M> AutoRecyclerAdapter setDataList(int index, int type, List<M> list, int spanSize) {
        if (list != null) {
            for (int x = list.size() - 1; x >= 0; x--) {
                M bean = list.get(x);
                AutoPackage autoPackage = new AutoPackage(type, bean, spanSize);
                packageList.add(index, autoPackage);
                dataList.add(index, bean);
            }
        }
        return this;
    }

    public <H extends AutoHolder, M> AutoRecyclerAdapter setDataList(Class<H> holderClass,
        List<M> list) {
        return setDataList(holderClass.hashCode(), list, 0);
    }

    public <H extends AutoHolder, M> AutoRecyclerAdapter setDataListIndex(int index,
        Class<H> holderClass, List<M> list) {
        return setDataList(index, holderClass.hashCode(), list, 0);
    }

    public <H extends AutoHolder, M> AutoRecyclerAdapter setDataListSpan(Class<H> holderClass,
        List<M> list, int spanSize) {
        return setDataList(holderClass.hashCode(), list, spanSize);
    }

    public <H extends AutoHolder, M> AutoRecyclerAdapter setDataListSpanIndex(int index,
        Class<H> holderClass, List<M> list, int spanSize) {
        return setDataList(index, holderClass.hashCode(), list, spanSize);
    }

    public <M> AutoRecyclerAdapter setDataList(int type, List<M> list) {
        return setDataList(type, list, 0);
    }

    public <M> AutoRecyclerAdapter setDataListIndex(int index, int type, List<M> list) {
        return setDataList(index, type, list, 0);
    }

    public <M> AutoRecyclerAdapter setDataListSpan(int type, List<M> list, int spanSize) {
        return setDataList(type, list, spanSize);
    }

    public <M> AutoRecyclerAdapter setDataListSpanIndex(int index, int type, List<M> list,
        int spanSize) {
        return setDataList(index, type, list, spanSize);
    }

    /////////////////////////////////////////////////////////////////////////
    //                               other
    /////////////////////////////////////////////////////////////////////////

    public List<AutoHolder> getHolderList() {
        return holderList;
    }

    public List<AutoPackage> getAutoPackageList() {
        return packageList;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public AutoRecyclerAdapter clear() {
        packageList.clear();
        dataList.clear();
        return this;
    }

    public AutoRecyclerAdapter clearHolder() {
        holderList.clear();
        return this;
    }
}
