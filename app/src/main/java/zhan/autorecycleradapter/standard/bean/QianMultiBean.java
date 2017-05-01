package zhan.autorecycleradapter.standard.bean;

import java.util.ArrayList;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.QianBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class QianMultiBean extends MultiType {

  private String text;
  private String str;
  private int icon = R.mipmap.avatar;

  public QianMultiBean(String text) {
    this.text = text;
  }

  public QianMultiBean(String text, String str) {
    this.text = text;
    this.str = str;
  }

  public QianMultiBean(String text, String str, int icon) {
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

  public static List<QianMultiBean> toList(List<QianBean> list, int type, int spanSize) {
    List<QianMultiBean> newList = new ArrayList<>();
    for (QianBean bean : list) {
      QianMultiBean multiBean =
          new QianMultiBean(bean.getText(), bean.getStr(), bean.getIcon());
      multiBean.setType(type);
      multiBean.setSpanSize(spanSize);
      newList.add(multiBean);
    }
    return newList;
  }
}
