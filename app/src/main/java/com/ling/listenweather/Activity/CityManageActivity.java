package com.ling.listenweather.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.ling.listenweather.Adapter.SelectedCityAdapter;
import com.ling.listenweather.Model.CountyChosen;
import com.ling.listenweather.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CityManageActivity extends BaseActivity {


    private List<CountyChosen> list = new ArrayList<>();

    private SelectedCityAdapter selectedCityAdapter;
    @BindView(R.id.chosenCityListRv)
    RecyclerView chosenCityListRv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage);
         ButterKnife.bind(this);
         toolbar.setTitle("城市管理");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         selectedCityAdapter = new SelectedCityAdapter(this,list);
         chosenCityListRv.setLayoutManager(new LinearLayoutManager(this));
         chosenCityListRv.setAdapter(selectedCityAdapter);

         queryCityFDb();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CityManageActivity.this,SearchCityActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    private void queryCityFDb() {
        list.clear();
        list.addAll(DataSupport.findAll(CountyChosen.class));
        selectedCityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case 1: if (resultCode == RESULT_OK){

                CountyChosen countyChosen  = (CountyChosen) data.getSerializableExtra("countyChosen");

                countyChosen.save();
                queryCityFDb();
            }

        }
    }
}
