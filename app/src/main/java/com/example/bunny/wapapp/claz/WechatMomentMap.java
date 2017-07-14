package com.example.bunny.wapapp.claz;

import android.content.res.Resources;

import com.example.bunny.wapapp.R;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/12.
 */

class WechatMomentMap extends HashMap<String, Object> {

    WechatMomentMap(ShareSdkHelper helper, Resources res) {
        put("AppKey", res.getString(R.string.wechat_app_id));
        put("AppSecret", res.getString(R.string.wechat_app_secret));
        put("BypassApproval", "false");
        put("Enable", "true");
    }
}
