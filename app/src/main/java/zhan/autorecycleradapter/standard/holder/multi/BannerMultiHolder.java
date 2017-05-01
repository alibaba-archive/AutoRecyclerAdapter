package zhan.autorecycleradapter.standard.holder.multi;

import android.view.View;
import android.widget.ImageView;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.standard.bean.ZhaoMultiBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class BannerMultiHolder extends MultiHolder<ZhaoMultiBean> {

  private ImageView iv;

  public BannerMultiHolder(View itemView) {
    super(itemView);
    iv = (ImageView) itemView.findViewById(R.id.banner_iv);
  }

  @Override public void bind(int position, ZhaoMultiBean bean) {
    iv.setImageResource(bean.getIcon());
  }
}
