package zhan.auto_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ruzhan on 2017/5/1.
 */

public abstract class AutoHolder<M> extends RecyclerView.ViewHolder {

  public AutoHolder(View itemView) {
    super(itemView);
  }

  public abstract void bind(int position, M bean);
}
