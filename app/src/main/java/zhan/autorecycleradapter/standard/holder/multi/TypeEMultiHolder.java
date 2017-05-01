package zhan.autorecycleradapter.standard.holder.multi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.standard.bean.WuMultiBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class TypeEMultiHolder extends MultiHolder<WuMultiBean> {

  private ImageView iv;
  private TextView tv;

  public TypeEMultiHolder(View itemView) {
    super(itemView);
    iv = (ImageView) itemView.findViewById(R.id.icon_iv);
    tv = (TextView) itemView.findViewById(R.id.title_tv);
  }

  @Override public void bind(int position, WuMultiBean bean) {
    iv.setImageResource(bean.getIcon());
    tv.setText(bean.getText());
  }
}
