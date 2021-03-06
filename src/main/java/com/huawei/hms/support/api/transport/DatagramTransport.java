package com.huawei.hms.support.api.transport;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.client.ApiClient;

public interface DatagramTransport {

    public interface CallBack {
        void onCallback(int i, IMessageEntity iMessageEntity);
    }

    void post(ApiClient apiClient, CallBack callBack);

    void send(ApiClient apiClient, CallBack callBack);
}
