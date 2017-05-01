package zhan.autorecycleradapter.auto.holder;

import android.view.View;
import android.widget.ImageView;
import zhan.auto_adapter.AutoHolder;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.auto.SendListener;
import zhan.autorecycleradapter.bean.ZhaoBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoBannerHolder extends AutoHolder<ZhaoBean> implements View.OnClickListener {

  private ImageView iv;

  public AutoBannerHolder(View itemView, Object obj1, Object obj2, Object obj3) {
    super(itemView, obj1, obj2, obj3);
    iv = (ImageView) itemView.findViewById(R.id.banner_iv);
    itemView.setOnClickListener(this);
  }

  @Override public void bind(int position, ZhaoBean bean) {
    iv.setImageResource(bean.getIcon());
  }

  @Override public void onClick(View v) {
    if(obj1 instanceof SendListener) {
      ((SendListener)obj1).send();
    }
  }
}
