package zhan.autorecycleradapter.standard.bean;

import java.util.ArrayList;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.ZhengBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class ZhengMultiBean extends MultiType {

  private String text;
  private String str;
  private int icon = R.mipmap.avatar;

  public ZhengMultiBean(String text) {
    this.text = text;
  }

  public ZhengMultiBean(String text, String str) {
    this.text = text;
    this.str = str;
  }

  public ZhengMultiBean(String text, String str, int icon) {
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

  public static List<ZhengMultiBean> toList(List<ZhengBean> list, int type, int spanSize) {
    List<ZhengMultiBean> newList = new ArrayList<>();
    for (ZhengBean bean : list) {
      ZhengMultiBean multiBean =
          new ZhengMultiBean(bean.getText(), bean.getStr(), bean.getIcon());
      multiBean.setType(type);
      multiBean.setSpanSize(spanSize);
      newList.add(multiBean);
    }
    return newList;
  }
}
