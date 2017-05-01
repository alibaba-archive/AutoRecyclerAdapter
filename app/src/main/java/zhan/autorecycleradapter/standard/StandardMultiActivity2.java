package zhan.autorecycleradapter.standard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.LiBean;
import zhan.autorecycleradapter.bean.QianBean;
import zhan.autorecycleradapter.bean.SunBean;
import zhan.autorecycleradapter.bean.WuBean;
import zhan.autorecycleradapter.bean.ZhaoBean;
import zhan.autorecycleradapter.bean.ZhengBean;
import zhan.autorecycleradapter.bean.ZhouBean;
import zhan.autorecycleradapter.standard.adapter.StandardMultiAdapter;
import zhan.autorecycleradapter.standard.adapter.StandardMultiAdapter2;
import zhan.autorecycleradapter.standard.bean.LiMultiBean;
import zhan.autorecycleradapter.standard.bean.QianMultiBean;
import zhan.autorecycleradapter.standard.bean.SunMultiBean;
import zhan.autorecycleradapter.standard.bean.WuMultiBean;
import zhan.autorecycleradapter.standard.bean.ZhaoMultiBean;
import zhan.autorecycleradapter.standard.bean.ZhengMultiBean;
import zhan.autorecycleradapter.standard.bean.ZhouMultiBean;
import zhan.autorecycleradapter.utils.ModelHelper;

/**
 * Created by ruzhan on 2017/4/6.
 */

public class StandardMultiActivity2 extends AppCompatActivity {

  private StandardMultiAdapter2 adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    adapter = new StandardMultiAdapter2();
    recyclerView.setAdapter(adapter);

    //multi item need set SpanSize
    GridLayoutManager manager = new GridLayoutManager(this, StandardMultiAdapter.SPAN_SIZE);

    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        return adapter.getSpanSize(position);
      }
    });
    recyclerView.setLayoutManager(manager);

    //net work request data
    List<ZhaoBean> zhaoList = ModelHelper.getZhaoList(1);
    List<QianBean> qianList = ModelHelper.getQianList(10);
    List<SunBean> sunList = ModelHelper.getSunList(1);
    List<LiBean> liList = ModelHelper.getLiList(4);
    List<ZhouBean> zhouList = ModelHelper.getZhouList(10);
    List<WuBean> wuList = ModelHelper.getWuList(1);
    List<ZhengBean> zhengList = ModelHelper.getZhengList(30);

    //Convert bean list
    List<ZhaoMultiBean> newZhaoList =
        ZhaoMultiBean.toList(zhaoList, StandardMultiAdapter2.TYPE_ZHAO,
            StandardMultiAdapter2.SPAN_SIZE);
    List<QianMultiBean> newQianList =
        QianMultiBean.toList(qianList, StandardMultiAdapter2.TYPE_QIAN,
            StandardMultiAdapter2.SPAN_SIZE / 5);
    List<SunMultiBean> newSunList = SunMultiBean.toList(sunList, StandardMultiAdapter2.TYPE_SUN,
        StandardMultiAdapter2.SPAN_SIZE);
    List<LiMultiBean> newLiList = LiMultiBean.toList(liList, StandardMultiAdapter2.TYPE_LI,
        StandardMultiAdapter2.SPAN_SIZE / 2);
    List<ZhouMultiBean> newZhouList =
        ZhouMultiBean.toList(zhouList, StandardMultiAdapter2.TYPE_ZHOU,
            StandardMultiAdapter2.SPAN_SIZE / 5);
    List<WuMultiBean> newWuList =
        WuMultiBean.toList(wuList, StandardMultiAdapter2.TYPE_WU, StandardMultiAdapter2.SPAN_SIZE);
    List<ZhengMultiBean> newZhengList =
        ZhengMultiBean.toList(zhengList, StandardMultiAdapter2.TYPE_ZHENG,
            StandardMultiAdapter2.SPAN_SIZE / 2);

    //refresh data
    adapter.setData(newZhaoList, newQianList, newSunList, newLiList, newZhouList, newWuList,
        newZhengList);
  }
}
