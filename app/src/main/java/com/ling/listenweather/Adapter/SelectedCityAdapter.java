package com.ling.listenweather.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ling.listenweather.Model.CountyChosen;
import com.ling.listenweather.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/8.
 */

public class SelectedCityAdapter extends RecyclerView.Adapter<SelectedCityAdapter.MyViewHolder> implements View.OnClickListener{


    private Context mContext;
    private List<CountyChosen> mData;

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    @Override
    public void onClick(View view) {
        if (onRecyclerViewItemClickListener!=null){
            onRecyclerViewItemClickListener.onItemClick(view,(String) view.getTag());
        }
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener){
        this.onRecyclerViewItemClickListener = listener;
    }
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,String data);
    }

    public SelectedCityAdapter(Context context,List<CountyChosen> list){
        this.mContext = context;
        this.mData =list;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.chosen_city_list_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

         view.setOnClickListener(this);
         return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.countyTv.setText(mData.get(position).getCountyName());
        holder.itemView.setTag(mData.get(position).getWeatherId());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.countyTv)
        TextView countyTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
