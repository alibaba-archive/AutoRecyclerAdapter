package zhan.autorecycleradapter.standard.bean;

import java.util.ArrayList;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.ZhaoBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class ZhaoMultiBean extends MultiType {

  private String text;
  private String str;
  private int icon = R.mipmap.avatar;

  public ZhaoMultiBean(String text) {
    this.text = text;
  }

  public ZhaoMultiBean(String text, String str) {
    this.text = text;
    this.str = str;
  }

  public ZhaoMultiBean(String text, String str, int icon) {
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

  public static List<ZhaoMultiBean> toList(List<ZhaoBean> list, int type, int spanSize) {
    List<ZhaoMultiBean> newList = new ArrayList<>();
    for (ZhaoBean bean : list) {
      ZhaoMultiBean multiBean =
          new ZhaoMultiBean(bean.getText(), bean.getStr(), bean.getIcon());
      multiBean.setType(type);
      multiBean.setSpanSize(spanSize);
      newList.add(multiBean);
    }
    return newList;
  }
}
