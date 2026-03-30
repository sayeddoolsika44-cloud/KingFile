package com.doolsjka.kingfile.ui;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import com.doolsjka.kingfile.R;

public class WelcomeActivity extends Activity {
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
        setContentView(R.layout.activity_welcome);

        TextView owner = findViewById(R.id.ownerName);
        AnimationSet a1 = new AnimationSet(true);
        a1.addAnimation(new AlphaAnimation(0f,1f){{setDuration(900);setStartOffset(300);}});
        a1.addAnimation(new TranslateAnimation(0,0,-50,0){{setDuration(900);setStartOffset(300);}});
        owner.startAnimation(a1);

        TextView title = findViewById(R.id.appTitle);
        AnimationSet a2 = new AnimationSet(true);
        a2.addAnimation(new AlphaAnimation(0f,1f){{setDuration(800);setStartOffset(600);}});
        a2.addAnimation(new TranslateAnimation(0,0,-30,0){{setDuration(800);setStartOffset(600);}});
        title.startAnimation(a2);

        Button btn = findViewById(R.id.startBtn);
        AlphaAnimation a3 = new AlphaAnimation(0f,1f);
        a3.setDuration(700); a3.setStartOffset(1000); a3.setFillAfter(true);
        btn.startAnimation(a3);

        btn.setOnClickListener(v -> {
            getSharedPreferences(com.doolsjka.kingfile.KingApp.PREFS, MODE_PRIVATE)
                .edit().putBoolean("welcomed", true).apply();
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        });
    }
}
