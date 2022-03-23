package com.larksuite.framework.http.p1187b;

import android.content.Context;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.larksuite.framework.http.p1186a.AbstractC25988d;
import com.larksuite.framework.http.p1186a.C25981a;
import com.larksuite.framework.http.p1189d.AbstractC25999a;
import com.larksuite.framework.http.p1189d.AbstractC26000b;
import com.larksuite.framework.http.p1190e.Lark_Clearable_Cookie_Jar_rn_;
import com.larksuite.framework.http.p1190e.Lark_Cookie_Persistor_rn_;
import com.larksuite.framework.http.request.Http_Request_rn_;
import com.larksuite.framework.http.response.Cookie_Details_Container_rn_;
import com.larksuite.framework.utils.C26252ad;
import com.ss.android.lark.log.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* renamed from: com.larksuite.framework.http.b.c */
public class C25992c implements AbstractC25990b {

    /* renamed from: a */
    OkHttpClient okhttp_client_rn_;

    /* renamed from: b */
    private AbstractC25990b.C25991a f64354b;

    /* renamed from: b */
    public OkHttpClient get_okhttp_client_rn_() {
        return this.okhttp_client_rn_;
    }

    @Override // com.larksuite.framework.http.p1187b.AbstractC25990b
    /* renamed from: a */
    public AbstractC25990b.C25991a mo92413a() {
        return new AbstractC25990b.C25991a(this.f64354b);
    }

    @Override // com.larksuite.framework.http.p1187b.AbstractC25990b
    /* renamed from: a */
    public <T> AbstractC25988d<T> mo92412a(Http_Request_rn_<T> aVar) {
        return new C25981a(this.okhttp_client_rn_, aVar);
    }

    /* renamed from: a */
    private List<Interceptor> m94096a(List<AbstractC26000b> list) {
        ArrayList arrayList = new ArrayList();
        for (AbstractC26000b bVar : list) {
            if (bVar instanceof AbstractC25999a) {
                final AbstractC25999a aVar = (AbstractC25999a) bVar;
                arrayList.add(new Interceptor() {
                    /* class com.larksuite.framework.http.p1187b.C25992c.C259931 */

                    @Override // okhttp3.Interceptor
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        return chain.proceed(chain.request().newBuilder().removeHeader("User-Agent").addHeader("User-Agent", aVar.mo92427a()).build());
                    }
                });
            }
        }
        return arrayList;
    }

    public C25992c(AbstractC25990b.C25991a aVar) {
        this.f64354b = aVar;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (aVar.f64346c) {
            Context context = aVar.f64344a;
            builder.cookieJar(new Lark_Clearable_Cookie_Jar_rn_(context, new SetCookieCache(), new Lark_Cookie_Persistor_rn_(context)));
        }
        List<Interceptor> a = m94096a(aVar.f64345b);
        if (a != null && a.size() > 0) {
            for (Interceptor interceptor : a) {
                builder.addInterceptor(interceptor);
            }
        }
        builder.connectTimeout(aVar.f64347d, aVar.f64348e).readTimeout(aVar.f64349f, aVar.f64350g).writeTimeout(aVar.f64351h, aVar.f64352i);
        this.okhttp_client_rn_ = builder.build();
    }

    @Override // com.larksuite.framework.http.p1187b.AbstractC25990b
    /* renamed from: a */
    public List<Cookie_Details_Container_rn_> mo92414a(String str) {
        List<Cookie> loadForRequest = this.okhttp_client_rn_.cookieJar().loadForRequest(HttpUrl.parse(str));
        ArrayList arrayList = new ArrayList();
        for (Cookie cookie : loadForRequest) {
            Cookie_Details_Container_rn_.C26015a aVar = new Cookie_Details_Container_rn_.C26015a();
            aVar.mo92513a(cookie.name()).mo92515b(cookie.value()).mo92512a(cookie.expiresAt()).mo92517c(cookie.domain()).mo92518d(cookie.path());
            if (cookie.secure()) {
                aVar.mo92511a();
            }
            if (cookie.httpOnly()) {
                aVar.mo92514b();
            }
            if (cookie.hostOnly()) {
                aVar.mo92514b();
            }
            if (cookie.persistent()) {
                aVar.mo92516c();
            }
            arrayList.add(aVar.mo92519d());
        }
        return arrayList;
    }

    @Override // com.larksuite.framework.http.p1187b.AbstractC25990b
    /* renamed from: a */
    public void mo92415a(Context context) {
        if (this.okhttp_client_rn_ == null) {
            throw new RuntimeException("please init HttpClient first!");
        } else if (C26252ad.m94993b(context)) {
            CookieJar cookieJar = this.okhttp_client_rn_.cookieJar();
            if (cookieJar instanceof ClearableCookieJar) {
                ((ClearableCookieJar) cookieJar).mo72355a();
                Log.m165389i("OkHttpClientImpl", "ClearableCookieJar clearSession finished");
            }
            Log.m165389i("OkHttpClientImpl", "clearSession not finished");
        }
    }

    @Override // com.larksuite.framework.http.p1187b.AbstractC25990b
    /* renamed from: a */
    public void mo92416a(String url_rn_, List<Cookie_Details_Container_rn_> cookie_details_list_rn_) {
        HttpUrl http_url_rn = HttpUrl.parse(url_rn_);
        CookieJar cookieJar = this.okhttp_client_rn_.cookieJar();
        ArrayList cookie_list_rn_ = new ArrayList();
        for (Cookie_Details_Container_rn_ cdc_rn_ : cookie_details_list_rn_) {
            Cookie.Builder builder = new Cookie.Builder();
            builder.name(cdc_rn_.mo92502a()).value(cdc_rn_.mo92503b()).expiresAt(cdc_rn_.mo92504c()).domain(cdc_rn_.mo92505d()).path(cdc_rn_.mo92506e());
            if (cdc_rn_.is_secure_rn_()) {
                builder.secure();
            }
            if (cdc_rn_.mo92508g()) {
                builder.httpOnly();
            }
            if (cdc_rn_.mo92510i()) {
                builder.httpOnly();
            }
            Cookie build = builder.build();
            if (cdc_rn_.is_persistent_rn_()) {
                build.persistent();
            }
            cookie_list_rn_.add(build);
        }
        cookieJar.saveFromResponse(http_url_rn, cookie_list_rn_);
    }
}
