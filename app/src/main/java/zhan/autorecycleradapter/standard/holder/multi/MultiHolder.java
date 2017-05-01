package zhan.autorecycleradapter.standard.holder.multi;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ruzhan on 2017/5/2.
 */

public abstract class MultiHolder<M> extends RecyclerView.ViewHolder {

  public MultiHolder(View itemView) {
    super(itemView);
  }

  public abstract void bind(int position, M bean);
}
