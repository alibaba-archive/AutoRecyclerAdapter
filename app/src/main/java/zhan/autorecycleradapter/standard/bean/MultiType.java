package zhan.autorecycleradapter.standard.bean;

/**
 * Created by ruzhan on 2017/5/2.
 */

public class MultiType {

  private int type;
  private int spanSize;

  public MultiType() {

  }

  public MultiType(int type) {
    this.type = type;
  }

  public MultiType(int type, int spanSize) {
    this.type = type;
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
}
