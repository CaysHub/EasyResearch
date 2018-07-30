package com.example.easyresearch.action;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HeadImgAction {
    //调用系统的裁剪功能
    public static void cropPhoto(Activity activity, Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, 3);
    }
    public static void setPicToView(String path,Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        // 检测sd是否可用
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { return; }
        FileOutputStream b = null;File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
