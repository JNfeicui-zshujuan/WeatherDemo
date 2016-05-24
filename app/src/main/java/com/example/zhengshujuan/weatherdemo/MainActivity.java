package com.example.zhengshujuan.weatherdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhengshujuan.weatherdemo.Energy.WeatherAdapter;
import com.example.zhengshujuan.weatherdemo.Energy.WeatherConstruct;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    TextView mTvTemp;
    TextView mTvData;
    TextView mTvQuality;
    ImageView mImageView;
    TextView mTvMostTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<WeatherConstruct> WeatherData=new ArrayList<>();
for (int i=0;i<15;i++){
    WeatherData.add(new WeatherConstruct("日期"+(i+1),"温度   30","天气    晴"));
}
        mListView= (ListView) findViewById(R.id.lv_show);
        mListView.setAdapter(new WeatherAdapter(MainActivity.this,WeatherData) );

    }
}
