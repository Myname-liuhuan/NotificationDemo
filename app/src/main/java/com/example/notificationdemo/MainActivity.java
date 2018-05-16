package com.example.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button01 = (Button)findViewById(R.id.button01);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,MainActivity2.class);
                PendingIntent pi= PendingIntent.getActivities(MainActivity.this,0, new Intent[]{intent},0);//当启动准备跳转到Avtivity时，就getActivity，与此类似的，还有getBroadcast(),getService()方法
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//Manager的目的是管理通知
                int id = 1; // 通知渠道的id
//                String CHANNEL_ID = "my_channel_01"; //Android8.0的新变化，增加了通知渠道这个概念
                Notification notification = new Notification.Builder(MainActivity.this)
                        .setContentTitle("New Message")
                        .setContentText("You've received new messages.")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setShowWhen(true)//7.0以上的的版本使用setShowWhen来显示时间，setWhen已经过时
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setFullScreenIntent(pi,true)//设置横幅
                        .setDefaults(NotificationCompat.DEFAULT_ALL)//默认通知效果涉及三个方面sound，Vibrate，Light
                        .build();
                manager.notify(id,notification);//发布通知
            }
        });
    }
}
