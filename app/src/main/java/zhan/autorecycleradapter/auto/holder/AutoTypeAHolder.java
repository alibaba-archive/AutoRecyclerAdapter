package zhan.autorecycleradapter.auto.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import zhan.auto_adapter.AutoHolder;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.QianBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoTypeAHolder extends AutoHolder<QianBean> {

  private ImageView iv;
  private TextView tv;

  public AutoTypeAHolder(View itemView, Object obj1, Object obj2, Object obj3) {
    super(itemView, obj1, obj2, obj3);
    iv = (ImageView) itemView.findViewById(R.id.icon_iv);
    tv = (TextView) itemView.findViewById(R.id.title_tv);
  }

  @Override public void bind(int position, QianBean bean) {
    iv.setImageResource(bean.getIcon());
    tv.setText(bean.getText());
  }
}
