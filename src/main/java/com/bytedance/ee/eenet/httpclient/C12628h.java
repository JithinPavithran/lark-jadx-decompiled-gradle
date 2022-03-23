package com.bytedance.ee.eenet.httpclient;

import com.bytedance.ee.eenet.util.__Assert_Not_Null_rn_;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* access modifiers changed from: package-private */
/* renamed from: com.bytedance.ee.eenet.httpclient.h */
public final class C12628h extends Abstract_ByteDance_OkHttp3_Client_rn_ {
    @Override // com.bytedance.ee.eenet.httpclient.AbstractC12625f
    /* renamed from: a */
    public C12614a mo47928a() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return new C12614a(okHttpClient.newBuilder(), true);
    }

    @Override // com.bytedance.ee.eenet.httpclient.AbstractC12627g
    /* renamed from: b */
    public Abstract_ByteDance_OkHttp3_Client_rn_ mo47924b(OkHttpClient okHttpClient) {
        return new C12628h(okHttpClient);
    }

    @Override // okhttp3.OkHttpClient, okhttp3.Call.Factory
    public Call newCall(Request request) {
        return super.newCall(request);
    }

    C12628h(OkHttpClient okHttpClient) {
        this.okHttp_client_rn_n = okHttpClient.newBuilder().build();
    }
}
