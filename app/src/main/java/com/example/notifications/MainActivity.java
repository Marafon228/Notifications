package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SoundPool.OnLoadCompleteListener {

    private SoundPool mSoundPool;
    private int mClapSound, mVoiceSound;

    public static final int NOTIFICATION_ID = 1;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        mSoundPool.setOnLoadCompleteListener(this);


    }
    @SuppressWarnings("deprecation")
    public void ClickButtonImage(View view) {
        mSoundPool.play(mVoiceSound, 1,1,0,0,1);
        mSoundPool.play(mClapSound,1,1,0,0,1);
        Resources res = this.getResources();
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setLargeIcon(BitmapFactory.decodeResource(res,R.drawable.ic_launcher_foreground))
                        .setAutoCancel(true)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setContentTitle("Колокольчик")
                        .setProgress(0,0,true)
                        .setDefaults(NotificationCompat.DEFAULT_LIGHTS)
                        .setDefaults(NotificationCompat.DEFAULT_SOUND)
                        .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                        .setContentText("Колокольчик звенит, колокользик зовёт");
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService (Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        //mSoundPool.stop();
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

    }
}