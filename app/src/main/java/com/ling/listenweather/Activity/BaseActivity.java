package com.ling.listenweather.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ling.listenweather.util.LogUtil;

public class BaseActivity extends AppCompatActivity {
    private MyBaseActivityBroad myBaseActivityBroad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //动态注册广播
        myBaseActivityBroad = new MyBaseActivityBroad();
        IntentFilter intentFilter = new IntentFilter("drc.xxx.yyy.baseActivity");

        registerReceiver(myBaseActivityBroad, intentFilter);
    }

    public class MyBaseActivityBroad extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int closeAll = intent.getIntExtra("closeAll", 0);
            if (closeAll == 1) {
                finish();//销毁BaseActivity
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d("Super","----onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("Super","----onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("Super","----onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d("Super","----onStop");

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("Super","----onDestroy");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d("Super","----onRestart");
        unregisterReceiver(myBaseActivityBroad);//注销广播
    }

}
