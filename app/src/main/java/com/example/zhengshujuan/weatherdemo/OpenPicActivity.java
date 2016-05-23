package com.example.zhengshujuan.weatherdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zhengshujuan.weatherdemo.Energy.GetImageData;

import java.io.IOException;

public class OpenPicActivity extends AppCompatActivity {
    public static final String  PIC_URL="http://img0.imgtn.bdimg.com/it/u=1357415251,3416892009&fm=21&gp=0.jpg";
    public  final int LODE_PIC=1;
    private ImageView mImageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pic);
        mImageView = (ImageView) findViewById(R.id.iv_show_online);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LODE_PIC:
                    mImageView.setImageBitmap(bitmap);
                    break;
            }
        }
    };

    public void getImage(View view) {
        new Thread(){
            @Override
            public void run() {
                try {
                   byte[] pic= GetImageData.getImageData(PIC_URL);
                 bitmap= BitmapFactory.decodeByteArray(pic,0,pic.length);
                    handler.sendEmptyMessage(LODE_PIC);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();

    }
}
