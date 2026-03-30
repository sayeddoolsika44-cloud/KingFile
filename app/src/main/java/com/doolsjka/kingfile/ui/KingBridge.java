package com.doolsjka.kingfile.ui;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.webkit.JavascriptInterface;
public class KingBridge {
    private final Context ctx;
    public KingBridge(Context ctx) { this.ctx = ctx; }
    @JavascriptInterface public String getDeviceInfo() {
        return "{\"brand\":\""+Build.BRAND+"\",\"model\":\""+Build.MODEL+"\",\"android\":\""+Build.VERSION.RELEASE+"\",\"owner\":\"D\u1111\u0308oo\ua9b6\u4e02\u02b0\ua4e9\",\"app\":\"King File\",\"version\":\"7.0.0\"}";
    }
    @JavascriptInterface public void vibrate(int ms) {
        Vibrator v = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
        if (v == null) return;
        if (Build.VERSION.SDK_INT >= 26) v.vibrate(VibrationEffect.createOneShot(ms, VibrationEffect.DEFAULT_AMPLITUDE));
        else v.vibrate(ms);
    }
    @JavascriptInterface public void openUrl(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }
    @JavascriptInterface public void setFlashlight(boolean on) {
        try {
            android.hardware.camera2.CameraManager cm = (android.hardware.camera2.CameraManager)
                ctx.getSystemService(Context.CAMERA_SERVICE);
            cm.setTorchMode(cm.getCameraIdList()[0], on);
        } catch(Exception e) {}
    }
    @JavascriptInterface public String getVersion() { return "7.0.0"; }
    @JavascriptInterface public boolean isPremium() { return true; }
}
