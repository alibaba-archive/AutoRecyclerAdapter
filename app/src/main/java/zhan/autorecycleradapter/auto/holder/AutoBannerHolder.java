package zhan.autorecycleradapter.auto.holder;

import android.view.View;
import android.widget.ImageView;
import zhan.auto_adapter.AutoHolder;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.ZhaoBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoBannerHolder extends AutoHolder<ZhaoBean> {

  private ImageView iv;

  public AutoBannerHolder(View itemView) {
    super(itemView);
    iv = (ImageView) itemView.findViewById(R.id.banner_iv);
  }

  @Override public void bind(int position, ZhaoBean bean) {
    iv.setImageResource(bean.getIcon());
  }
}
