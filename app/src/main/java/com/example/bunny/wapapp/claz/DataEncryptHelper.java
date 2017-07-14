package com.example.bunny.wapapp.claz;

import android.content.Context;
import android.content.pm.PackageManager;

import java.security.MessageDigest;

/****************************************
 * 功能说明:  数据编码辅助类
 *
 * Author: Created by bayin on 2017/7/13.
 ****************************************/

class DataEncryptHelper {
    static String checkApkIsInstalled(String paramString, EncryptStyle paramt) {
        return checkApkIsInstalled(paramString.getBytes(), paramt);
    }

    static String checkApkIsInstalled(byte[] paramArrayOfByte) {
        char[] arrayOfChar = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
        StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
        int i = paramArrayOfByte.length;
        for (int j = 0; j < i; j++) {
            int k = paramArrayOfByte[j];
            localStringBuilder.append(arrayOfChar[((k & 0xF0) >>> 4)]);
            localStringBuilder.append(arrayOfChar[(k & 0xF)]);
        }
        return localStringBuilder.toString();
    }

    static String checkApkIsInstalled(byte[] paramArrayOfByte, EncryptStyle paramt) {
        String str1 = "";
        if (paramt == EncryptStyle.MD5)
            str1 = "MD5";
        else if (paramt == EncryptStyle.SHA_1)
            str1 = "SHA-1";
        else if (paramt == EncryptStyle.SHA_256)
            str1 = "SHA-256";
        else if (paramt == EncryptStyle.SHA_384)
            str1 = "SHA-384";
        else if (paramt == EncryptStyle.SHA_512)
            str1 = "SHA-512";
        try {
            while (true) {
                MessageDigest localMessageDigest = MessageDigest.getInstance(str1);
                localMessageDigest.update(paramArrayOfByte);
                String str2 = checkApkIsInstalled(localMessageDigest.digest());
                return str2;
            }
        } catch (Exception localException) {
        }
        return "";
    }

    /**
     * 查看手机是否安装软件
     *
     * @param context
     * @param packageName 包名
     * @return
     */
    static boolean checkApkIsInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
        }
        return false;
    }
}
