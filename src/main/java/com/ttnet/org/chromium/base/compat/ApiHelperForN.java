package com.ttnet.org.chromium.base.compat;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.view.PointerIcon;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class ApiHelperForN {
    private ApiHelperForN() {
    }

    public static void setPointerIcon(View view, PointerIcon pointerIcon) {
        view.setPointerIcon(pointerIcon);
    }

    public static JobInfo getPendingJob(JobScheduler jobScheduler, int i) {
        return jobScheduler.getPendingJob(i);
    }

    public static void setVrModeEnabled(Activity activity, boolean z, ComponentName componentName) throws PackageManager.NameNotFoundException {
        activity.setVrModeEnabled(z, componentName);
    }

    public static PointerIcon createPointerIcon(Bitmap bitmap, float f, float f2) {
        return PointerIcon.create(bitmap, f, f2);
    }

    public static boolean shouldOverrideUrlLoading(WebViewClient webViewClient, WebView webView, WebResourceRequest webResourceRequest) {
        return webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    public static void setCryptoInfoPattern(MediaCodec.CryptoInfo cryptoInfo, int i, int i2) {
        cryptoInfo.setPattern(new MediaCodec.CryptoInfo.Pattern(i, i2));
    }

    public static boolean startDragAndDrop(View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i) {
        return view.startDragAndDrop(clipData, dragShadowBuilder, obj, i);
    }
}
