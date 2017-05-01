package zhan.autorecycleradapter.standard.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.standard.bean.LiMultiBean;
import zhan.autorecycleradapter.standard.bean.MultiType;
import zhan.autorecycleradapter.standard.bean.QianMultiBean;
import zhan.autorecycleradapter.standard.bean.SunMultiBean;
import zhan.autorecycleradapter.standard.bean.WuMultiBean;
import zhan.autorecycleradapter.standard.bean.ZhaoMultiBean;
import zhan.autorecycleradapter.standard.bean.ZhengMultiBean;
import zhan.autorecycleradapter.standard.bean.ZhouMultiBean;
import zhan.autorecycleradapter.standard.holder.multi.BannerMultiHolder;
import zhan.autorecycleradapter.standard.holder.multi.MultiHolder;
import zhan.autorecycleradapter.standard.holder.multi.TypeAMultiHolder;
import zhan.autorecycleradapter.standard.holder.multi.TypeBMultiHolder;
import zhan.autorecycleradapter.standard.holder.multi.TypeCMultiHolder;
import zhan.autorecycleradapter.standard.holder.multi.TypeDMultiHolder;
import zhan.autorecycleradapter.standard.holder.multi.TypeEMultiHolder;
import zhan.autorecycleradapter.standard.holder.multi.TypeFMultiHolder;

/**
 * Created by ruzhan on 2017/4/20.
 */

public class StandardMultiAdapter2 extends RecyclerView.Adapter {

  public static final int SPAN_SIZE = 10;

  public static final int TYPE_ZHAO = 1000;
  public static final int TYPE_QIAN = 1001;
  public static final int TYPE_SUN = 1002;
  public static final int TYPE_LI = 1003;
  public static final int TYPE_ZHOU = 1004;
  public static final int TYPE_WU = 1005;
  public static final int TYPE_ZHENG = 1006;

  private List<MultiType> data = new ArrayList<>();

  public void setData(List<ZhaoMultiBean> zhaoList, List<QianMultiBean> qianList, List<SunMultiBean> sunList,
      List<LiMultiBean> liList, List<ZhouMultiBean> zhouList, List<WuMultiBean> wuList,
      List<ZhengMultiBean> zhengList) {
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
    MultiType multiType = data.get(position);
    return multiType.getType();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_ZHAO) {
      return new BannerMultiHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false));
    } else if (viewType == TYPE_QIAN) {
      return new TypeAMultiHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_a, parent, false));
    } else if (viewType == TYPE_SUN) {
      return new TypeBMultiHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_b, parent, false));
    } else if (viewType == TYPE_LI) {
      return new TypeCMultiHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_c, parent, false));
    } else if (viewType == TYPE_ZHOU) {
      return new TypeDMultiHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_d, parent, false));
    } else if (viewType == TYPE_WU) {
      return new TypeEMultiHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_e, parent, false));
    } else if (viewType == TYPE_ZHENG) {
      return new TypeFMultiHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_f, parent, false));
    }
    return new TypeFMultiHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_f, parent, false));
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if(holder instanceof MultiHolder) {
      MultiHolder multiHolder = (MultiHolder) holder;
      multiHolder.bind(position, data.get(position));
    }
  }

  @Override public int getItemCount() {
    return data == null ? 0 : data.size();
  }

  public int getSpanSize(int position) {
    MultiType multiType = data.get(position);
    return multiType.getSpanSize();
  }
}
