package com.example.bunny.wapapp.claz;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import org.json.JSONObject;

/****************************************
 * 功能说明:  拦截js事件，并调用Android本地方法
 *
 * Author: Created by bayin on 2017/7/13.
 ****************************************/
public class JsInterface {
    private Activity mActivity;//checkApkIsInstalled
    public Handler mHandler;//c
    private ShareSdkHelper mShareSdkHelper;//class f, b

    public JsInterface(Activity activity, WebView webView, ShareSdkHelper shareSdkHelper) {
        mActivity = activity;
        mHandler = new Handler(new MyHandlerCallback(shareSdkHelper,webView));
        mShareSdkHelper = shareSdkHelper;
    }

    @JavascriptInterface
    public void configShareParams(String paramString)
    {
        Log.i("huoniu_debug", "config share params:" + paramString);
        try
        {
            JSONObject localJSONObject = new JSONObject(paramString);
            String title = localJSONObject.getString("title");
            String desc = localJSONObject.getString("desc");
            String link = localJSONObject.getString("link");
            String image = localJSONObject.getString("image");
            String identity = localJSONObject.getString("identity");
            String bridge = localJSONObject.getString("bridge");
            String onSuccess = localJSONObject.getString("onSuccess");
            String onCancel = localJSONObject.getString("onCancel");
            String onFail = localJSONObject.getString("onFail");
            mShareSdkHelper.setShareParams(title, desc, link, image, identity, bridge);
            mShareSdkHelper.setInterface(new MyInterfaceImpl(this, onSuccess, onCancel, onFail));
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
            Log.i("huoniu_debug", "config share params error:" + localException.getMessage());
        }
    }


    @JavascriptInterface
    public void openSharePlatform(String paramString)
    {
        Log.i("huoniu_debug", "open share platform:" + paramString);
        this.mShareSdkHelper.showShareDialog(paramString);
    }
}
