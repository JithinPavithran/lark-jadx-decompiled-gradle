package com.huawei.updatesdk.p1029a.p1030a.p1031a;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.huawei.updatesdk.a.a.a.e */
public class C23833e implements X509TrustManager {

    /* renamed from: a */
    protected List<X509TrustManager> f59040a = new ArrayList();

    public C23833e(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalArgumentException {
        if (context != null) {
            InputStream inputStream = null;
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
                KeyStore instance2 = KeyStore.getInstance("bks");
                inputStream = context.getAssets().open("updatesdkcas.bks");
                inputStream.reset();
                instance2.load(inputStream, "".toCharArray());
                instance.init(instance2);
                TrustManager[] trustManagers = instance.getTrustManagers();
                for (TrustManager trustManager : trustManagers) {
                    if (trustManager instanceof X509TrustManager) {
                        this.f59040a.add((X509TrustManager) trustManager);
                    }
                }
                if (this.f59040a.isEmpty()) {
                    throw new CertificateException("X509TrustManager is empty");
                }
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                        Log.e("SecureX509TrustManager", "close bks exception");
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("context is null");
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        Log.i("SecureX509TrustManager", "checkClientTrusted: ");
        for (X509TrustManager x509TrustManager : this.f59040a) {
            try {
                x509TrustManager.checkServerTrusted(x509CertificateArr, str);
                return;
            } catch (CertificateException e) {
                Log.e("SecureX509TrustManager", "checkServerTrusted CertificateException" + e.getMessage());
            }
        }
        throw new CertificateException("checkServerTrusted CertificateException");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        int size = this.f59040a.size();
        for (int i = 0; i < size; i++) {
            try {
                Log.i("SecureX509TrustManager", "checkServerTrusted: check server i : " + i);
                this.f59040a.get(i).checkServerTrusted(x509CertificateArr, str);
                return;
            } catch (CertificateException e) {
                Log.e("SecureX509TrustManager", "checkServerTrusted error :" + e.getMessage() + " , time : " + i);
                if (i == size - 1) {
                    if (x509CertificateArr != null && x509CertificateArr.length > 0) {
                        Log.e("SecureX509TrustManager", "root ca issuer : " + x509CertificateArr[x509CertificateArr.length - 1].getIssuerDN());
                    }
                    throw e;
                }
            }
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        try {
            ArrayList arrayList = new ArrayList();
            for (X509TrustManager x509TrustManager : this.f59040a) {
                arrayList.addAll(Arrays.asList(x509TrustManager.getAcceptedIssuers()));
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
        } catch (Exception e) {
            Log.e("SecureX509TrustManager", "getAcceptedIssuers exception : " + e.getMessage());
            return new X509Certificate[0];
        }
    }
}
