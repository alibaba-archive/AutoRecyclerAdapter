package zhan.autorecycleradapter.auto.holder;

import android.view.View;
import android.widget.ImageView;
import java.util.Map;
import zhan.auto_adapter.AutoHolder;
import zhan.auto_adapter.OnAutoHolderListener;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.ZhaoBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoBannerHolder extends AutoHolder<ZhaoBean> implements View.OnClickListener {

    private ImageView iv;
    private int position;
    private ZhaoBean zhaoBean;

    public AutoBannerHolder(View itemView, Map<String, Object> dataMap) {
        super(itemView, dataMap);
        iv = (ImageView) itemView.findViewById(R.id.banner_iv);
        itemView.setOnClickListener(this);
    }

    @Override public void bind(int position, ZhaoBean bean) {
        this.position = position;
        zhaoBean = bean;
        iv.setImageResource(zhaoBean.getIcon());
    }

    @Override public void onClick(View v) {
        OnAutoHolderListener listener = getOnAutoHolderListener();
        if (listener != null) {
            listener.onAutoHolder(position, zhaoBean);
        }
    }
}
