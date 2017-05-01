package zhan.auto_adapter;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoHolderPackage<H extends AutoHolder> {

  private Class<H> holderClass;
  private int holderLayoutRes;

  public AutoHolderPackage(Class<H> holderClass, int holderLayoutRes) {
    this.holderClass = holderClass;
    this.holderLayoutRes = holderLayoutRes;
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
}
