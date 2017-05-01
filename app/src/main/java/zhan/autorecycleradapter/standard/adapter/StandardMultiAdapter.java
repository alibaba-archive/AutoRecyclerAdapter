package zhan.autorecycleradapter.standard.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.LiBean;
import zhan.autorecycleradapter.bean.QianBean;
import zhan.autorecycleradapter.bean.SunBean;
import zhan.autorecycleradapter.bean.WuBean;
import zhan.autorecycleradapter.bean.ZhaoBean;
import zhan.autorecycleradapter.bean.ZhengBean;
import zhan.autorecycleradapter.bean.ZhouBean;
import zhan.autorecycleradapter.standard.holder.BannerHolder;
import zhan.autorecycleradapter.standard.holder.TypeAHolder;
import zhan.autorecycleradapter.standard.holder.TypeBHolder;
import zhan.autorecycleradapter.standard.holder.TypeCHolder;
import zhan.autorecycleradapter.standard.holder.TypeDHolder;
import zhan.autorecycleradapter.standard.holder.TypeEHolder;
import zhan.autorecycleradapter.standard.holder.TypeFHolder;

/**
 * Created by ruzhan on 2017/4/20.
 */

public class StandardMultiAdapter extends RecyclerView.Adapter {

  public static final int SPAN_SIZE = 10;

  private static final int TYPE_ZHAO = 1000;
  private static final int TYPE_QIAN = 1001;
  private static final int TYPE_SUN = 1002;
  private static final int TYPE_LI = 1003;
  private static final int TYPE_ZHOU = 1004;
  private static final int TYPE_WU = 1005;
  private static final int TYPE_ZHENG = 1006;

  private List<Object> data = new ArrayList<>();

  public void setData(List<ZhaoBean> zhaoList, List<QianBean> qianList, List<SunBean> sunList,
      List<LiBean> liList, List<ZhouBean> zhouList, List<WuBean> wuList,
      List<ZhengBean> zhengList) {
    data.addAll(zhaoList);
    data.addAll(qianList);
    data.addAll(sunList);
    data.addAll(liList);
    data.addAll(zhouList);
    data.addAll(wuList);
    data.addAll(zhengList);
    notifyDataSetChanged();
  }

  @Override public int getItemViewType(int position) {
    Object object = data.get(position);
    if (object instanceof ZhaoBean) {
      return TYPE_ZHAO;
    } else if (object instanceof QianBean) {
      return TYPE_QIAN;
    } else if (object instanceof SunBean) {
      return TYPE_SUN;
    } else if (object instanceof LiBean) {
      return TYPE_LI;
    } else if (object instanceof ZhouBean) {
      return TYPE_ZHOU;
    } else if (object instanceof WuBean) {
      return TYPE_WU;
    } else if (object instanceof ZhengBean) {
      return TYPE_ZHENG;
    }
    return TYPE_ZHENG;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_ZHAO) {
      return new BannerHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false));
    } else if (viewType == TYPE_QIAN) {
      return new TypeAHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_a, parent, false));
    } else if (viewType == TYPE_SUN) {
      return new TypeBHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_b, parent, false));
    } else if (viewType == TYPE_LI) {
      return new TypeCHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_c, parent, false));
    } else if (viewType == TYPE_ZHOU) {
      return new TypeDHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_d, parent, false));
    } else if (viewType == TYPE_WU) {
      return new TypeEHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_e, parent, false));
    } else if (viewType == TYPE_ZHENG) {
      return new TypeFHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_f, parent, false));
    }
    return new TypeFHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_f, parent, false));
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    if (viewType == TYPE_ZHAO) {
      ((BannerHolder) holder).bind(position, (ZhaoBean) data.get(position));
    } else if (viewType == TYPE_QIAN) {
      ((TypeAHolder) holder).bind(position, (QianBean) data.get(position));
    } else if (viewType == TYPE_SUN) {
      ((TypeBHolder) holder).bind(position, (SunBean) data.get(position));
    } else if (viewType == TYPE_LI) {
      ((TypeCHolder) holder).bind(position, (LiBean) data.get(position));
    } else if (viewType == TYPE_ZHOU) {
      ((TypeDHolder) holder).bind(position, (ZhouBean) data.get(position));
    } else if (viewType == TYPE_WU) {
      ((TypeEHolder) holder).bind(position, (WuBean) data.get(position));
    } else if (viewType == TYPE_ZHENG) {
      ((TypeFHolder) holder).bind(position, (ZhengBean) data.get(position));
    }
  }

  @Override public int getItemCount() {
    return data == null ? 0 : data.size();
  }

  public int getSpanSize(int position) {
    int viewType = getItemViewType(position);

    if (viewType == TYPE_ZHAO) {
      return SPAN_SIZE;
    } else if (viewType == TYPE_QIAN) {
      return SPAN_SIZE / 5;
    } else if (viewType == TYPE_SUN) {
      return SPAN_SIZE;
    } else if (viewType == TYPE_LI) {
      return SPAN_SIZE / 2;
    } else if (viewType == TYPE_ZHOU) {
      return SPAN_SIZE / 5;
    } else if (viewType == TYPE_WU) {
      return SPAN_SIZE;
    } else if (viewType == TYPE_ZHENG) {
      return SPAN_SIZE / 2;
    }
    return SPAN_SIZE;
  }
}
