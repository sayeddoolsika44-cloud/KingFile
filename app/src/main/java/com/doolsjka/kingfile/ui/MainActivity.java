package com.doolsjka.kingfile.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.*;
import android.widget.FrameLayout;
import android.Manifest;
import android.content.pm.PackageManager;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
    private WebView webView;
    private ValueCallback<Uri[]> fileChooserCb;
    static final int FILE_REQ = 101;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#0A0A12"));
            getWindow().setNavigationBarColor(Color.parseColor("#0A0A12"));
        }
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        webView = new WebView(this);
        FrameLayout layout = new FrameLayout(this);
        layout.setBackgroundColor(Color.parseColor("#0F0F1C"));
        layout.addView(webView, new FrameLayout.LayoutParams(-1,-1));
        setContentView(layout);
        setupWebView();
        webView.loadUrl("file:///android_asset/app.html");
    }

    @SuppressLint("SetJavaScriptEnabled")
    void setupWebView() {
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowFileAccessFromFileURLs(true);
        s.setAllowUniversalAccessFromFileURLs(true);
        s.setAllowContentAccess(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setSupportZoom(true);
        s.setBuiltInZoomControls(true);
        s.setDisplayZoomControls(false);
        s.setGeolocationEnabled(true);
        s.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        s.setUserAgentString("KingFile/7.0 D\u1111\u0308oo\ua9b6\u4e02\u02b0\ua4e9");
        if (Build.VERSION.SDK_INT >= 19) WebView.setWebContentsDebuggingEnabled(false);
        webView.setWebViewClient(new KingWebViewClient());
        webView.setWebChromeClient(new KingChromeClient(this));
        webView.addJavascriptInterface(new KingBridge(this), "KingNative");
    }

    boolean handleFileChooser(ValueCallback<Uri[]> cb, WebChromeClient.FileChooserParams p) {
        fileChooserCb = cb;
        try { startActivityForResult(p.createIntent(), FILE_REQ); return true; }
        catch(Exception e) { fileChooserCb = null; return false; }
    }

    @Override
    protected void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (req == FILE_REQ && fileChooserCb != null) {
            Uri[] results = null;
            if (res == RESULT_OK && data != null) {
                if (data.getClipData() != null) {
                    int n = data.getClipData().getItemCount();
                    results = new Uri[n];
                    for (int i=0;i<n;i++) results[i]=data.getClipData().getItemAt(i).getUri();
                } else if (data.getData() != null) results = new Uri[]{data.getData()};
            }
            fileChooserCb.onReceiveValue(results);
            fileChooserCb = null;
        }
    }

    @Override public void onBackPressed() { if (webView.canGoBack()) webView.goBack(); }
    @Override protected void onPause() { super.onPause(); webView.onPause(); }
    @Override protected void onResume() { super.onResume(); webView.onResume(); }
    @Override protected void onDestroy() { webView.destroy(); super.onDestroy(); }
}
