package zhan.autorecycleradapter.utils;

import java.util.ArrayList;
import java.util.List;
import zhan.autorecycleradapter.bean.LiBean;
import zhan.autorecycleradapter.bean.QianBean;
import zhan.autorecycleradapter.bean.SunBean;
import zhan.autorecycleradapter.bean.WangBean;
import zhan.autorecycleradapter.bean.WuBean;
import zhan.autorecycleradapter.bean.ZhaoBean;
import zhan.autorecycleradapter.bean.ZhengBean;
import zhan.autorecycleradapter.bean.ZhouBean;

/**
 * Created by ruzhan on 2017/4/20.
 */

public final class ModelHelper {

  private ModelHelper() {}

  public static List<ZhaoBean> getZhaoList(int size) {
    List<ZhaoBean> list = new ArrayList<>();
    for (int x = 0; x < size; x++) {
      ZhaoBean bean = new ZhaoBean("Zhao " + x);
      list.add(bean);
    }
    return list;
  }

  public static List<QianBean> getQianList(int size) {
    List<QianBean> list = new ArrayList<>();
    for (int x = 0; x < size; x++) {
      QianBean bean = new QianBean("Qian " + x);
      list.add(bean);
    }
    return list;
  }

  public static List<SunBean> getSunList(int size) {
    List<SunBean> list = new ArrayList<>();
    for (int x = 0; x < size; x++) {
      SunBean bean = new SunBean("Sun " + x);
      list.add(bean);
    }
    return list;
  }

  public static List<LiBean> getLiList(int size) {
    List<LiBean> list = new ArrayList<>();
    for (int x = 0; x < size; x++) {
      LiBean bean = new LiBean("Li " + x);
      list.add(bean);
    }
    return list;
  }

  public static List<ZhouBean> getZhouList(int size) {
    List<ZhouBean> list = new ArrayList<>();
    for (int x = 0; x < size; x++) {
      ZhouBean bean = new ZhouBean("Zhou " + x);
      list.add(bean);
    }
    return list;
  }

  public static List<WuBean> getWuList(int size) {
    List<WuBean> list = new ArrayList<>();
    for (int x = 0; x < size; x++) {
      WuBean bean = new WuBean("Wu " + x);
      list.add(bean);
    }
    return list;
  }

  public static List<ZhengBean> getZhengList(int size) {
    List<ZhengBean> list = new ArrayList<>();
    for (int x = 0; x < size; x++) {
      ZhengBean bean = new ZhengBean("Zheng " + x);
      list.add(bean);
    }
    return list;
  }

  public static List<WangBean> getWangList(int size) {
    List<WangBean> list = new ArrayList<>();
    for (int x = 0; x < size; x++) {
      WangBean bean = new WangBean("Wang " + x);
      list.add(bean);
    }
    return list;
  }
}
