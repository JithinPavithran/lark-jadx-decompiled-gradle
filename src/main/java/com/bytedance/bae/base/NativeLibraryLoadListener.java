package com.bytedance.bae.base;

public interface NativeLibraryLoadListener {
    void onLoadAlready(String str);

    void onLoadError(String str);

    void onLoadSuccess(String str);
}
