package com.huawei.hms.framework.network.grs.p1018d.p1020b;

import android.content.Context;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.huawei.hms.framework.network.grs.d.b.d */
public class C23583d implements X509TrustManager {

    /* renamed from: a */
    protected List<X509TrustManager> f58156a = new ArrayList();

    public C23583d(Context context, String str) {
        if (context == null || str == null) {
            throw new IllegalArgumentException("context or trust file is null");
        }
        InputStream inputStream = null;
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            KeyStore instance2 = KeyStore.getInstance("bks");
            inputStream = context.getAssets().open(str);
            inputStream.reset();
            instance2.load(inputStream, "".toCharArray());
            instance.init(instance2);
            TrustManager[] trustManagers = instance.getTrustManagers();
            for (int i = 0; i < trustManagers.length; i++) {
                if (trustManagers[i] instanceof X509TrustManager) {
                    this.f58156a.add((X509TrustManager) trustManagers[i]);
                }
            }
            if (this.f58156a.isEmpty()) {
                throw new CertificateException("X509TrustManager is empty");
            }
        } finally {
            IoUtils.closeSecure(inputStream);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        if (!this.f58156a.isEmpty()) {
            this.f58156a.get(0).checkClientTrusted(x509CertificateArr, str);
            return;
        }
        throw new CertificateException("checkClientTrusted CertificateException");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        if (!this.f58156a.isEmpty()) {
            this.f58156a.get(0).checkServerTrusted(x509CertificateArr, str);
            return;
        }
        throw new CertificateException("checkServerTrusted CertificateException");
    }

    public X509Certificate[] getAcceptedIssuers() {
        try {
            ArrayList arrayList = new ArrayList();
            for (X509TrustManager x509TrustManager : this.f58156a) {
                arrayList.addAll(Arrays.asList(x509TrustManager.getAcceptedIssuers()));
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
        } catch (Exception unused) {
            Logger.m85375w("SecureX509TrustManager", "getAcceptedIssuers exception");
            return new X509Certificate[0];
        }
    }
}
