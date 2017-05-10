package zhan.auto_adapter;

import java.util.Map;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoHolderPackage<H extends AutoHolder> {

    private Class<H> holderClass;
    private int holderLayoutRes;
    private Map<String, Object> dataMap;

    public AutoHolderPackage(Class<H> holderClass, int holderLayoutRes) {
        this.holderClass = holderClass;
        this.holderLayoutRes = holderLayoutRes;
    }

    public AutoHolderPackage(Class<H> holderClass, int holderLayoutRes,
        Map<String, Object> dataMap) {
        this.holderClass = holderClass;
        this.holderLayoutRes = holderLayoutRes;
        this.dataMap = dataMap;
    }

    public Class<H> getHolderClass() {
        return holderClass;
    }

    public void setHolderClass(Class<H> holderClass) {
        this.holderClass = holderClass;
    }

    public int getHolderLayoutRes() {
        return holderLayoutRes;
    }

    public void setHolderLayoutRes(int holderLayoutRes) {
        this.holderLayoutRes = holderLayoutRes;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
