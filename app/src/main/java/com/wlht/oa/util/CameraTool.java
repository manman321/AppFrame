package com.wlht.oa.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.widget.ImageView;

import com.wlht.oa.AppConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by hr on 15/7/12.
 */
public class CameraTool {
    public final static int CAMERA_REQUEST = 8888;
    public final static int PHOTO_REQUEST = 8889;
    public final static int CORP_REQUEST = 8890;
    private String mPhotoPath;
    private String cameraCaptureFileName;

    protected Activity mContext;

    public CameraTool(Activity context)
    {
        mContext = context;
    }

    public void camera() {
        cameraCaptureFileName = String.format("IMG_%s.jpg", DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)));
//                "IMG_"+ DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";
        mPhotoPath = String.format("%s%s", AppConfig.CAMERA_PATH, cameraCaptureFileName);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mPhotoPath)));
        intent.putExtra("return-data", false);
        File file = new File(mPhotoPath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("scale", true);
        intent.putExtra("crop", "true");
        mContext.startActivityForResult(intent, CAMERA_REQUEST);// 1是requestCode

    }


    public void photo()
    {
        cameraCaptureFileName = String.format("IMG_%s.jpg", DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)));
        mPhotoPath = String.format("%s%s", AppConfig.CAMERA_PATH, cameraCaptureFileName);
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        File file = new File(mPhotoPath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.setType("image/*");
        intent.putExtra("scale", true);
        intent.putExtra("crop", "true");
        intent.putExtra("return-data", false);
        mContext.startActivityForResult(intent, PHOTO_REQUEST);
    }


    public void showImage(final ImageView imageView) {
        imageView.setPadding(0, 0, 0, 0);
        imageView.setBackgroundColor(Color.TRANSPARENT);
        String filePath = mPhotoPath;

        if (mPhotoPath.contains("file://")) {
            filePath = mPhotoPath.replace("file://", "");
        }
//        imageView.setTag(filePath);
//        if (!mPhotoPath.startsWith("file://")) {
//            filePath = String.format("file://%s", mPhotoPath);
//        }

        Bitmap bitmap = decodeSampledBitmapFromFile(filePath,400,400);
        String path = saveBitmap(bitmap);
        imageView.setTag(path);
        imageView.setImageBitmap(bitmap);

//        Picasso.with(mContext)
//                .load(filePath)
//                .into(imageView);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
        // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
        // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
        // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }



    public static Bitmap decodeSampledBitmapFromFile(String path,
                                                     int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        // 调用上面定义的方法计算inSampleSize值
        int height = options.outHeight;
        int width = options.outWidth;
        if (width >= height)//宽图
        {
            reqHeight = height * reqWidth / width ;
        }else//长图
        {
            reqWidth = width * reqHeight / height;
        }
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        Bitmap newBitmap = ThumbnailUtils.extractThumbnail(bitmap, reqWidth, reqWidth);
        bitmap.recycle();
        return newBitmap;
    }

    /** 保存方法 */
    public String saveBitmap(Bitmap bitmap) {
        String fileNewName = String.format("%s%s", AppConfig.CAMERA_PATH, "avtar.png");
        File f = new File(fileNewName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return fileNewName;
    }




}
