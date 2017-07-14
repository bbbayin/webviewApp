package com.example.bunny.wapapp.claz;

import android.content.res.Resources;

import com.example.bunny.wapapp.R;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/12.
 */

public class SinaMap extends HashMap<String,Object> {

    public SinaMap(ShareSdkHelper helper, Resources res) {
        put("AppKey", res.getString(R.string.sina_app_key));
        put("AppSecret", res.getString(R.string.sina_app_secret));
        put("RedirectUrl", "");
        put("ShareByAppClient", "false");
        put("Enable", "true");
    }
}
