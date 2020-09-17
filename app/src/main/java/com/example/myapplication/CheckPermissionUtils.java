package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * @description:
 * @author: ZhouCong
 * @email: 13328023455@163.com
 * @date :   2020/7/16 8:53 AM
 */
public final class CheckPermissionUtils {
    private CheckPermissionUtils() {
    }

    //需要申请的权限
    private static String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    //检测权限
    public static String[] checkPermission(Context context){
        List<String> data = new ArrayList<>();//存储未申请的权限
        for (String permission : permissions) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
            if(checkSelfPermission == PackageManager.PERMISSION_DENIED){//未申请
                data.add(permission);
            }
        }
        return data.toArray(new String[data.size()]);
    }
}
