package zhan.autorecycleradapter.auto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import zhan.auto_adapter.AutoAdapter;
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

public class AutoMultiActivity extends AppCompatActivity {

  private static final int SPAN_SIZE = 10;

  private AutoAdapter autoAdapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    autoAdapter = new AutoAdapter();
    recyclerView.setAdapter(autoAdapter);

    //multi item need set SpanSize
    GridLayoutManager manager =
        new GridLayoutManager(this, SPAN_SIZE);

    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        return autoAdapter.getSpanSize(position);
      }
    });
    recyclerView.setLayoutManager(manager);

    //set holder

    //autoAdapter.setHolder(AutoBannerHolder.class, R.layout.item_banner);
    //autoAdapter.setHolder(AutoTypeAHolder.class, R.layout.item_type_a);
    //autoAdapter.setHolder(AutoTypeBHolder.class, R.layout.item_type_b);
    //autoAdapter.setHolder(AutoTypeCHolder.class, R.layout.item_type_c);
    //autoAdapter.setHolder(AutoTypeDHolder.class, R.layout.item_type_d);
    //autoAdapter.setHolder(AutoTypeEHolder.class, R.layout.item_type_e);
    //autoAdapter.setHolder(AutoTypeFHolder.class, R.layout.item_type_f);

    autoAdapter.setHolder(AutoBannerHolder.class, R.layout.item_banner)
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

    //autoAdapter.setDataListSpan(AutoBannerHolder.class, zhaoList, SPAN_SIZE);
    //autoAdapter.setDataListSpan(AutoTypeAHolder.class, qianList, SPAN_SIZE / 5);
    //autoAdapter.setDataListSpan(AutoTypeBHolder.class, sunList, SPAN_SIZE);
    //autoAdapter.setDataListSpan(AutoTypeCHolder.class, liList, SPAN_SIZE / 2);
    //autoAdapter.setDataListSpan(AutoTypeDHolder.class, zhouList, SPAN_SIZE / 5);
    //autoAdapter.setDataListSpan(AutoTypeEHolder.class, wuList, SPAN_SIZE);
    //autoAdapter.setDataListSpan(AutoTypeFHolder.class, zhengList, SPAN_SIZE / 2);
    //autoAdapter.notifyDataSetChanged();

    autoAdapter.setDataListSpan(AutoBannerHolder.class, zhaoList, SPAN_SIZE)
        .setDataListSpan(AutoTypeAHolder.class, qianList, SPAN_SIZE / 5)
        .setDataListSpan(AutoTypeBHolder.class, sunList, SPAN_SIZE)
        .setDataListSpan(AutoTypeCHolder.class, liList, SPAN_SIZE / 2)
        .setDataListSpan(AutoTypeDHolder.class, zhouList, SPAN_SIZE / 5)
        .setDataListSpan(AutoTypeEHolder.class, wuList, SPAN_SIZE)
        .setDataListSpan(AutoTypeFHolder.class, zhengList, SPAN_SIZE / 2)
        .notifyDataSetChanged();
  }
}
