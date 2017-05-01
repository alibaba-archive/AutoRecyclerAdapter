package zhan.auto_adapter;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoPackage {

  private int type;
  private Object autoPackage;
  private int spanSize;

  public AutoPackage(int type, Object autoPackage) {
    this.type = type;
    this.autoPackage = autoPackage;
  }

  public AutoPackage(int type, Object autoPackage, int spanSize) {
    this.type = type;
    this.autoPackage = autoPackage;
    this.spanSize = spanSize;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getSpanSize() {
    return spanSize;
  }

  public void setSpanSize(int spanSize) {
    this.spanSize = spanSize;
  }

  public Object getAutoPackage() {
    return autoPackage;
  }

  public void setAutoPackage(Object autoPackage) {
    this.autoPackage = autoPackage;
  }
}
