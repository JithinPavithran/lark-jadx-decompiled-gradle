package com.lynx.tasm;

import com.huawei.hms.android.HwBuildEx;
import com.lynx.react.bridge.ReadableMap;
import com.lynx.react.bridge.ReadableMapKeySetIterator;
import com.lynx.tasm.base.LLog;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class LynxPerfMetric {
    private static String[] PERF_POINTS = {"tasm_binary_decode", "tasm_end_decode_finish_load_template", "tasm_finish_load_template", "diff_root_create", "js_finish_load_core", "js_finish_load_app", "js_and_tasm_all_ready", "tti", "js_runtime_type", "corejs_size", "first_page_layout", "FIRST_SEP", "layout", "BOTH_SEP", "render_page", "diff_same_root", "UPDATE_SEP"};
    private static String[] PERF_STAMP_POINTS = {"init_start", "init_end", "load_template_start", "load_template_end", "decode_binary_start", "decode_binary_end", "render_template_start", "render_template_end", "diff_root_start", "diff_root_end", "layout_start", "layout_end", "load_corejs_start", "load_corejs_end", "load_appjs_start", "load_appjs_end", "start_diff", "end_diff", "update_page_start", "update_page_end"};
    private double corejs_size;
    private double diff_root_create;
    private double diff_same_root;
    private double first_page_layout;
    private double js_and_tasm_all_ready;
    private double js_finish_load_app;
    private double js_finish_load_core;
    private double js_runtime_type;
    private double layout;
    private boolean mIsColdBoot;
    private final HashMap<String, Double> mMetricMap;
    private final HashMap<String, Object> mMetricTimingMap;
    private double render_page;
    private double tasm_binary_decode;
    private double tasm_end_decode_finish_load_template;
    private double tasm_finish_load_template;
    private double tti;
    private String url;

    public double getCoreJSSize() {
        return this.corejs_size;
    }

    public double getDiffRootCreate() {
        return this.diff_root_create;
    }

    public double getDiffSameRoot() {
        return this.diff_same_root;
    }

    public double getFirsPageLayout() {
        return this.first_page_layout;
    }

    public double getJsAndTasmAllReady() {
        return this.js_and_tasm_all_ready;
    }

    public double getJsFinishLoadApp() {
        return this.js_finish_load_app;
    }

    public double getJsFinishLoadCore() {
        return this.js_finish_load_core;
    }

    public double getJsRuntimeType() {
        return this.js_runtime_type;
    }

    public double getLayout() {
        return this.layout;
    }

    public double getRenderPage() {
        return this.render_page;
    }

    public double getTasmBinaryDecode() {
        return this.tasm_binary_decode;
    }

    public double getTasmEndDecodeFinishLoadTemplate() {
        return this.tasm_end_decode_finish_load_template;
    }

    public double getTasmFinishLoadTemplate() {
        return this.tasm_finish_load_template;
    }

    public double getTti() {
        return this.tti;
    }

    public String toString() {
        return "LynxPerfMetric" + this.mMetricMap + "\nurl=" + this.url;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject(this.mMetricMap);
        try {
            String str = this.url;
            if (str != null) {
                jSONObject.put("url", str);
                jSONObject.put("cold_boot", this.mIsColdBoot);
            }
            if (this.mMetricTimingMap.size() > 0) {
                jSONObject.put("timing", new JSONObject(this.mMetricTimingMap));
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    /* access modifiers changed from: package-private */
    public void setIsColdBoot(boolean z) {
        this.mIsColdBoot = z;
    }

    private void convert(ReadableMap readableMap) {
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            fillIn(Integer.parseInt(nextKey), readableMap.getDouble(nextKey));
        }
    }

    private void convertTiming(ReadableMap readableMap) {
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            this.mMetricTimingMap.put(PERF_STAMP_POINTS[Integer.parseInt(nextKey)], readableMap.getString(nextKey));
        }
    }

    /* access modifiers changed from: package-private */
    public void correctFirstPageLayout(long j) {
        Object obj = this.mMetricTimingMap.get("load_template_start");
        if ((obj instanceof String) && j != 0) {
            try {
                long longValue = j - Long.valueOf((String) obj).longValue();
                if (longValue > 0) {
                    double d = (double) longValue;
                    if (d < this.first_page_layout) {
                        this.first_page_layout = d;
                        this.mMetricMap.put("first_page_layout", Double.valueOf(d));
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setInitTiming(long j, long j2) {
        this.mMetricTimingMap.put("init_start", Long.valueOf(j));
        this.mMetricTimingMap.put("init_end", Long.valueOf(j2));
    }

    private void fillIn(int i, double d) {
        switch (i) {
            case 0:
                this.tasm_binary_decode = d;
                break;
            case 1:
                this.tasm_end_decode_finish_load_template = d;
                break;
            case 2:
                this.tasm_finish_load_template = d;
                break;
            case 3:
                this.diff_root_create = d;
                break;
            case 4:
                this.js_finish_load_core = d;
                break;
            case 5:
                this.js_finish_load_app = d;
                break;
            case 6:
                this.js_and_tasm_all_ready = d;
                break;
            case 7:
                this.tti = d;
                break;
            case 8:
                this.js_runtime_type = d;
                break;
            case 9:
                this.corejs_size = d;
                break;
            case 10:
                this.first_page_layout = d;
                break;
            case 11:
            case 13:
            default:
                LLog.m96429e("lynx_LynxPerfMetric", "no such perf key=" + i);
                return;
            case HwBuildEx.VersionCodes.EMUI_5_1:
                this.layout = d;
                break;
            case 14:
                this.render_page = d;
                break;
            case 15:
                this.diff_same_root = d;
                break;
        }
        this.mMetricMap.put(PERF_POINTS[i], Double.valueOf(d));
    }

    public LynxPerfMetric(ReadableMap readableMap, ReadableMap readableMap2, String str) {
        this.url = str;
        this.mMetricMap = new HashMap<>(readableMap.size());
        this.mMetricTimingMap = new HashMap<>(readableMap2.size());
        convert(readableMap);
        convertTiming(readableMap2);
    }
}
