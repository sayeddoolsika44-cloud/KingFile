package com.doolsjka.kingfile.ui;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.doolsjka.kingfile.R;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#0A0A12"));
            getWindow().setNavigationBarColor(Color.parseColor("#0A0A12"));
        }
        setContentView(R.layout.activity_splash);
        LinearLayout logo = findViewById(R.id.logoContainer);
        AlphaAnimation anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(900);
        logo.startAnimation(anim);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            boolean welcomed = getSharedPreferences(com.doolsjka.kingfile.KingApp.PREFS, MODE_PRIVATE)
                .getBoolean("welcomed", false);
            startActivity(new Intent(this, welcomed ? MainActivity.class : WelcomeActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 1800);
    }
}
