package zhan.autorecycleradapter.standard.holder.multi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.standard.bean.LiMultiBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class TypeCMultiHolder extends MultiHolder<LiMultiBean> {

  private ImageView iv;
  private TextView tv;

  public TypeCMultiHolder(View itemView) {
    super(itemView);
    iv = (ImageView) itemView.findViewById(R.id.icon_iv);
    tv = (TextView) itemView.findViewById(R.id.title_tv);
  }

  @Override public void bind(int position, LiMultiBean bean) {
    iv.setImageResource(bean.getIcon());
    tv.setText(bean.getText());
  }
}
