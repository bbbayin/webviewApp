package com.example.bunny.wapapp.claz;

import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;

/****************************************
 * 功能说明:  
 *
 * Author: Created by bayin on 2017/7/13.
 ****************************************/

public class MyHandlerCallback implements Handler.Callback {
    private ShareSdkHelper mShareSdkHelper;
    private WebView mWebView;
    public MyHandlerCallback(ShareSdkHelper helper, WebView webView) {
        this.mShareSdkHelper = helper;
        this.mWebView = webView;
    }

    @Override
    public boolean handleMessage(Message msg) {
        String str = msg.getData().getString("rawCode");
        this.mWebView.loadUrl("javascript:" + str);
        return true;
    }
}
