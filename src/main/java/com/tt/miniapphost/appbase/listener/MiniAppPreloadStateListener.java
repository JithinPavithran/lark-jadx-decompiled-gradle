package com.tt.miniapphost.appbase.listener;

public interface MiniAppPreloadStateListener {
    void onPreloadCancel(String str);

    void onPreloadFail(String str);

    void onPreloadFinish(String str, boolean z, long j);

    void onPreloadProgress(String str, int i);

    void onPreloadResume(String str);

    void onPreloadStart(String str);

    void onPreloadStop(String str);
}
