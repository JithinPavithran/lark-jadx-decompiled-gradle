package com.larksuite.framework.http.p1190e;

import android.content.Context;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.cache.CookieCache;
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;
import com.larksuite.framework.utils.C26252ad;
import com.ss.android.lark.log.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/* renamed from: com.larksuite.framework.http.e.a */
public class Lark_Clearable_Cookie_Jar_rn_ implements ClearableCookieJar {

    /* renamed from: a */
    boolean f64359a;

    /* renamed from: b */
    private Context context_rn_;

    /* renamed from: c */
    private CookieCache cookie_cache_rn_;

    /* renamed from: d */
    private CookiePersistor cookie_persistor_rn_;

    @Override // com.franmontiel.persistentcookiejar.ClearableCookieJar
    /* renamed from: a */
    public synchronized void mo72355a() {
        this.cookie_cache_rn_.mo72356a();
        this.cookie_persistor_rn_.mo72368b();
        Log.m165389i("PersistentCookieJar", "clear process:" + C26252ad.m94992a(this.context_rn_));
    }

    /* renamed from: a */
    private static List<Cookie> m94109a(List<Cookie> list) {
        ArrayList arrayList = new ArrayList();
        for (Cookie cookie : list) {
            if (cookie.persistent()) {
                arrayList.add(cookie);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m94111a(boolean z) {
        if (!this.f64359a) {
            List<Cookie> a = this.cookie_persistor_rn_.mo72366a();
            if (a == null || a.size() <= 0) {
                Log.m165389i("PersistentCookieJar", "ensureLoaded failed");
                return;
            }
            this.f64359a = true;
            this.cookie_cache_rn_.mo72357a(a);
            Log.m165388i("PersistentCookieJar ensureLoaded process:" + C26252ad.m94992a(this.context_rn_));
        }
    }

    /* renamed from: a */
    private static boolean is_expired_cookie_rn_(Cookie cookie) {
        if (cookie.expiresAt() < System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    @Override // okhttp3.CookieJar
    public synchronized List<Cookie> loadForRequest(HttpUrl httpUrl) {
        ArrayList cookies_for_the_URL_rn_;
        ArrayList expired_cookies_rn_ = new ArrayList();
        cookies_for_the_URL_rn_ = new ArrayList();
        m94111a(false);
        Iterator it = this.cookie_cache_rn_.iterator();
        while (it.hasNext()) {
            Cookie cookie = (Cookie) it.next();
            if (is_expired_cookie_rn_(cookie)) {
                expired_cookies_rn_.add(cookie);
                it.remove();
                m94110a(httpUrl, cookie, "PersistentCookieJar expiredcookie loadForRequest");
            } else if (cookie.matches(httpUrl)) {
                cookies_for_the_URL_rn_.add(cookie);
            }
        }
        this.cookie_persistor_rn_.mo72369b(expired_cookies_rn_);
        httpUrl.host();
        return cookies_for_the_URL_rn_;
    }

    @Override // okhttp3.CookieJar
    public synchronized void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        this.cookie_cache_rn_.mo72357a(list);
        this.cookie_persistor_rn_.mo72367a(m94109a(list));
        httpUrl.host();
    }

    public Lark_Clearable_Cookie_Jar_rn_(Context context, CookieCache cookieCache, CookiePersistor cookiePersistor) {
        this.context_rn_ = context;
        this.cookie_cache_rn_ = cookieCache;
        this.cookie_persistor_rn_ = cookiePersistor;
    }

    /* renamed from: a */
    private void m94110a(HttpUrl httpUrl, Cookie cookie, String str) {
        try {
            Log.m165389i(str, httpUrl.toString());
        } catch (Throwable th) {
            Log.m165386e("logCookie", th);
        }
    }
}
