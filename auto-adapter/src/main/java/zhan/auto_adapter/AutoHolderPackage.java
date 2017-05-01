package zhan.auto_adapter;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoHolderPackage<H extends AutoHolder> {

  private Class<H> holderClass;
  private int holderLayoutRes;
  private Object obj1;
  private Object obj2;
  private Object obj3;

  public AutoHolderPackage(Class<H> holderClass, int holderLayoutRes) {
    this.holderClass = holderClass;
    this.holderLayoutRes = holderLayoutRes;
  }

  public AutoHolderPackage(Class<H> holderClass, int holderLayoutRes, Object obj1) {
    this.holderClass = holderClass;
    this.holderLayoutRes = holderLayoutRes;
    this.obj1 = obj1;
  }

  public AutoHolderPackage(Class<H> holderClass, int holderLayoutRes, Object obj1, Object obj2) {
    this.holderClass = holderClass;
    this.holderLayoutRes = holderLayoutRes;
    this.obj1 = obj1;
    this.obj2 = obj2;
  }

  public AutoHolderPackage(Class<H> holderClass, int holderLayoutRes, Object obj1, Object obj2,
      Object obj3) {
    this.holderClass = holderClass;
    this.holderLayoutRes = holderLayoutRes;
    this.obj1 = obj1;
    this.obj2 = obj2;
    this.obj3 = obj3;
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

  public Object getObj1() {
    return obj1;
  }

  public void setObj1(Object obj1) {
    this.obj1 = obj1;
  }

  public Object getObj2() {
    return obj2;
  }

  public void setObj2(Object obj2) {
    this.obj2 = obj2;
  }

  public Object getObj3() {
    return obj3;
  }

  public void setObj3(Object obj3) {
    this.obj3 = obj3;
  }
}
