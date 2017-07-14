package com.example.bunny.wapapp.claz;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.bunny.wapapp.activity.MainActivity;

/****************************************
 * 功能说明:  
 *
 * Author: Created by bayin on 2017/7/13.
 ****************************************/

public class MyWebChromeClient extends WebChromeClient {
    private MainActivity mMainActivity;

    public MyWebChromeClient(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }

    private void showFileChooser(ValueCallback callback1,ValueCallback callback2){


        // TODO: 2017/7/13 这两个方法有待猜测

//        MainActivity.checkApkIsInstalled(mMainActivity, paramValueCallback1);
//        MainActivity.b(this.this$0, paramValueCallback2);

        Intent localIntent1 = new Intent("android.intent.action.GET_CONTENT");
        localIntent1.addCategory("android.intent.category.OPENABLE");
        localIntent1.setType("*/*");
        Intent localIntent2 = Intent.createChooser(localIntent1, "选择上传的文件");
        mMainActivity.startActivityForResult(localIntent2, 0);
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        Log.i("huoniu_debug", "file upload callback (Android 5.0 (API level 21) -- current)");
        showFileChooser(null, filePathCallback);
        return true;
    }

    public void openFileChooser(ValueCallback paramValueCallback)
    {
        Log.i("huoniu_debug", "file upload callback (Android 2.2 (API level 8) -- Android 2.3 (API level 10))");
        showFileChooser(paramValueCallback, null);
    }

    public void openFileChooser(ValueCallback paramValueCallback, String paramString)
    {
        Log.i("huoniu_debug", "file upload callback (Android 3.0 (API level 11) -- Android 4.0 (API level 15))");
        showFileChooser(paramValueCallback, null);
    }

    public void openFileChooser(ValueCallback paramValueCallback, String paramString1, String paramString2)
    {
        Log.i("huoniu_debug", "file upload callback (Android 4.1 (API level 16) -- Android 4.4 (API level 19))");
        showFileChooser(paramValueCallback, null);
    }
}
