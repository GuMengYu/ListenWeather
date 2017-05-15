package com.ling.listenweather.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ling.listenweather.Api.Api;
import com.ling.listenweather.Api.ApiImpl;
import com.ling.listenweather.Model.WeatherData.Weather;
import com.ling.listenweather.R;
import com.ling.listenweather.View.widget.IndexListView;
import com.ling.listenweather.util.HandleResponseUtil;
import com.ling.listenweather.util.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;
import okhttp3.Call;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.locationTv)
    TextView locationTv;
    @BindView(R.id.updateTimeTv)
    TextView updateTimeTv;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.container_weather_trendView)
    IndexListView indexListView;
    @BindView(R.id.temLineChart)
    LineChartView mLineChartView;//线性图表控件

    private SharedPreferences sharedPreferences;
    private Boolean isExit = false; //是否退出状态

    /*=========== 数据相关 ==========*/
    private LineChartData mLineChartData;//图表数据
    private int numberofLines = 2;//图上折线/曲线数
    private int maxNumberOfLines = 2;//最多折线/曲线数
    private int numberOfPoints = 7 ; //图上的节点数

     /*=========== 状态相关 ==========*/
    private boolean isHasAxes = false;                   //是否显示坐标轴
    private boolean isHasAxesNames = false;              //是否显示坐标轴名称
    private boolean isHasLines = true;                  //是否显示折线/曲线
    private boolean isHasPoints = true;                 //是否显示线上的节点
    private boolean isFilled = false;                   //是否填充线下方区域
    private boolean isHasPointsLabels = false;          //是否显示节点上的标签信息
    private boolean isCubic = false;                    //是否是立体的
    private boolean isPointsHasSelected = false;        //设置节点点击后效果(消失/显示标签)
    private boolean isPointsHaveDifferentColor;         //节点是否有不同的颜色

    private ValueShape pointsShape = ValueShape.CIRCLE; //点的形状(圆/方/菱形)
    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints]; //将线上的点放在一个数组中


    private List<Map<String,Object>> list = new ArrayList<>();

    private Weather weather;



    ApiImpl api = new ApiImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initComponent();
        initData();
    }

    private void initData() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String id = sharedPreferences.getString("weatherId","北京");

        api.GetWeather(id, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {

                Weather weather= HandleResponseUtil.handleWeatherResponse(response);

                indexListView.setData(weather);
                updateTimeTv.setText(weather.basic.update.LocUpdateTime);
                locationTv.setText(weather.basic.cityName);
                String title = weather.basic.cityName+" " +weather.now.temperature+"'C "+weather.now.more.info;
                collapsing_toolbar.setTitle(title);


            }
        });

        api.GetPicture(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                String url = HandleResponseUtil.handlePictureResponse(response);
                Glide.with(MainActivity.this).load(url).into(image);
            }
        });



    }

    private void initComponent() {
        setSupportActionBar(toolbar);
        collapsing_toolbar.setTitle("重庆");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mLineChartView.setViewportCalculationEnabled(false);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.city_manage) {
            Intent intent = new Intent(this,CityManageActivity.class);
            startActivity(intent);

         } else if (id == R.id.setting) {

        } else if (id == R.id.about) {

            Intent intent = new Intent(this,AboutActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * 方法简述：重写返回键点击行为
     *@ parameter
     *@ return
     *@ author Administrator
     *@ Time 2017/3/8 16:43
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            ExitBy2Click();
        }
        return true;
    }

    /**
     * 方法简述： 双击退出程序
     *
     * @ return
     * @ parameter
     * @ author Administrator
     * @ Time 2017/3/3 14:15
     */
    public void ExitBy2Click() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            Intent intent = new Intent("drc.xxx.yyy.baseActivity");
            intent.putExtra("closeAll", 1);
            sendBroadcast(intent);//发送广播
        }
    }

}
