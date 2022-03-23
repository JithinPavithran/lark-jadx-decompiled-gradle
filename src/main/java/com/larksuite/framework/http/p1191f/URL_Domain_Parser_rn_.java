package com.larksuite.framework.http.p1191f;

import okhttp3.HttpUrl;

/* renamed from: com.larksuite.framework.http.f.c */
public class URL_Domain_Parser_rn_ {
    /* renamed from: a */
    public static String get_top_private_domain_from_URL_rn_(String str) {
        HttpUrl parse = HttpUrl.parse(str);
        if (parse == null) {
            return null;
        }
        return parse.topPrivateDomain();
    }

    /* renamed from: b */
    public static String get_host_from_URL_rn_(String str) {
        return HttpUrl.parse(str).host();
    }
}
