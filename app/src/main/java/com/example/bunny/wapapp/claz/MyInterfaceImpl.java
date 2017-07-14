package com.example.bunny.wapapp.claz;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

/****************************************
 * 功能说明:  分享接口的实现类
 *
 * Author: Created by bayin on 2017/7/13.
 ****************************************/

public class MyInterfaceImpl implements ShareInterface {
    private JsInterface mJsInterface;
    private String shareParam1;
    private String shareParam2;
    private String shareParam3;

    public MyInterfaceImpl(JsInterface jsInterface, String paramString1, String paramString2, String paramString3) {
        this.mJsInterface = jsInterface;
        shareParam1 = paramString1;
        shareParam2 = paramString2;
        shareParam3 = paramString3;
    }

    /**
     * @param shareTarget 分享终端
     */
    @Override
    public void executeShareAction(String shareTarget) {
        Log.i("huoniu_debug", "success share:" + shareTarget);
        Message localMessage = new Message();
        Bundle localBundle = new Bundle();
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = this.mJsInterface;
        arrayOfObject[1] = shareTarget;
        localBundle.putString("rawCode", String.format("javascript:%s(\"%s\", null)", arrayOfObject));
        localMessage.setData(localBundle);
        mJsInterface.mHandler.sendMessage(localMessage);
    }

    /**
     * @param shareTarget  分享终端
     * @param shareMessage 消息
     */
    @Override
    public void executeShareAction(String shareTarget, String shareMessage) {
        Log.i("huoniu_debug", "fail share:" + shareTarget + "," + shareMessage);
        Message localMessage = new Message();
        Bundle localBundle = new Bundle();
        String str = shareMessage.replace("\"", "\\\"");
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = this.shareParam1;
        arrayOfObject[1] = shareTarget;
        arrayOfObject[2] = str;
        localBundle.putString("rawCode", String.format("javascript:%s(\"%s\", \"%s\")", arrayOfObject));
        localMessage.setData(localBundle);
        mJsInterface.mHandler.sendMessage(localMessage);
    }

    /**
     * @param shareTarget 分享终端
     */
    @Override
    public void cancelShareAction(String shareTarget) {
        Log.i("huoniu_debug", "cancel share:" + shareTarget);
        Message localMessage = new Message();
        Bundle localBundle = new Bundle();
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = this.shareParam2;
        arrayOfObject[1] = shareTarget;
        localBundle.putString("rawCode", String.format("javascript:%s(\"%s\")", arrayOfObject));
        localMessage.setData(localBundle);
        mJsInterface.mHandler.sendMessage(localMessage);
    }
}
