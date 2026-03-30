package com.doolsjka.kingfile.ui;
import android.net.Uri;
import android.webkit.*;
public class KingChromeClient extends WebChromeClient {
    private final MainActivity a;
    public KingChromeClient(MainActivity a) { this.a = a; }
    @Override public boolean onShowFileChooser(WebView wv, ValueCallback<Uri[]> cb, FileChooserParams p) {
        return a.handleFileChooser(cb, p);
    }
    @Override public void onGeolocationPermissionsShowPrompt(String o, GeolocationPermissions.Callback cb) {
        cb.invoke(o, true, false);
    }
    @Override public void onPermissionRequest(PermissionRequest r) { r.grant(r.getResources()); }
}
