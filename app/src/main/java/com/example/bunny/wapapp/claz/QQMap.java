package com.example.bunny.wapapp.claz;

import android.content.res.Resources;

import com.example.bunny.wapapp.R;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/12.
 */

class QQMap extends HashMap<String,Object> {

    QQMap(ShareSdkHelper helper, Resources res) {
        put("AppId", res.getString(R.string.tencent_app_id));
        put("AppKey", res.getString(R.string.tencent_app_key));
        put("ShareByAppClient", "true");
        put("Enable", "true");
    }
}
