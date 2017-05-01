package zhan.autorecycleradapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import zhan.autorecycleradapter.auto.AutoMultiActivity;
import zhan.autorecycleradapter.auto.AutoSingleActivity;
import zhan.autorecycleradapter.standard.StandardSingleActivity;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public static void launch(Context context, Class clazz) {
    Intent intent = new Intent(context, clazz);
    context.startActivity(intent);
  }

  public void standardSingleItem(View view) {
    launch(this, StandardSingleActivity.class);
  }

  public void standardMultiItem(View view) {
    //launch(this, StandardMultiActivity.class);
  }

  public void autoSingleItem(View view) {
    launch(this, AutoSingleActivity.class);
  }

  public void autoMultiItem(View view) {
    launch(this, AutoMultiActivity.class);
  }
}
