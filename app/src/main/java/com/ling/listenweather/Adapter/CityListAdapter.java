package com.ling.listenweather.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ling.listenweather.Model.City;
import com.ling.listenweather.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class CityListAdapter extends BaseAdapter{


    private List<City> mList ;

    private Context mContext;

    public CityListAdapter(Context context , List<City> list){
        mList =list;
        mContext=context;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view==null){
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.city_list_item,null);
            holder.county = (TextView)view.findViewById(R.id.countyTv);
            holder.city = (TextView)view.findViewById(R.id.cityTv);
            holder.province = (TextView)view.findViewById(R.id.provinceTv);

            view.setTag(holder);

        }else {
            holder = (ViewHolder)view.getTag();
        }

        holder.county.setText(mList.get(i).getCityZn());
        holder.city.setText(mList.get(i).getLeaderZh());
        holder.province.setText(mList.get(i).getProvinceZh());
        return view;
    }


}

    class ViewHolder {
        public TextView county;
        public TextView city;
        public TextView province;
}
