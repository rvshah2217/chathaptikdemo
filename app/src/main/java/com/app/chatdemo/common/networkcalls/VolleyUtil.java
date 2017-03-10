package com.app.chatdemo.common.networkcalls;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.app.chatdemo.BuildConfig;

/**
 * Created by swapna on 4/3/17.
 */
public class VolleyUtil {
    private static final String TAG = "VolleyUtil";
    private static VolleyUtil sInstance;
    private RequestQueue mRequestQueue;
     ImageLoader mImageLoader;

    private VolleyUtil(final Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });


    }

    public static synchronized VolleyUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VolleyUtil(context);
        }
        return sInstance;
    }

    private VolleyUtil() {
    }


    public <T> void addToRequestQueue(final Request<T> req) {


        mRequestQueue.add(req);
        if (BuildConfig.DEBUG) {
            mRequestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                @Override
                public void onRequestFinished(Request<Object> request) {
                }
            });
        }
    }



}
