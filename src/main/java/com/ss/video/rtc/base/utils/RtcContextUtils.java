package com.ss.video.rtc.base.utils;

import android.content.Context;
import org.webrtc.ContextUtils;

public class RtcContextUtils {
    private static Context applicationContext;

    public static Context getApplicationContext() {
        return ContextUtils.getApplicationContext();
    }

    public static void initialize(Context context) {
        if (context != null) {
            ContextUtils.initialize(context);
            return;
        }
        throw new IllegalArgumentException("Application context cannot be null for ContextUtils.initialize.");
    }
}
