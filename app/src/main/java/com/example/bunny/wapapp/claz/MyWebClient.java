package com.example.bunny.wapapp.claz;

import android.app.ProgressDialog;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.bunny.wapapp.activity.MainActivity;

/****************************************
 * 功能说明:  
 *
 * Author: Created by bayin on 2017/7/13.
 ****************************************/

public class MyWebClient extends WebViewClient {
    private MainActivity mMainActivity;
    private ProgressDialog mProgressDialog;
    public MyWebClient(MainActivity mainActivity, ProgressDialog dialog) {
        this.mMainActivity = mainActivity;
        this.mProgressDialog = dialog;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view,url);
        this.mProgressDialog.dismiss();
    }
}
