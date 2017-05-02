package zhan.autorecycleradapter.auto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import zhan.auto_adapter.AutoRecyclerAdapter;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.auto.holder.AutoSingleHolder;
import zhan.autorecycleradapter.bean.ZhaoBean;
import zhan.autorecycleradapter.utils.ModelHelper;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class AutoSingleActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    AutoRecyclerAdapter autoRecyclerAdapter = new AutoRecyclerAdapter();
    recyclerView.setAdapter(autoRecyclerAdapter);

    LinearLayoutManager manager =
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(manager);
    recyclerView.setHasFixedSize(true);

    //set holder
    autoRecyclerAdapter.setHolder(AutoSingleHolder.class, R.layout.item_single);

    //net work request data
    List<ZhaoBean> list = ModelHelper.getZhaoList(30);

    autoRecyclerAdapter.setDataList(AutoSingleHolder.class, list).notifyDataSetChanged();
  }
}
