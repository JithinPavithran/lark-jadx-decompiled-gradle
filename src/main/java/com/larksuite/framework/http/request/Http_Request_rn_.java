package com.larksuite.framework.http.request;

import com.larksuite.framework.http.p1186a.AbstractC25988d;
import com.larksuite.framework.http.p1187b.AbstractC25990b;
import com.larksuite.framework.http.p1187b.C25994d;
import com.larksuite.framework.http.p1188c.AbstractC25998b;
import com.larksuite.framework.http.p1191f.Time_And_Random_rn_;
import com.larksuite.framework.http.p1191f.URL_Parameter_Add_rn_;
import com.larksuite.framework.http.response.AbstractC26016b;

/* renamed from: com.larksuite.framework.http.request.a */
public class Http_Request_rn_<T> {

    /* renamed from: a */
    private HttpParams http_params_rn_ = new HttpParams();

    /* renamed from: b */
    private HttpHeaders http_headers_rn_ = new HttpHeaders();

    /* renamed from: c */
    private String f64371c;

    /* renamed from: d */
    private String f64372d;

    /* renamed from: e */
    private AbstractC26016b f64373e;

    /* renamed from: f */
    private AbstractC25998b<T> f64374f;

    /* renamed from: g */
    private HttpMethod http_method_rn_ = HttpMethod.GET;

    /* renamed from: h */
    private RequestBody request_body_rn_;

    /* renamed from: i */
    private String f64377i = "BaseRequest";

    /* renamed from: j */
    private volatile AbstractC25988d<T> f64378j = null;

    /* renamed from: k */
    private AbstractC25990b f64379k = C25994d.m94103b().mo92425a();

    /* renamed from: d */
    public AbstractC25998b<T> mo92481d() {
        return this.f64374f;
    }

    /* renamed from: e */
    public HttpMethod get_http_method_rn_() {
        return this.http_method_rn_;
    }

    /* renamed from: f */
    public RequestBody get_request_body_rn_() {
        return this.request_body_rn_;
    }

    /* renamed from: g */
    public String mo92485g() {
        return this.f64377i;
    }

    /* renamed from: i */
    public HttpParams get_http_params_rn_() {
        return this.http_params_rn_;
    }

    /* renamed from: j */
    public AbstractC26016b mo92488j() {
        return this.f64373e;
    }

    /* renamed from: b */
    public String mo92478b() {
        String str = this.f64372d;
        if (str == null) {
            return "";
        }
        return str;
    }

    /* renamed from: c */
    public String get_url_with_parameters_rn_() {
        return URL_Parameter_Add_rn_.add_parameters_to_URL_rn_(mo92474a() + mo92478b(), this.http_params_rn_.urlParamsMap);
    }

    /* renamed from: h */
    public HttpHeaders get_headers_rn_() {
        if (!this.http_headers_rn_.headersMap.containsKey("X-BaseRequest-ID")) {
            this.http_headers_rn_.put("X-BaseRequest-ID", Time_And_Random_rn_.get_current_milli_seconds_plus_insecure_random_rn_());
        }
        return this.http_headers_rn_;
    }

    public Http_Request_rn_() {
    }

    /* renamed from: a */
    public String mo92474a() {
        return this.f64371c;
    }

    /* renamed from: a */
    public Http_Request_rn_<T> set_http_headers_rn_(HttpHeaders httpHeaders) {
        this.http_headers_rn_ = httpHeaders;
        return this;
    }

    /* renamed from: b */
    public Http_Request_rn_<T> mo92477b(String str) {
        this.f64371c = str;
        return this;
    }

    /* renamed from: c */
    public Http_Request_rn_<T> mo92479c(String str) {
        this.f64372d = str;
        return this;
    }

    /* renamed from: a */
    public Http_Request_rn_<T> set_http_method_rn_(HttpMethod httpMethod) {
        this.http_method_rn_ = httpMethod;
        return this;
    }

    /* renamed from: b */
    public Http_Request_rn_<T> set_POST_request_body_rn_(RequestBody requestBody) {
        this.http_method_rn_ = HttpMethod.POST;
        this.request_body_rn_ = requestBody;
        return this;
    }

    /* renamed from: d */
    public Http_Request_rn_<T> mo92482d(String str) {
        this.http_headers_rn_.remove(str);
        return this;
    }

    /* renamed from: a */
    public Http_Request_rn_<T> set_http_parameters_rn_(HttpParams httpParams) {
        this.http_params_rn_ = httpParams;
        return this;
    }

    /* renamed from: a */
    public Http_Request_rn_<T> set_request_body_rn_(RequestBody requestBody) {
        this.request_body_rn_ = requestBody;
        return this;
    }

    /* renamed from: a */
    public Http_Request_rn_<T> mo92472a(String str) {
        this.f64371c = str;
        return this;
    }

    /* renamed from: a */
    public final void mo92475a(AbstractC26011b<T> bVar) {
        this.f64378j = this.f64379k.mo92412a(this);
        this.f64378j.mo92408a(bVar);
    }

    public Http_Request_rn_(HttpMethod httpMethod, String str) {
        this.http_method_rn_ = httpMethod;
        this.f64371c = str;
    }

    /* renamed from: a */
    public Http_Request_rn_<T> mo92473a(String str, String str2) {
        this.http_headers_rn_.put(str, str2);
        return this;
    }
}
