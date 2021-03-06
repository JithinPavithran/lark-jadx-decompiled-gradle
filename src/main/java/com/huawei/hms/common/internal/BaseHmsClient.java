package com.huawei.hms.common.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.adapter.BinderAdapter;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.IPCTransport;
import com.huawei.hms.common.PackageConstants;
import com.huawei.hms.core.aidl.AbstractC23528d;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.client.AidlApiClient;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.core.DisconnectResp;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.manager.PackageNameManager;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.Util;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseHmsClient implements AidlApiClient {
    private static final int BINDING = 5;
    private static final int CONNECTED = 3;
    private static final int DISCONNECTED = 1;
    private static final int DISCONNECTING = 4;
    private static final Object LOCK_CONNECT_TIMEOUT_HANDLER = new Object();
    private static final int MSG_CONN_TIMEOUT = 2;
    private static final String TAG = "BaseHmsClient";
    private String mAppID;
    public BinderAdapter mBinderAdapter;
    public final ClientSettings mClientSettings;
    private final AtomicInteger mConnStatus = new AtomicInteger(1);
    private Handler mConnectTimeoutHandler = null;
    public final ConnectionCallbacks mConnectionCallbacks;
    private final OnConnectionFailedListener mConnectionFailedListener;
    public final Context mContext;
    public volatile AbstractC23528d mService;
    protected String sessionId;

    public interface ConnectionCallbacks {
        public static final int CAUSE_API_CLIENT_EXPIRED = 3;
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected();

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    public int getMinApkVersion() {
        return 30000000;
    }

    public String getServiceAction() {
        return "com.huawei.hms.core.aidlservice";
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getAppID() {
        return this.mAppID;
    }

    /* access modifiers changed from: protected */
    public ClientSettings getClientSettings() {
        return this.mClientSettings;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public Context getContext() {
        return this.mContext;
    }

    @Override // com.huawei.hms.support.api.client.AidlApiClient
    public AbstractC23528d getService() {
        return this.mService;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getSessionId() {
        return this.sessionId;
    }

    public void onConnecting() {
        connectionConnected();
    }

    @Override // com.huawei.hms.support.api.client.AidlApiClient
    public List<String> getApiNameList() {
        return this.mClientSettings.getApiName();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getCpID() {
        return this.mClientSettings.getCpID();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getPackageName() {
        return this.mClientSettings.getClientPackageName();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public SubAppInfo getSubAppInfo() {
        return this.mClientSettings.getSubAppID();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getTransportName() {
        return IPCTransport.class.getName();
    }

    /* access modifiers changed from: protected */
    public final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    /* access modifiers changed from: protected */
    public final void connectionConnected() {
        setConnectStatus(3);
        ConnectionCallbacks connectionCallbacks = this.mConnectionCallbacks;
        if (connectionCallbacks != null) {
            connectionCallbacks.onConnected();
        }
    }

    public boolean isConnecting() {
        if (this.mConnStatus.get() == 5) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public class VoidOnConnectionFailed implements PackageNameManager.QueryPublishCallback {
        private int errorCode;

        @Override // com.huawei.hms.update.manager.PackageNameManager.QueryPublishCallback
        public void onQueryResult() {
            BaseHmsClient.this.notifyFailed(new ConnectionResult(26, HuaweiApiAvailability.getInstance().getErrPendingIntent(BaseHmsClient.this.mContext, this.errorCode, 0)));
        }

        public VoidOnConnectionFailed(int i) {
            this.errorCode = i;
        }
    }

    private void cancelConnDelayHandle() {
        synchronized (LOCK_CONNECT_TIMEOUT_HANDLER) {
            Handler handler = this.mConnectTimeoutHandler;
            if (handler != null) {
                handler.removeMessages(2);
                this.mConnectTimeoutHandler = null;
            }
        }
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public boolean isConnected() {
        if (this.mConnStatus.get() == 3 || this.mConnStatus.get() == 4) {
            return true;
        }
        return false;
    }

    public void disconnect() {
        int i = this.mConnStatus.get();
        HMSLog.m86966i(TAG, "Enter disconnect, Connection Status: " + i);
        if (i == 1) {
            return;
        }
        if (i == 3) {
            BinderAdapter binderAdapter = this.mBinderAdapter;
            if (binderAdapter != null) {
                binderAdapter.unBind();
            }
            setConnectStatus(1);
        } else if (i != 4 && i == 5) {
            cancelConnDelayHandle();
            setConnectStatus(4);
        }
    }

    public void bindCoreService() {
        String innerHmsPkg = this.mClientSettings.getInnerHmsPkg();
        HMSPackageManager.getInstance(this.mContext).refresh();
        if (PackageConstants.SERVICES_PACKAGE_ALL_SCENE.equals(innerHmsPkg)) {
            innerHmsPkg = HMSPackageManager.getInstance(this.mContext).getHMSPackageName();
        }
        String serviceAction = getServiceAction();
        if (innerHmsPkg.equalsIgnoreCase(this.mContext.getPackageName())) {
            String serviceActionMetadata = Util.getServiceActionMetadata(this.mContext);
            if (!TextUtils.isEmpty(serviceActionMetadata)) {
                serviceAction = serviceActionMetadata;
            }
        }
        HMSLog.m86966i(TAG, "enter bindCoreService, packageName is " + innerHmsPkg + ", serviceAction is " + serviceAction);
        BinderAdapter binderAdapter = new BinderAdapter(this.mContext, serviceAction, innerHmsPkg);
        this.mBinderAdapter = binderAdapter;
        binderAdapter.binder(new BinderAdapter.BinderCallBack() {
            /* class com.huawei.hms.common.internal.BaseHmsClient.C235101 */

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onBinderFailed(int i) {
                onBinderFailed(i, null);
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onNullBinding(ComponentName componentName) {
                BaseHmsClient.this.setConnectStatus(1);
                BaseHmsClient.this.notifyFailed(10);
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onServiceDisconnected(ComponentName componentName) {
                HMSLog.m86966i(BaseHmsClient.TAG, "Enter onServiceDisconnected.");
                BaseHmsClient.this.setConnectStatus(1);
                if (BaseHmsClient.this.mConnectionCallbacks != null) {
                    BaseHmsClient.this.mConnectionCallbacks.onConnectionSuspended(1);
                }
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                HMSLog.m86966i(BaseHmsClient.TAG, "Enter onServiceConnected.");
                BaseHmsClient.this.mService = AbstractC23528d.AbstractBinderC23529a.m85296a(iBinder);
                if (BaseHmsClient.this.mService == null) {
                    HMSLog.m86964e(BaseHmsClient.TAG, "Failed to get service as interface, trying to unbind.");
                    BaseHmsClient.this.mBinderAdapter.unBind();
                    BaseHmsClient.this.setConnectStatus(1);
                    BaseHmsClient.this.notifyFailed(10);
                    return;
                }
                BaseHmsClient.this.onConnecting();
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onBinderFailed(int i, Intent intent) {
                if (intent != null) {
                    Activity cpActivity = BaseHmsClient.this.mClientSettings.getCpActivity();
                    if (cpActivity != null) {
                        HMSLog.m86966i(BaseHmsClient.TAG, "onBinderFailed: SDK try to resolve and reConnect!");
                        cpActivity.startActivity(intent);
                        return;
                    }
                    HMSLog.m86966i(BaseHmsClient.TAG, "onBinderFailed: return pendingIntent to kit and cp");
                    BaseHmsClient.this.notifyFailed(new ConnectionResult(10, PendingIntent.getActivity(BaseHmsClient.this.mContext, 11, intent, 134217728)));
                    BaseHmsClient.this.mService = null;
                    return;
                }
                HMSLog.m86966i(BaseHmsClient.TAG, "onBinderFailed: pendingIntent is null!");
                BaseHmsClient.this.notifyFailed(new ConnectionResult(10, (PendingIntent) null));
                BaseHmsClient.this.mService = null;
            }
        });
    }

    public void connect(int i) {
        checkAvailabilityAndConnect(i, false);
    }

    public void setConnectStatus(int i) {
        this.mConnStatus.set(i);
    }

    private void onDisconnectionResult(ResolveResult<DisconnectResp> resolveResult) {
        HMSLog.m86966i(TAG, "Enter onDisconnectionResult, disconnect from server result: " + resolveResult.getStatus().getStatusCode());
        this.mBinderAdapter.unBind();
        setConnectStatus(1);
    }

    public void notifyFailed(int i) {
        HMSLog.m86966i(TAG, "notifyFailed result: " + i);
        OnConnectionFailedListener onConnectionFailedListener = this.mConnectionFailedListener;
        if (onConnectionFailedListener != null) {
            onConnectionFailedListener.onConnectionFailed(new ConnectionResult(i));
        }
    }

    public void notifyFailed(ConnectionResult connectionResult) {
        HMSLog.m86966i(TAG, "notifyFailed result: " + connectionResult.getErrorCode());
        OnConnectionFailedListener onConnectionFailedListener = this.mConnectionFailedListener;
        if (onConnectionFailedListener != null) {
            onConnectionFailedListener.onConnectionFailed(connectionResult);
        }
    }

    public void connect(int i, boolean z) {
        checkAvailabilityAndConnect(i, z);
    }

    private void resolution(AvailableAdapter availableAdapter, int i) {
        HMSLog.m86966i(TAG, "enter HmsCore resolution");
        if (!getClientSettings().isHasActivity()) {
            PackageNameManager.getInstance(this.mContext).queryPackageName(new VoidOnConnectionFailed(i));
            return;
        }
        Activity activeActivity = Util.getActiveActivity(getClientSettings().getCpActivity(), getContext());
        if (activeActivity != null) {
            availableAdapter.startResolution(activeActivity, new AvailableAdapter.AvailableCallBack() {
                /* class com.huawei.hms.common.internal.BaseHmsClient.C235112 */

                @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
                public void onComplete(int i) {
                    if (i == 0) {
                        BaseHmsClient.this.bindCoreService();
                    } else {
                        BaseHmsClient.this.notifyFailed(i);
                    }
                }
            });
        } else {
            notifyFailed(26);
        }
    }

    private void checkAvailabilityAndConnect(int i, boolean z) {
        HMSLog.m86966i(TAG, "====== HMSSDK version: 50005300 ======");
        int i2 = this.mConnStatus.get();
        HMSLog.m86966i(TAG, "Enter connect, Connection Status: " + i2);
        if (z || !(i2 == 3 || i2 == 5 || i2 == 4)) {
            setConnectStatus(5);
            if (getMinApkVersion() > i) {
                i = getMinApkVersion();
            }
            HMSLog.m86966i(TAG, "connect minVersion:" + i + " packageName:" + this.mClientSettings.getInnerHmsPkg());
            if (this.mContext.getPackageName().equals(this.mClientSettings.getInnerHmsPkg())) {
                HMSLog.m86966i(TAG, "service packageName is same, bind core service return");
                bindCoreService();
            } else if (Util.isAvailableLibExist(this.mContext)) {
                AvailableAdapter availableAdapter = new AvailableAdapter(i);
                int isHuaweiMobileServicesAvailable = availableAdapter.isHuaweiMobileServicesAvailable(this.mContext);
                HMSLog.m86966i(TAG, "check available result: " + isHuaweiMobileServicesAvailable);
                if (isHuaweiMobileServicesAvailable == 0) {
                    bindCoreService();
                } else if (availableAdapter.isUserResolvableError(isHuaweiMobileServicesAvailable)) {
                    HMSLog.m86966i(TAG, "bindCoreService3.0 fail, start resolution now.");
                    resolution(availableAdapter, isHuaweiMobileServicesAvailable);
                } else {
                    HMSLog.m86966i(TAG, "bindCoreService3.0 fail: " + isHuaweiMobileServicesAvailable + " is not resolvable.");
                    notifyFailed(isHuaweiMobileServicesAvailable);
                }
            } else {
                int isHuaweiMobileServicesAvailable2 = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this.mContext, i);
                HMSLog.m86966i(TAG, "HuaweiApiAvailability check available result: " + isHuaweiMobileServicesAvailable2);
                if (isHuaweiMobileServicesAvailable2 == 0) {
                    bindCoreService();
                } else {
                    notifyFailed(isHuaweiMobileServicesAvailable2);
                }
            }
        }
    }

    public BaseHmsClient(Context context, ClientSettings clientSettings, OnConnectionFailedListener onConnectionFailedListener, ConnectionCallbacks connectionCallbacks) {
        this.mContext = context;
        this.mClientSettings = clientSettings;
        this.mAppID = clientSettings.getAppID();
        this.mConnectionFailedListener = onConnectionFailedListener;
        this.mConnectionCallbacks = connectionCallbacks;
    }
}
