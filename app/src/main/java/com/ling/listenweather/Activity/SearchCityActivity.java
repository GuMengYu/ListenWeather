package com.ling.listenweather.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ling.listenweather.Adapter.CityListAdapter;
import com.ling.listenweather.Api.Api;
import com.ling.listenweather.Api.ApiImpl;
import com.ling.listenweather.Model.City;
import com.ling.listenweather.Model.CountyChosen;
import com.ling.listenweather.R;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class SearchCityActivity extends BaseActivity {


    ApiImpl api = new ApiImpl();
    @BindView(R.id.city_nameEt)
    SearchView   city_nameEt;
    @BindView(R.id.result_cityLv)
    ListView result_cityLv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private CityListAdapter cityListAdapter;
    List<City>  mCityList= new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        ButterKnife.bind(this);

        toolbar.setTitle("搜索城市");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (DataSupport.findFirst(City.class)==null) {
            querySaveCity();
        }


        cityListAdapter =  new CityListAdapter(this,mCityList);
        result_cityLv.setAdapter(cityListAdapter);

        result_cityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CountyChosen countyChosen = new CountyChosen();
                countyChosen.setWeatherId(mCityList.get(i).getWeatherId());
                countyChosen.setCountyName(mCityList.get(i).getCityZn());
                countyChosen.setCityName(mCityList.get(i).getLeaderZh());
                countyChosen.setProvinceName(mCityList.get(i).getProvinceZh());
                Intent intent = new Intent(SearchCityActivity.this,CityManageActivity.class);
                intent.putExtra("countyChosen",countyChosen);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
        city_nameEt.setIconifiedByDefault(true);
        city_nameEt.setIconified(true);
        city_nameEt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mCityList.clear();

                if (!TextUtils.isEmpty(query)){

                    mCityList.addAll(DataSupport.where("cityZn like ?" ,"%"+query+"%").find(City.class));

                    cityListAdapter.notifyDataSetChanged();

                }

                Toast.makeText(SearchCityActivity.this,query,Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mCityList.clear();
                if (!TextUtils.isEmpty(newText)){

                    mCityList.addAll(DataSupport.where("cityZn like ?" ,"%"+newText+"%").find(City.class));

                    cityListAdapter.notifyDataSetChanged();

                }else {
                    mCityList.clear();
                    cityListAdapter.notifyDataSetChanged();
                }
                Toast.makeText(SearchCityActivity.this,newText,Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }

    private void querySaveCity() {

        api.QueryCitys(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                List<Map<String,Object>>  list = new ArrayList();
                Gson gson = new Gson();
                if (response!=null && !response.equals("")) {
                    list = gson.fromJson(response,new TypeToken<List<Map<String,Object>>>(){}.getType());

                    for (Map map : list){

                        City city = new City();
                        city.setWeatherId(map.get("id").toString());
                        city.setCityEn(map.get("cityEn").toString());
                        city.setCityZn(map.get("cityZh").toString());
                        city.setCountryCode(map.get("countryCode").toString());
                        city.setCountryEn(map.get("countryEn").toString());
                        city.setCountryZh(map.get("countryZh").toString());
                        city.setProvinceEn(map.get("provinceEn").toString());
                        city.setProvinceZh(map.get("provinceZh").toString());
                        city.setLeaderEn(map.get("leaderEn").toString());
                        city.setLeaderZh(map.get("leaderZh").toString());
                        city.setLat(map.get("lat").toString());
                        city.setLon(map.get("lon").toString());
                        city.save();
                    }

                }
            }
        });
    }
}
