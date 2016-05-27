package com.example.zhengshujuan.weatherdemo;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhengshujuan.weatherdemo.Energy.WeatherAdapter;
import com.example.zhengshujuan.weatherdemo.Energy.WeatherConstruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    ListView mListView;
    TextView mTvTemp;
    TextView mTvData;
    TextView mTvQuality;
    ImageView mImageView;
    TextView mTvMostTemp;
    private String weatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<WeatherConstruct> WeatherData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            WeatherData.add(new WeatherConstruct("日期" + (i + 1), "温度   30", "天气    晴"));
        }
        mListView = (ListView) findViewById(R.id.lv_show);
        mListView.setAdapter(new WeatherAdapter(MainActivity.this, WeatherData));
        new Thread() {
            @Override
            public void run() {
                try {
                    getIntnetAddress();
                    Log.d(TAG, "run: 0000000000000000000000000000000000000000000");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "run: 异常");
                }
                handler.sendEmptyMessage(1);
            }
        }.start();
//        try {
//            GetWeatherData(weatherData);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "run: 00000000000000000000000000000000000000000001");
            switch (msg.what) {
                case 1:
                    try {
                        GetWeatherData(weatherData);
                        Log.d(TAG, "run: 00000000000000000000000000000000000000000020");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "handleMessage: jbfjckfhnvn");


                    break;

            }

        }
    };


    //获取网络地址的方法
    public void getIntnetAddress() throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        final String HTTP = "http://api.openweathermap.org/data/2.5/weather?q=jinank&appid=7630ceb776cce9179876749d6a56d302";
        //统一资源标识符
        Uri builtUri = Uri.parse(HTTP).buildUpon().build();
        //    统一资源定位器
        URL url = new URL(builtUri.toString());
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        //输入流获取请求内容
        InputStream inputStream = urlConnection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
            // Nothing to do.
            Log.d(TAG, "getIntnetAddress: nhdoaigvn");
            return;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
            // But it does make debugging a *lot* easier if you print out the completed
            // buffer for debugging.
            buffer.append(line + "\n");
        }
        if (buffer.length() == 0) {
            // Stream was empty.  No point in parsing.
            return;
        }
        weatherData = buffer.toString();
        Log.d(TAG, "getIntnetAddress: " + weatherData);
    }

    public void GetWeatherData(String jsn) throws JSONException {
        Log.d(TAG, "GetWeatherData: 走到这里");
        JSONObject jsonObject = new JSONObject(jsn);
        Log.d(TAG, "GetWeatherData: " + jsonObject);
        JSONObject coord = jsonObject.getJSONObject("coord");
        Log.d(TAG, "GetWeatherData: " + coord);
        double lon = coord.getDouble("lon");
        Log.d(TAG, "GetWeatherData: " + lon);

    }


}
