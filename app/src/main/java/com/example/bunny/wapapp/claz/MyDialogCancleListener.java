package com.example.bunny.wapapp.claz;

import android.content.DialogInterface;

import com.example.bunny.wapapp.activity.MainActivity;

/****************************************
 * 功能说明:  
 *
 * Author: Created by bayin on 2017/7/13.
 ****************************************/

public class MyDialogCancleListener implements DialogInterface.OnCancelListener {
    private MainActivity mActivity;
    public MyDialogCancleListener(MainActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        // TODO: 2017/7/13 MainActivity.checkApkIsInstalled(this.checkApkIsInstalled).stopLoading();
    }
}
