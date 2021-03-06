package com.huawei.hms.adapter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.adapter.internal.CommonCode;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.adapter.sysobs.SystemObserver;
import com.huawei.hms.adapter.ui.BaseResolutionAdapter;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.PendingResultImpl;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.kpms.KpmsConstant;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.hms.utils.Util;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class BaseAdapter {
    private static final String TAG = "BaseAdapter";
    private WeakReference<Activity> activityWeakReference;
    private WeakReference<ApiClient> api;
    public Context appContext;
    private BaseCallBack baseCallBackReplay;
    private BaseCallBack callback;
    private String jsonHeaderReplay;
    private String jsonObjectReplay;
    private SystemObserver observer;
    private Parcelable parcelableReplay;
    private RequestHeader requestHeader = new RequestHeader();
    public ResponseHeader responseHeader = new ResponseHeader();
    public String transactionId;

    public interface BaseCallBack {
        void onComplete(String str, String str2, Parcelable parcelable);

        void onError(String str);
    }

    private BaseCallBack getBaseCallBackReplay() {
        return this.baseCallBackReplay;
    }

    private String getJsonHeaderReplay() {
        return this.jsonHeaderReplay;
    }

    private String getJsonObjectReplay() {
        return this.jsonObjectReplay;
    }

    private Parcelable getParcelableReplay() {
        return this.parcelableReplay;
    }

    private void initObserver() {
        this.observer = new SystemObserver() {
            /* class com.huawei.hms.adapter.BaseAdapter.C234391 */

            @Override // com.huawei.hms.adapter.sysobs.SystemObserver
            public boolean onUpdateResult(int i) {
                return false;
            }

            @Override // com.huawei.hms.adapter.sysobs.SystemObserver
            public boolean onSolutionResult(Intent intent, String str) {
                if (TextUtils.isEmpty(str)) {
                    HMSLog.m86964e(BaseAdapter.TAG, "onSolutionResult but id is null");
                    BaseCallBack callBack = BaseAdapter.this.getCallBack();
                    if (callBack == null) {
                        HMSLog.m86964e(BaseAdapter.TAG, "onSolutionResult baseCallBack null");
                        return true;
                    }
                    callBack.onError(BaseAdapter.this.getResponseHeaderForError(-6));
                    return true;
                } else if (!str.equals(BaseAdapter.this.transactionId)) {
                    return false;
                } else {
                    HMSLog.m86966i(BaseAdapter.TAG, "onSolutionResult + id is :" + str);
                    BaseCallBack callBack2 = BaseAdapter.this.getCallBack();
                    if (callBack2 == null) {
                        HMSLog.m86964e(BaseAdapter.TAG, "onResult baseCallBack null");
                        return true;
                    }
                    long j = 0;
                    if (intent == null) {
                        HMSLog.m86964e(BaseAdapter.TAG, "onSolutionResult but data is null");
                        String responseHeaderForError = BaseAdapter.this.getResponseHeaderForError(-7);
                        BaseAdapter baseAdapter = BaseAdapter.this;
                        baseAdapter.biReportRequestReturnSolution(baseAdapter.appContext, BaseAdapter.this.responseHeader, 0);
                        callBack2.onError(responseHeaderForError);
                        return true;
                    } else if (intent.hasExtra(KpmsConstant.KIT_UPDATE_RESULT)) {
                        int intExtra = intent.getIntExtra(KpmsConstant.KIT_UPDATE_RESULT, 0);
                        HMSLog.m86966i(BaseAdapter.TAG, "kit_update_result is " + intExtra);
                        if (intExtra == 1) {
                            HMSLog.m86964e(BaseAdapter.TAG, "kit update success,replay request");
                            BaseAdapter.this.replayRequest();
                        } else {
                            HMSLog.m86964e(BaseAdapter.TAG, "kit update failed");
                            callBack2.onError(BaseAdapter.this.getResponseHeaderForError(-10));
                        }
                        return true;
                    } else {
                        HMSLog.m86964e(BaseAdapter.TAG, "onComplete for on activity result");
                        String stringExtra = intent.getStringExtra(CommonCode.MapKey.JSON_HEADER);
                        String stringExtra2 = intent.getStringExtra(CommonCode.MapKey.JSON_BODY);
                        Object infoFromJsonobject = JsonUtil.getInfoFromJsonobject(stringExtra, "status_code");
                        Object infoFromJsonobject2 = JsonUtil.getInfoFromJsonobject(stringExtra, "error_code");
                        if (intent.hasExtra(CommonCode.MapKey.HMS_FOREGROUND_RES_UI)) {
                            Object infoFromJsonobject3 = JsonUtil.getInfoFromJsonobject(intent.getStringExtra(CommonCode.MapKey.HMS_FOREGROUND_RES_UI), "uiDuration");
                            if (infoFromJsonobject3 instanceof Long) {
                                j = ((Long) infoFromJsonobject3).longValue();
                            }
                        }
                        if (!(infoFromJsonobject instanceof Integer) || !(infoFromJsonobject2 instanceof Integer)) {
                            BaseAdapter.this.getResponseHeaderForError(-8);
                            BaseAdapter baseAdapter2 = BaseAdapter.this;
                            baseAdapter2.biReportRequestReturnSolution(baseAdapter2.appContext, BaseAdapter.this.responseHeader, j);
                        } else {
                            int intValue = ((Integer) infoFromJsonobject).intValue();
                            BaseAdapter.this.getResponseHeaderForError(((Integer) infoFromJsonobject2).intValue());
                            BaseAdapter.this.responseHeader.setStatusCode(intValue);
                            BaseAdapter baseAdapter3 = BaseAdapter.this;
                            baseAdapter3.biReportRequestReturnSolution(baseAdapter3.appContext, BaseAdapter.this.responseHeader, j);
                        }
                        callBack2.onComplete(stringExtra, stringExtra2, null);
                        return true;
                    }
                }
            }
        };
    }

    public BaseCallBack getCallBack() {
        BaseCallBack baseCallBack = this.callback;
        if (baseCallBack != null) {
            return baseCallBack;
        }
        HMSLog.m86964e(TAG, "callback null");
        return null;
    }

    public void replayRequest() {
        if (this.jsonHeaderReplay != null && this.baseCallBackReplay != null) {
            this.responseHeader = null;
            this.responseHeader = new ResponseHeader();
            baseRequest(getJsonHeaderReplay(), getJsonObjectReplay(), getParcelableReplay(), getBaseCallBackReplay());
        }
    }

    public Activity getCpActivity() {
        if (this.activityWeakReference == null) {
            HMSLog.m86966i(TAG, "activityWeakReference is " + this.activityWeakReference);
            return null;
        }
        ApiClient apiClient = this.api.get();
        if (apiClient == null) {
            HMSLog.m86966i(TAG, "tmpApi is " + apiClient);
            return null;
        }
        HMSLog.m86966i(TAG, "activityWeakReference has " + this.activityWeakReference.get());
        return Util.getActiveActivity(this.activityWeakReference.get(), apiClient.getContext());
    }

    private void setBaseCallBackReplay(BaseCallBack baseCallBack) {
        this.baseCallBackReplay = baseCallBack;
    }

    private void setJsonHeaderReplay(String str) {
        this.jsonHeaderReplay = str;
    }

    private void setJsonObjectReplay(String str) {
        this.jsonObjectReplay = str;
    }

    private void setParcelableReplay(Parcelable parcelable) {
        this.parcelableReplay = parcelable;
    }

    public class BaseRequestResultCallback implements ResultCallback<ResolveResult<CoreBaseResponse>> {
        private AtomicBoolean isFirstRsp = new AtomicBoolean(true);

        public BaseRequestResultCallback() {
        }

        public void onResult(ResolveResult<CoreBaseResponse> resolveResult) {
            BaseCallBack callBack = BaseAdapter.this.getCallBack();
            if (callBack == null) {
                HMSLog.m86964e(BaseAdapter.TAG, "onResult baseCallBack null");
            } else if (resolveResult == null) {
                HMSLog.m86964e(BaseAdapter.TAG, "result null");
                callBack.onError(BaseAdapter.this.getResponseHeaderForError(-1));
            } else {
                CoreBaseResponse value = resolveResult.getValue();
                if (value == null) {
                    HMSLog.m86964e(BaseAdapter.TAG, "response null");
                    callBack.onError(BaseAdapter.this.getResponseHeaderForError(-1));
                } else if (TextUtils.isEmpty(value.getJsonHeader())) {
                    HMSLog.m86964e(BaseAdapter.TAG, "jsonHeader null");
                    callBack.onError(BaseAdapter.this.getResponseHeaderForError(-1));
                } else {
                    JsonUtil.jsonToEntity(value.getJsonHeader(), BaseAdapter.this.responseHeader);
                    if (this.isFirstRsp.compareAndSet(true, false)) {
                        BaseAdapter baseAdapter = BaseAdapter.this;
                        baseAdapter.biReportRequestReturnIpc(baseAdapter.appContext, BaseAdapter.this.responseHeader);
                    }
                    if ("intent".equals(BaseAdapter.this.responseHeader.getResolution())) {
                        Activity cpActivity = BaseAdapter.this.getCpActivity();
                        HMSLog.m86966i(BaseAdapter.TAG, "activity is " + cpActivity);
                        if (cpActivity == null || cpActivity.isFinishing()) {
                            HMSLog.m86964e(BaseAdapter.TAG, "activity null");
                            handleSolutionIntent(callBack, value);
                            return;
                        }
                        PendingIntent pendingIntent = value.getPendingIntent();
                        if (pendingIntent == null) {
                            Intent intent = value.getIntent();
                            if (intent == null) {
                                HMSLog.m86964e(BaseAdapter.TAG, "hasResolution is true but NO_SOLUTION");
                                callBack.onError(BaseAdapter.this.getResponseHeaderForError(-4));
                            } else if (Util.isAvailableLibExist(BaseAdapter.this.appContext)) {
                                BaseAdapter.this.startResolution(cpActivity, intent);
                            } else {
                                callBack.onError(BaseAdapter.this.getResponseHeaderForError(-9));
                            }
                        } else if (Util.isAvailableLibExist(BaseAdapter.this.appContext)) {
                            BaseAdapter.this.startResolution(cpActivity, pendingIntent);
                        } else {
                            callBack.onError(BaseAdapter.this.getResponseHeaderForError(-9));
                        }
                    } else {
                        handleSolutionIntent(callBack, value);
                    }
                }
            }
        }

        private void handleSolutionIntent(BaseCallBack baseCallBack, CoreBaseResponse coreBaseResponse) {
            HMSLog.m86966i(BaseAdapter.TAG, "baseCallBack.onComplete");
            PendingIntent pendingIntent = coreBaseResponse.getPendingIntent();
            if (pendingIntent != null) {
                baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), pendingIntent);
                return;
            }
            Intent intent = coreBaseResponse.getIntent();
            if (intent != null) {
                baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), intent);
            } else {
                baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), null);
            }
        }
    }

    /* access modifiers changed from: private */
    public static class MPendingResultImpl extends PendingResultImpl<ResolveResult<CoreBaseResponse>, CoreBaseResponse> {
        public ResolveResult<CoreBaseResponse> onComplete(CoreBaseResponse coreBaseResponse) {
            ResolveResult<CoreBaseResponse> resolveResult = new ResolveResult<>(coreBaseResponse);
            resolveResult.setStatus(Status.SUCCESS);
            return resolveResult;
        }

        public MPendingResultImpl(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }
    }

    public BaseAdapter(ApiClient apiClient) {
        this.api = new WeakReference<>(apiClient);
    }

    public String getResponseHeaderForError(int i) {
        this.responseHeader.setTransactionId(this.requestHeader.getTransactionId());
        this.responseHeader.setAppID(this.requestHeader.getAppID());
        this.responseHeader.setApiName(this.requestHeader.getApiName());
        this.responseHeader.setSrvName(this.requestHeader.getSrvName());
        this.responseHeader.setPkgName(this.requestHeader.getPkgName());
        this.responseHeader.setStatusCode(1);
        this.responseHeader.setErrorCode(i);
        this.responseHeader.setErrorReason("Core error");
        return this.responseHeader.toJson();
    }

    public BaseAdapter(ApiClient apiClient, Activity activity) {
        this.api = new WeakReference<>(apiClient);
        this.activityWeakReference = new WeakReference<>(activity);
        this.appContext = activity.getApplicationContext();
    }

    private void biReportRequestEntryIpc(Context context, RequestHeader requestHeader2) {
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getInstance().getMapFromRequestHeader(requestHeader2);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.REQUEST);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(requestHeader2.getKitSdkVersion())));
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_PHONETYPE, Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_CALL_AIDL, mapFromRequestHeader);
    }

    private void biReportRequestEntrySolution(Context context, RequestHeader requestHeader2) {
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getInstance().getMapFromRequestHeader(requestHeader2);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.REQUEST);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(requestHeader2.getKitSdkVersion())));
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_PHONETYPE, Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_START_RESOLUTION, mapFromRequestHeader);
    }

    public void biReportRequestReturnIpc(Context context, ResponseHeader responseHeader2) {
        HiAnalyticsUtil.getInstance();
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getMapFromRequestHeader(responseHeader2);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.RESPONSE);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(this.requestHeader.getKitSdkVersion())));
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_PHONETYPE, Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_CALL_AIDL, mapFromRequestHeader);
    }

    public void startResolution(Activity activity, Parcelable parcelable) {
        HMSLog.m86966i(TAG, "startResolution");
        RequestHeader requestHeader2 = this.requestHeader;
        if (requestHeader2 != null) {
            biReportRequestEntrySolution(this.appContext, requestHeader2);
        }
        if (this.observer == null) {
            initObserver();
        }
        SystemManager.getSystemNotifier().registerObserver(this.observer);
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(activity, BaseResolutionAdapter.class.getName());
        Bundle bundle = new Bundle();
        bundle.putParcelable(CommonCode.MapKey.HAS_RESOLUTION, parcelable);
        intentStartBridgeActivity.putExtras(bundle);
        intentStartBridgeActivity.putExtra(CommonCode.MapKey.TRANSACTION_ID, this.transactionId);
        activity.startActivity(intentStartBridgeActivity);
    }

    private PendingResult<ResolveResult<CoreBaseResponse>> baseRequest(ApiClient apiClient, String str, CoreBaseRequest coreBaseRequest) {
        return new MPendingResultImpl(apiClient, str, coreBaseRequest);
    }

    public void biReportRequestReturnSolution(Context context, ResponseHeader responseHeader2, long j) {
        HiAnalyticsUtil.getInstance();
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getMapFromRequestHeader(responseHeader2);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.RESPONSE);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_WAITTIME, String.valueOf(j));
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(this.requestHeader.getKitSdkVersion())));
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_PHONETYPE, Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_START_RESOLUTION, mapFromRequestHeader);
    }

    private void setReplayData(String str, String str2, Parcelable parcelable, BaseCallBack baseCallBack) {
        setJsonHeaderReplay(str);
        setJsonObjectReplay(str2);
        setParcelableReplay(parcelable);
        setBaseCallBackReplay(baseCallBack);
    }

    public void baseRequest(String str, String str2, Parcelable parcelable, BaseCallBack baseCallBack) {
        setReplayData(str, str2, parcelable, baseCallBack);
        if (this.api == null) {
            HMSLog.m86964e(TAG, "client is null");
            baseCallBack.onError(getResponseHeaderForError(-2));
            return;
        }
        this.callback = baseCallBack;
        JsonUtil.jsonToEntity(str, this.requestHeader);
        CoreBaseRequest coreBaseRequest = new CoreBaseRequest();
        coreBaseRequest.setJsonObject(str2);
        coreBaseRequest.setJsonHeader(str);
        coreBaseRequest.setParcelable(parcelable);
        String apiName = this.requestHeader.getApiName();
        if (TextUtils.isEmpty(apiName)) {
            HMSLog.m86964e(TAG, "get uri null");
            baseCallBack.onError(getResponseHeaderForError(-5));
            return;
        }
        String transactionId2 = this.requestHeader.getTransactionId();
        this.transactionId = transactionId2;
        if (TextUtils.isEmpty(transactionId2)) {
            HMSLog.m86964e(TAG, "get transactionId null");
            baseCallBack.onError(getResponseHeaderForError(-6));
            return;
        }
        HMSLog.m86966i(TAG, "in baseRequest + uri is :" + apiName + ", transactionId is : " + this.transactionId);
        biReportRequestEntryIpc(this.appContext, this.requestHeader);
        baseRequest(this.api.get(), apiName, coreBaseRequest).setResultCallback(new BaseRequestResultCallback());
    }
}
