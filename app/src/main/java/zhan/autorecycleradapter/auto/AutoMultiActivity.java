package zhan.autorecycleradapter.auto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.List;
import zhan.auto_adapter.AutoRecyclerAdapter;
import zhan.auto_adapter.OnAutoHolderListener;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.auto.holder.AutoBannerHolder;
import zhan.autorecycleradapter.auto.holder.AutoTypeAHolder;
import zhan.autorecycleradapter.auto.holder.AutoTypeBHolder;
import zhan.autorecycleradapter.auto.holder.AutoTypeCHolder;
import zhan.autorecycleradapter.auto.holder.AutoTypeDHolder;
import zhan.autorecycleradapter.auto.holder.AutoTypeEHolder;
import zhan.autorecycleradapter.auto.holder.AutoTypeFHolder;
import zhan.autorecycleradapter.bean.LiBean;
import zhan.autorecycleradapter.bean.QianBean;
import zhan.autorecycleradapter.bean.SunBean;
import zhan.autorecycleradapter.bean.WuBean;
import zhan.autorecycleradapter.bean.ZhaoBean;
import zhan.autorecycleradapter.bean.ZhengBean;
import zhan.autorecycleradapter.bean.ZhouBean;
import zhan.autorecycleradapter.utils.ModelHelper;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoMultiActivity extends AppCompatActivity implements OnAutoHolderListener {

    private static final int SPAN_SIZE = 10;

    private AutoRecyclerAdapter autoRecyclerAdapter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        autoRecyclerAdapter = new AutoRecyclerAdapter();
        recyclerView.setAdapter(autoRecyclerAdapter);

        //multi item need set SpanSize
        GridLayoutManager manager = new GridLayoutManager(this, SPAN_SIZE);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return autoRecyclerAdapter.getSpanSize(position);
            }
        });
        recyclerView.setLayoutManager(manager);

        //set holder

        //autoRecyclerAdapter.setHolder(AutoBannerHolder.class, R.layout.item_banner);
        //autoRecyclerAdapter.setHolder(AutoTypeAHolder.class, R.layout.item_type_a);
        //autoRecyclerAdapter.setHolder(AutoTypeBHolder.class, R.layout.item_type_b);
        //autoRecyclerAdapter.setHolder(AutoTypeCHolder.class, R.layout.item_type_c);
        //autoRecyclerAdapter.setHolder(AutoTypeDHolder.class, R.layout.item_type_d);
        //autoRecyclerAdapter.setHolder(AutoTypeEHolder.class, R.layout.item_type_e);
        //autoRecyclerAdapter.setHolder(AutoTypeFHolder.class, R.layout.item_type_f);

        autoRecyclerAdapter.setHolderToListener(AutoBannerHolder.class, R.layout.item_banner, this)
            .setHolder(AutoTypeAHolder.class, R.layout.item_type_a)
            .setHolder(AutoTypeBHolder.class, R.layout.item_type_b)
            .setHolder(AutoTypeCHolder.class, R.layout.item_type_c)
            .setHolder(AutoTypeDHolder.class, R.layout.item_type_d)
            .setHolder(AutoTypeEHolder.class, R.layout.item_type_e)
            .setHolder(AutoTypeFHolder.class, R.layout.item_type_f);

        //net work request data
        List<ZhaoBean> zhaoList = ModelHelper.getZhaoList(1);
        List<QianBean> qianList = ModelHelper.getQianList(10);
        List<SunBean> sunList = ModelHelper.getSunList(1);
        List<LiBean> liList = ModelHelper.getLiList(4);
        List<ZhouBean> zhouList = ModelHelper.getZhouList(10);
        List<WuBean> wuList = ModelHelper.getWuList(1);
        List<ZhengBean> zhengList = ModelHelper.getZhengList(30);

        //set data

        //autoRecyclerAdapter.setDataListSpan(AutoBannerHolder.class, zhaoList, SPAN_SIZE);
        //autoRecyclerAdapter.setDataListSpan(AutoTypeAHolder.class, qianList, SPAN_SIZE / 5);
        //autoRecyclerAdapter.setDataListSpan(AutoTypeBHolder.class, sunList, SPAN_SIZE);
        //autoRecyclerAdapter.setDataListSpan(AutoTypeCHolder.class, liList, SPAN_SIZE / 2);
        //autoRecyclerAdapter.setDataListSpan(AutoTypeDHolder.class, zhouList, SPAN_SIZE / 5);
        //autoRecyclerAdapter.setDataListSpan(AutoTypeEHolder.class, wuList, SPAN_SIZE);
        //autoRecyclerAdapter.setDataListSpan(AutoTypeFHolder.class, zhengList, SPAN_SIZE / 2);
        //autoRecyclerAdapter.notifyDataSetChanged();

        autoRecyclerAdapter.setDataListSpan(AutoBannerHolder.class, zhaoList, SPAN_SIZE)
            .setDataListSpan(AutoTypeAHolder.class, qianList, SPAN_SIZE / 5)
            .setDataListSpan(AutoTypeBHolder.class, sunList, SPAN_SIZE)
            .setDataListSpan(AutoTypeCHolder.class, liList, SPAN_SIZE / 2)
            .setDataListSpan(AutoTypeDHolder.class, zhouList, SPAN_SIZE / 5)
            .setDataListSpan(AutoTypeEHolder.class, wuList, SPAN_SIZE)
            .setDataListSpan(AutoTypeFHolder.class, zhengList, SPAN_SIZE / 2)
            .notifyDataSetChanged();
    }

    @Override public void onAutoHolder(int position, Object bean) {
        Toast.makeText(this, "send net work request: " + position, Toast.LENGTH_SHORT).show();
    }
}
