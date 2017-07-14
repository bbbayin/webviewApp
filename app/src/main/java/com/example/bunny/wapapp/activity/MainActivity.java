package com.example.bunny.wapapp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.bunny.wapapp.R;
import com.example.bunny.wapapp.claz.JsInterface;
import com.example.bunny.wapapp.claz.MyDialogCancleListener;
import com.example.bunny.wapapp.claz.MyWebChromeClient;
import com.example.bunny.wapapp.claz.MyWebClient;
import com.example.bunny.wapapp.claz.ShareInterface;
import com.example.bunny.wapapp.claz.ShareSdkHelper;

public class MainActivity extends Activity {
    private String HomeUrl = "http://www.zbtoutiao.com";
    private WebView webView;//a
    private ShareSdkHelper mShareSdkHelper;//b
    private JsInterface mJsInterface;//c

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            PackageInfo localPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = localPackageInfo.versionName;
            String versionCode = localPackageInfo.versionCode + "";

            webView = (WebView) findViewById(R.id.webview);
            webView.loadUrl(HomeUrl);
            webView.requestFocus();

            this.mShareSdkHelper = new ShareSdkHelper(this);

            this.mJsInterface = new JsInterface(this, webView, mShareSdkHelper);

            this.webView.addJavascriptInterface(this.mJsInterface, "HuoNiuApp");

            WebSettings localWebSettings = this.webView.getSettings();
            localWebSettings.setAllowFileAccess(true);
            localWebSettings.setAppCacheEnabled(true);
            localWebSettings.setSupportMultipleWindows(false);
            localWebSettings.setLoadWithOverviewMode(true);
            localWebSettings.setDatabaseEnabled(true);
            localWebSettings.setDomStorageEnabled(true);
            localWebSettings.setGeolocationEnabled(true);
            localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            localWebSettings.setSaveFormData(false);
            localWebSettings.setJavaScriptEnabled(true);

            Object[] arrayOfObject = new Object[3];
            arrayOfObject[0] = localWebSettings.getUserAgentString();
            arrayOfObject[1] = versionName;
            arrayOfObject[2] = versionCode;
            localWebSettings.setUserAgentString(String.format("%s HuoNiuApp/%s_%s", arrayOfObject));
            ProgressDialog localProgressDialog = ProgressDialog.show(this, null, "正在加载页面...");
            localProgressDialog.setCanceledOnTouchOutside(false);
            localProgressDialog.setCancelable(true);

            localProgressDialog.setOnCancelListener(new MyDialogCancleListener(this));

            this.webView.setWebChromeClient(new MyWebChromeClient(this));
            this.webView.setWebViewClient(new MyWebClient(this, localProgressDialog));
            return;

        } catch (Exception e) {
            Toast.makeText(this, "读取设置项出错!", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if ((paramInt == 4) && (this.webView.canGoBack())) {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    /**
     * @param paramIntent
     */
    protected void onNewIntent(Intent paramIntent) {
        super.onNewIntent(paramIntent);
        Uri localUri = paramIntent.getData();
        Log.i("huoniu_debug", "receive intent:" + paramIntent.getDataString());
        ShareInterface shareInterface;
        String target;
        String state;
        String message;
        if ((localUri != null) && ("share_bridge_return".equals(localUri.getHost()))) {
            shareInterface = this.mShareSdkHelper.getInterface();
            if (shareInterface != null) {
                target = localUri.getQueryParameter("target");
                state = localUri.getQueryParameter("state");
                message = localUri.getQueryParameter("message");
                if ("success".equals(state))
                    shareInterface.cancelShareAction(target);
                else if ("cancel".equals(state))
                    shareInterface.cancelShareAction(target);
                else if ("fail".equals(state))
                    shareInterface.executeShareAction(target, message);
            }
        }
    }
}
