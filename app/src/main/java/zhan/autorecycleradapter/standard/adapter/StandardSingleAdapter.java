package zhan.autorecycleradapter.standard.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.ZhaoBean;
import zhan.autorecycleradapter.standard.holder.StandardSingleHolder;

/**
 * Created by ruzhan on 2017/4/20.
 */

public class StandardSingleAdapter extends RecyclerView.Adapter {

  private List<ZhaoBean> data;

  public void setData(List<ZhaoBean> data) {
    if (data == null || data.isEmpty()) {
      return;
    }
    this.data = data;
    notifyDataSetChanged();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new StandardSingleHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single, parent, false));
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((StandardSingleHolder) holder).bind(data.get(position));
  }

  @Override public int getItemCount() {
    return data == null ? 0 : data.size();
  }
}
