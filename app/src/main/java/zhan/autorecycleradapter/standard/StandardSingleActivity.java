package zhan.autorecycleradapter.standard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import zhan.autorecycleradapter.R;
import zhan.autorecycleradapter.bean.ZhaoBean;
import zhan.autorecycleradapter.standard.adapter.StandardSingleAdapter;
import zhan.autorecycleradapter.utils.ModelHelper;

/**
 * Created by ruzhan on 2017/4/6.
 */

public class StandardSingleActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    StandardSingleAdapter adapter = new StandardSingleAdapter();
    recyclerView.setAdapter(adapter);

    LinearLayoutManager manager =
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(manager);
    recyclerView.setHasFixedSize(true);

    //net work request data
    List<ZhaoBean> list = ModelHelper.getZhaoList(30);

    //refresh data
    adapter.setData(list);
  }
}
