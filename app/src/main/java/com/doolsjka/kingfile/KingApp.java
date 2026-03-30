package com.doolsjka.kingfile;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
public class KingApp extends Application {
    public static final String PREFS = "kf_prefs";
    @Override public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(new NotificationChannel("kf_media","وسائط",NotificationManager.IMPORTANCE_LOW));
        }
    }
}
