package com.facebook.react.modules.accessibilityinfo;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

@ReactModule(name = "AccessibilityInfo")
public class AccessibilityInfoModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private final ContentObserver animationScaleObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
        /* class com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule.C211521 */

        public void onChange(boolean z) {
            onChange(z, null);
        }

        public void onChange(boolean z, Uri uri) {
            if (AccessibilityInfoModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                AccessibilityInfoModule.this.updateAndSendReduceMotionChangeEvent();
            }
        }
    };
    private AccessibilityManager mAccessibilityManager;
    private final ContentResolver mContentResolver;
    private boolean mReduceMotionEnabled = false;
    private boolean mTouchExplorationEnabled = false;
    private accessibilityAccessibilityManager$TouchExplorationStateChangeListenerC21153a mTouchExplorationStateChangeListener;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AccessibilityInfo";
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        getReactApplicationContext().removeLifecycleEventListener(this);
    }

    private boolean getIsReduceMotionEnabledValue() {
        String str;
        if (Build.VERSION.SDK_INT < 17) {
            str = null;
        } else {
            str = Settings.Global.getString(this.mContentResolver, "transition_animation_scale");
        }
        if (str == null || !str.equals("0.0")) {
            return false;
        }
        return true;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
        updateAndSendTouchExplorationChangeEvent(this.mAccessibilityManager.isTouchExplorationEnabled());
        updateAndSendReduceMotionChangeEvent();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mAccessibilityManager.removeTouchExplorationStateChangeListener(this.mTouchExplorationStateChangeListener);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            this.mContentResolver.unregisterContentObserver(this.animationScaleObserver);
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mAccessibilityManager.addTouchExplorationStateChangeListener(this.mTouchExplorationStateChangeListener);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            this.mContentResolver.registerContentObserver(Settings.Global.getUriFor("transition_animation_scale"), false, this.animationScaleObserver);
        }
        updateAndSendTouchExplorationChangeEvent(this.mAccessibilityManager.isTouchExplorationEnabled());
        updateAndSendReduceMotionChangeEvent();
    }

    public void updateAndSendReduceMotionChangeEvent() {
        boolean isReduceMotionEnabledValue = getIsReduceMotionEnabledValue();
        if (this.mReduceMotionEnabled != isReduceMotionEnabledValue) {
            this.mReduceMotionEnabled = isReduceMotionEnabledValue;
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("reduceMotionDidChange", Boolean.valueOf(this.mReduceMotionEnabled));
        }
    }

    /* renamed from: com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule$a  reason: invalid class name */
    private class accessibilityAccessibilityManager$TouchExplorationStateChangeListenerC21153a implements AccessibilityManager.TouchExplorationStateChangeListener {
        private accessibilityAccessibilityManager$TouchExplorationStateChangeListenerC21153a() {
        }

        public void onTouchExplorationStateChanged(boolean z) {
            AccessibilityInfoModule.this.updateAndSendTouchExplorationChangeEvent(z);
        }
    }

    @ReactMethod
    public void isReduceMotionEnabled(Callback callback) {
        callback.invoke(Boolean.valueOf(this.mReduceMotionEnabled));
    }

    @ReactMethod
    public void isTouchExplorationEnabled(Callback callback) {
        callback.invoke(Boolean.valueOf(this.mTouchExplorationEnabled));
    }

    public void updateAndSendTouchExplorationChangeEvent(boolean z) {
        if (this.mTouchExplorationEnabled != z) {
            this.mTouchExplorationEnabled = z;
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("touchExplorationDidChange", Boolean.valueOf(this.mTouchExplorationEnabled));
        }
    }

    @ReactMethod
    public void announceForAccessibility(String str) {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        if (accessibilityManager != null && accessibilityManager.isEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
            obtain.getText().add(str);
            obtain.setClassName(AccessibilityInfoModule.class.getName());
            obtain.setPackageName(getReactApplicationContext().getPackageName());
            this.mAccessibilityManager.sendAccessibilityEvent(obtain);
        }
    }

    public AccessibilityInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mAccessibilityManager = (AccessibilityManager) reactApplicationContext.getApplicationContext().getSystemService("accessibility");
        this.mContentResolver = getReactApplicationContext().getContentResolver();
        this.mTouchExplorationEnabled = this.mAccessibilityManager.isTouchExplorationEnabled();
        this.mReduceMotionEnabled = getIsReduceMotionEnabledValue();
        if (Build.VERSION.SDK_INT >= 19) {
            this.mTouchExplorationStateChangeListener = new accessibilityAccessibilityManager$TouchExplorationStateChangeListenerC21153a();
        }
    }
}
