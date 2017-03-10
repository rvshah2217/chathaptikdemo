package com.app.chatdemo;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by swapna on 4/3/17.
 */
public class ChatApplication extends Application {

    private static final String TAG = "ChatApplication";

    // FOR IMAGE LOADER
    private static final long MAX_SIZE = 1000000L;//1 MB

    public static int GENERAL_TRACKER = 0;

   private static ImageLoader imageLoader;


    @Override
    public void onCreate() {

        super.onCreate();

        //==================FOR UNIVERSAL IMAGE LOADER==================================
        try {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.ARGB_8888)
                    /*.showImageOnFail(R.drawable.ic_placeholder)
                    .showImageOnLoading(R.drawable.ic_placeholder)*/
                    .build();


            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                    .denyCacheImageMultipleSizesInMemory()
                    .defaultDisplayImageOptions(options)
                    .memoryCache(new LruMemoryCache(4 * 1024 * 1024))
                    .memoryCacheSize(4 * 1024 * 1024)
                    .memoryCacheSizePercentage(25)
                    .writeDebugLogs()
                    .threadPriority(Thread.NORM_PRIORITY - 1) // default
                    .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                    .denyCacheImageMultipleSizesInMemory()
                    .writeDebugLogs()
                    .build();


            imageLoader = ImageLoader.getInstance();
            imageLoader.init(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //==================end FOR UNIVERSAL IMAGE LOADER==================================

    }
    public static ImageLoader getImageLoader() {
        if (imageLoader == null) {
            imageLoader = ImageLoader.getInstance();
        }
        return imageLoader;

    }


}
