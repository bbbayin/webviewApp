package com.example.bunny.wapapp.claz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import com.example.bunny.wapapp.R;

import java.net.URLEncoder;
import java.util.Arrays;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


public class ShareSdkHelper {
    private Activity mActivity;//checkApkIsInstalled
    private String mTitle;//标题
    private String mDescribe;//描述
    private String mLink;//链接
    private String mImage;//图片链接
    private String mIdentity;//说明
    private String mBrowserName;//浏览器的名称 mBrowserName
    private ShareInterface mInterface;//分享接口
    private String mShareUriData;
    private String UC_download_URL = "http://app4huoniu.applinzi.com/ucd.php?platform=android";//uc下载地址
    private String QQ_download_URL = "http://app4huoniu.applinzi.com/qbd.php?platform=android";//QQ 下载地址


    public ShareSdkHelper(Activity activity) {
        this.mActivity = activity;
        Resources resources = activity.getResources();
        ShareSDK.initSDK(activity, resources.getString(R.string.mob_app_key));
        ShareSDK.setPlatformDevInfo(SinaWeibo.NAME, new SinaMap(this, resources));
        ShareSDK.setPlatformDevInfo(Wechat.NAME, new WechatMap(this, resources));
        ShareSDK.setPlatformDevInfo(WechatMoments.NAME, new WechatMomentMap(this, resources));
        ShareSDK.setPlatformDevInfo(QQ.NAME, new QQMap(this, resources));
        ShareSDK.setPlatformDevInfo(QZone.NAME, new QQzoneMap(this, resources));
    }

    /**
     * 分享
     *
     * @param platform    分享的平台
     */
    private void share(String platform) {
        try {
            Uri.Builder localBuilder = Uri.parse("http://www.zbtoutiao.com" + "/shareBridge").buildUpon();
            localBuilder.appendQueryParameter("platform", platform);
            localBuilder.appendQueryParameter("bundle", this.mActivity.getPackageName());
            if (this.mTitle != null)
                localBuilder.appendQueryParameter("title", this.mTitle);
            if (this.mDescribe != null)
                localBuilder.appendQueryParameter("describe", this.mDescribe);
            if (this.mIdentity != null)
                localBuilder.appendQueryParameter("identity", this.mIdentity);
            if (this.mLink != null)
                localBuilder.appendQueryParameter("link", this.mLink);
            if (this.mImage != null)
                localBuilder.appendQueryParameter("image", this.mImage);

            mShareUriData = localBuilder.build().toString();
            Log.i("huoniu_debug", "Uri : " + mShareUriData);

            if ("uc_web".equals(this.mBrowserName)) {}

            try {
                mShareUriData = URLEncoder.encode(mShareUriData, "UTF-8");
                String tencentSchema = "com.tencent.mtt";
                String qqLaunchData = "mttbrowser://url=" + mShareUriData;
                String QQBrowserName = "手机QQ浏览器";

                if (DataEncryptHelper.checkApkIsInstalled(this.mActivity, tencentSchema)) {
                    Log.i("huoniu_debug", QQBrowserName + " is installed, launch it with:" + qqLaunchData);
                    Uri localUri = Uri.parse(qqLaunchData);
                    if (localUri != null) {
                        Intent localIntent = new Intent("android.intent.action.VIEW", localUri);
                        this.mActivity.startActivity(localIntent);
                        return;
                    }
                }

                Log.i("huoniu_debug", QQBrowserName + " is not installed, " + qqLaunchData);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.mActivity);
                alertDialog.setTitle("分享失败");
                alertDialog.setMessage("您没有安装" + QQBrowserName + ",无法分享到微信渠道!");
                alertDialog.setPositiveButton("马上安装", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(QQ_download_URL));
                        mActivity.startActivity(localIntent);
                        dialog.dismiss();
                    }
                });
                alertDialog.setNegativeButton("放弃分享", null);
                alertDialog.show();
            } catch (Exception localException2) {
                Log.e("huoniu_debug", "分享异常：" + localException2.toString());
            }
        } catch (Exception localException1) {
            localException1.printStackTrace();
            Log.e("huoniu_debug", localException1.toString());
        }
    }

    public ShareInterface getInterface() {
        return mInterface;
    }

    void setInterface(ShareInterface myInterface) {
        this.mInterface = myInterface;
    }

    /**
     * 弹出分享面板
     *
     * @param platform    分享平台
     */
    void showShareDialog(String platform) {
        if (this.mTitle == null || platform == null) return;

        if ((platform.indexOf("wechat_") == 0) && (!this.mBrowserName.equals("wx_native"))) {
            share(platform);
        } else {
            Platform.ShareParams localShareParams = new Platform.ShareParams();
            String str = "";

            while (true) {
                if (platform.equals("wechat_moments")) {
                    str = WechatMoments.NAME;
                    localShareParams.setShareType(4);
                    localShareParams.setTitle(this.mTitle);
                    localShareParams.setText(this.mDescribe);
                    localShareParams.setImageUrl(this.mImage);
                    localShareParams.setUrl(this.mLink);
                } else if (platform.equals("wechat_friend")) {
                    str = Wechat.NAME;
                    localShareParams.setShareType(4);
                    localShareParams.setTitle(this.mTitle);
                    localShareParams.setText(this.mDescribe);
                    localShareParams.setImageUrl(this.mImage);
                    localShareParams.setUrl(this.mLink);
                } else if (platform.equals("qq_mobile")) {
                    str = QQ.NAME;
                    localShareParams.setShareType(4);
                    localShareParams.setTitle(this.mTitle);
                    localShareParams.setText(this.mDescribe);
                    localShareParams.setImageUrl(this.mImage);
                    localShareParams.setUrl(this.mLink);
                } else if (platform.equals("qq_zone")) {
                    str = QZone.NAME;
                    localShareParams.setShareType(4);
                    localShareParams.setTitle(this.mTitle);
                    localShareParams.setText(this.mDescribe);
                    localShareParams.setImageUrl(this.mImage);
                    localShareParams.setUrl(this.mLink);
                    Resources localResources = this.mActivity.getResources();
                    localShareParams.setSite(localResources.getString(R.string.app_name));
                    localShareParams.setSiteUrl(localResources.getString(R.string.web_domain));
                }

                Platform localPlatform = ShareSDK.getPlatform(str);
                localPlatform.setPlatformActionListener(new MyPlatformActionListener(this, platform));
                localPlatform.share(localShareParams);
                return;
            }
        }
    }

    /**
     * 设置分享参数
     *
     * @param title
     * @param describe
     * @param urlLink
     * @param imageLink
     * @param identity
     * @param browser
     */
    void setShareParams(String title, String describe, String urlLink,
                        String imageLink, String identity, String browser) {
        mTitle = title;
        mDescribe = describe;
        mLink = urlLink;
        mImage = imageLink;
        mIdentity = identity;
        if (!Arrays.asList(new String[]{"uc_web", "qq_browser", "wx_native"}).contains(browser))
            this.mBrowserName = browser;
        else this.mBrowserName = "qq_browser";
    }
}
