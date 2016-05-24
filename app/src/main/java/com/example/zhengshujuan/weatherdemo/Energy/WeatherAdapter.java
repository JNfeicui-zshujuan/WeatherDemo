package com.example.zhengshujuan.weatherdemo.Energy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhengshujuan.weatherdemo.MainActivity;
import com.example.zhengshujuan.weatherdemo.R;

import java.util.List;

/**
 * Created by zhengshujuan on 2016/5/24.
 */
public class WeatherAdapter extends BaseAdapter {
    private List<WeatherConstruct> mData;
    private LayoutInflater mInflater;

    public WeatherAdapter(Context context, List<WeatherConstruct> data) {
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHole;
        if (convertView==null){
            viewHole=new ViewHold();
            convertView=mInflater.inflate(R.layout.list_detail,null);
            viewHole.mTVDate= (TextView) convertView.findViewById(R.id.tv_date_item);
            viewHole.mTVWeather= (TextView) convertView.findViewById(R.id.tv_weather_item);
            viewHole.mTVTemp= (TextView) convertView.findViewById(R.id.tv_temp_item);
            convertView.setTag(viewHole);
        }
        else {
            viewHole= (ViewHold) convertView.getTag();
        }
        WeatherConstruct weatherCon=mData.get(position);
        viewHole.mTVDate.setText(weatherCon.date);
        viewHole.mTVWeather.setText(weatherCon.weather);
        viewHole.mTVTemp.setText(weatherCon.temp);
        return convertView;
    }

    private class ViewHold {
        TextView mTVDate;
        TextView mTVWeather;
        TextView mTVTemp;
    }
}
