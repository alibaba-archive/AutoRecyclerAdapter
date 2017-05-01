package zhan.autorecycleradapter.standard.bean;

import java.util.ArrayList;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.WuBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class WuMultiBean extends MultiType {

  private String text;
  private String str;
  private int icon = R.mipmap.avatar;

  public WuMultiBean(String text) {
    this.text = text;
  }

  public WuMultiBean(String text, String str) {
    this.text = text;
    this.str = str;
  }

  public WuMultiBean(String text, String str, int icon) {
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

  public static List<WuMultiBean> toList(List<WuBean> list, int type, int spanSize) {
    List<WuMultiBean> newList = new ArrayList<>();
    for (WuBean bean : list) {
      WuMultiBean multiBean =
          new WuMultiBean(bean.getText(), bean.getStr(), bean.getIcon());
      multiBean.setType(type);
      multiBean.setSpanSize(spanSize);
      newList.add(multiBean);
    }
    return newList;
  }

}
