package zhan.autorecycleradapter.auto.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Map;
import zhan.auto_adapter.AutoHolder;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.ZhaoBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoSingleHolder extends AutoHolder<ZhaoBean> {

    private ImageView iv;
    private TextView tv;

    public AutoSingleHolder(View itemView, Map<String, Object> dataMap) {
        super(itemView, dataMap);
        iv = (ImageView) itemView.findViewById(R.id.icon_iv);
        tv = (TextView) itemView.findViewById(R.id.title_tv);
    }

    @Override public void bind(int position, ZhaoBean bean) {
        iv.setImageResource(bean.getIcon());
        tv.setText(bean.getText());
    }
}
