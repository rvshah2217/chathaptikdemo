package com.app.chatdemo.common.networkcalls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by swapna on 4/3/17.
 */
public class NetworkStatus {
    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = null;
        try {
            cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cm.getActiveNetworkInfo();
    }

    public static boolean isConnected(Context context){
        NetworkInfo info = NetworkStatus.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }
}
