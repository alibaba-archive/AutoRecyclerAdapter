package zhan.auto_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ruzhan on 2017/5/1.
 */

public abstract class AutoHolder<M> extends RecyclerView.ViewHolder {

  protected Object obj1;
  protected Object obj2;
  protected Object obj3;

  public AutoHolder(View itemView, Object obj1, Object obj2, Object obj3) {
    super(itemView);
    this.obj1 = obj1;
    this.obj2 = obj2;
    this.obj3 = obj3;
  }

  public abstract void bind(int position, M bean);
}
