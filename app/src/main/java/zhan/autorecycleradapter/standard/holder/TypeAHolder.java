package zhan.autorecycleradapter.standard.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.QianBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class TypeAHolder extends RecyclerView.ViewHolder {

  private ImageView iv;
  private TextView tv;

  public TypeAHolder(View itemView) {
    super(itemView);
    iv = (ImageView) itemView.findViewById(R.id.icon_iv);
    tv = (TextView) itemView.findViewById(R.id.title_tv);
  }

  public void bind(int position, QianBean bean) {
    iv.setImageResource(bean.getIcon());
    tv.setText(bean.getText());
  }
}
