package zhan.autorecycleradapter.standard.bean;

import java.util.ArrayList;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.LiBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class LiMultiBean extends MultiType {

  private String text;
  private String str;
  private int icon = R.mipmap.avatar;

  public LiMultiBean(String text) {
    this.text = text;
  }

  public LiMultiBean(String text, String str) {
    this.text = text;
    this.str = str;
  }

  public LiMultiBean(String text, String str, int icon) {
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

  public static List<LiMultiBean> toList(List<LiBean> list, int type, int spanSize) {
    List<LiMultiBean> newList = new ArrayList<>();
    for (LiBean bean : list) {
      LiMultiBean multiBean =
          new LiMultiBean(bean.getText(), bean.getStr(), bean.getIcon());
      multiBean.setType(type);
      multiBean.setSpanSize(spanSize);
      newList.add(multiBean);
    }
    return newList;
  }
}
