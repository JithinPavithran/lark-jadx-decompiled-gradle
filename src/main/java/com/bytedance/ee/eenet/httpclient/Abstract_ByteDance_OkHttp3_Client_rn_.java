package com.bytedance.ee.eenet.httpclient;

import com.bytedance.ee.eenet.util.__Assert_Not_Null_rn_;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.CookieJar;
import okhttp3.Dispatcher;
import okhttp3.Dns;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

/* renamed from: com.bytedance.ee.eenet.httpclient.f */
public abstract class Abstract_ByteDance_OkHttp3_Client_rn_ extends OkHttpClient implements AbstractC12627g {

    /* renamed from: a */
    protected OkHttpClient okHttp_client_rn_n;

    /* renamed from: b */
    public C12619d f33828b = new C12619d();

    /* renamed from: c */
    private EventListener.Factory f33829c = new EventListener.Factory() {
        /* class com.bytedance.ee.eenet.httpclient.AbstractC12625f.C126261 */

        @Override // okhttp3.EventListener.Factory
        public EventListener create(Call call) {
            OkHttpClient okHttpClient = Abstract_ByteDance_OkHttp3_Client_rn_.this.okHttp_client_rn_n;
            __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
            Abstract_ByteDance_OkHttp3_Client_rn_.this.f33828b.mo47926a(call, okHttpClient.eventListenerFactory().create(call));
            return Abstract_ByteDance_OkHttp3_Client_rn_.this.f33828b;
        }
    };

    @Override // okhttp3.OkHttpClient
    public EventListener.Factory eventListenerFactory() {
        return this.f33829c;
    }

    /* renamed from: a */
    public C12614a mo47928a() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return new C12614a(okHttpClient.newBuilder());
    }

    @Override // okhttp3.OkHttpClient
    public Authenticator authenticator() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.authenticator();
    }

    @Override // okhttp3.OkHttpClient
    public Cache cache() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.cache();
    }

    @Override // okhttp3.OkHttpClient
    public CertificatePinner certificatePinner() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.certificatePinner();
    }

    @Override // okhttp3.OkHttpClient
    public int connectTimeoutMillis() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.connectTimeoutMillis();
    }

    @Override // okhttp3.OkHttpClient
    public ConnectionPool connectionPool() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.connectionPool();
    }

    @Override // okhttp3.OkHttpClient
    public List<ConnectionSpec> connectionSpecs() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.connectionSpecs();
    }

    @Override // okhttp3.OkHttpClient
    public CookieJar cookieJar() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.cookieJar();
    }

    @Override // okhttp3.OkHttpClient
    public Dispatcher dispatcher() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.dispatcher();
    }

    @Override // okhttp3.OkHttpClient
    public Dns dns() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.dns();
    }

    @Override // okhttp3.OkHttpClient
    public boolean followRedirects() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.followRedirects();
    }

    @Override // okhttp3.OkHttpClient
    public boolean followSslRedirects() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.followSslRedirects();
    }

    @Override // okhttp3.OkHttpClient
    public HostnameVerifier hostnameVerifier() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.hostnameVerifier();
    }

    @Override // okhttp3.OkHttpClient
    public List<Interceptor> interceptors() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.interceptors();
    }

    @Override // okhttp3.OkHttpClient
    public List<Interceptor> networkInterceptors() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.networkInterceptors();
    }

    @Override // okhttp3.OkHttpClient
    public final OkHttpClient.Builder newBuilder() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.newBuilder();
    }

    @Override // okhttp3.OkHttpClient
    public int pingIntervalMillis() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.pingIntervalMillis();
    }

    @Override // okhttp3.OkHttpClient
    public List<Protocol> protocols() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.protocols();
    }

    @Override // okhttp3.OkHttpClient
    public Proxy proxy() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.proxy();
    }

    @Override // okhttp3.OkHttpClient
    public Authenticator proxyAuthenticator() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.proxyAuthenticator();
    }

    @Override // okhttp3.OkHttpClient
    public ProxySelector proxySelector() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.proxySelector();
    }

    @Override // okhttp3.OkHttpClient
    public int readTimeoutMillis() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.readTimeoutMillis();
    }

    @Override // okhttp3.OkHttpClient
    public boolean retryOnConnectionFailure() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.retryOnConnectionFailure();
    }

    @Override // okhttp3.OkHttpClient
    public SocketFactory socketFactory() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.socketFactory();
    }

    @Override // okhttp3.OkHttpClient
    public SSLSocketFactory sslSocketFactory() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.sslSocketFactory();
    }

    @Override // okhttp3.OkHttpClient
    public int writeTimeoutMillis() {
        OkHttpClient okHttpClient = this.okHttp_client_rn_n;
        __Assert_Not_Null_rn_.__assert_not_null_rn_(okHttpClient);
        return okHttpClient.writeTimeoutMillis();
    }
}
