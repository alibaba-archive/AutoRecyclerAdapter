package zhan.autorecycleradapter.standard.bean;

import java.util.ArrayList;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.SunBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class SunMultiBean extends MultiType {

  private String text;
  private String str;
  private int icon = R.mipmap.avatar;

  public SunMultiBean(String text) {
    this.text = text;
  }

  public SunMultiBean(String text, String str) {
    this.text = text;
    this.str = str;
  }

  public SunMultiBean(String text, String str, int icon) {
    this.text = text;
    this.str = str;
    this.icon = icon;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public int getIcon() {
    return icon;
  }

  public void setIcon(int icon) {
    this.icon = icon;
  }

  public static List<SunMultiBean> toList(List<SunBean> list, int type, int spanSize) {
    List<SunMultiBean> newList = new ArrayList<>();
    for (SunBean bean : list) {
      SunMultiBean multiBean =
          new SunMultiBean(bean.getText(), bean.getStr(), bean.getIcon());
      multiBean.setType(type);
      multiBean.setSpanSize(spanSize);
      newList.add(multiBean);
    }
    return newList;
  }
}
