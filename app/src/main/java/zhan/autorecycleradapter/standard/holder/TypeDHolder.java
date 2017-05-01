package zhan.autorecycleradapter.standard.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.ZhouBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class TypeDHolder extends RecyclerView.ViewHolder {

  private ImageView iv;
  private TextView tv;

  public TypeDHolder(View itemView) {
    super(itemView);
    iv = (ImageView) itemView.findViewById(R.id.icon_iv);
    tv = (TextView) itemView.findViewById(R.id.title_tv);
  }

  public void bind(int position, ZhouBean bean) {
    iv.setImageResource(bean.getIcon());
    tv.setText(bean.getText());
  }
}
