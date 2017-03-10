package com.app.chatdemo.common;

/**
 * Created by swapna on 4/3/17.
 */

public class Constants {

    public static String BASE_URL = "http://haptik.mobi/android/test_data/";

    public static final class HomeAdapterRowViewType {
        public static final int USER = 1;
        public static final int OTHERS = 2;
    }

    public interface HeaderKeys {
        String API_VERSION_CODE = "X-VERSION-CODE";
        String X_CLIENT = "X-Client";
        String CONTENT_TYPE = "Content-Type";
        String CACHE_CONTROL = "Cache-Control";
    }

    public interface AppConstants {
        String PLATFORM = "Android";
        String CONTENT_FORMAT = "application/json";
        String NO_CACHE = "no-cache";
    }
}
