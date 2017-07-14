package com.example.bunny.wapapp.claz;

import android.util.Log;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.tencent.qq.QQClientNotExistException;
import cn.sharesdk.wechat.utils.WechatClientNotExistException;

/****************************************
 * 功能说明:  
 *
 * Author: Created by bayin on 2017/7/13.
 ****************************************/

public class MyPlatformActionListener implements PlatformActionListener {
    private ShareSdkHelper mShareSdkHelper;
    private String mStr;
    public MyPlatformActionListener(ShareSdkHelper helper,String paramStr) {
        this.mShareSdkHelper = helper;
        this.mStr = paramStr;
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        platform.setPlatformActionListener(null);
        mShareSdkHelper.showShareDialog(mStr);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        platform.setPlatformActionListener(null);
        String str;
        while (true)
        {
            if ((throwable instanceof WechatClientNotExistException))
                str = "未安装微信客户端";
            else if ((throwable instanceof QQClientNotExistException))
                str = "未安装手机QQ客户端";
            else
                str = throwable.getMessage();
            Log.i("huoniu_debug", "huoniu_error", throwable);
            throwable.printStackTrace();
            mShareSdkHelper.getInterface().executeShareAction(mStr,str);
            return;
        }
    }

    @Override
    public void onCancel(Platform platform, int i) {
        platform.setPlatformActionListener(null);
        mShareSdkHelper.showShareDialog(mStr);
    }
}
