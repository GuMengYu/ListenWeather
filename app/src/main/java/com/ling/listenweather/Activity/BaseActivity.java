package com.ling.listenweather.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ling.listenweather.util.LogUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }

}
