package com.doolsjka.kingfile.ui;
import android.webkit.*;
public class KingWebViewClient extends WebViewClient {
    @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r) { return false; }
    @Override public boolean shouldOverrideUrlLoading(WebView v, String url) { return false; }
}
