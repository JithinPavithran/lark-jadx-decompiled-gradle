package com.huawei.hms.framework.network.grs.p1018d;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.BuildConfig;
import com.huawei.hms.framework.common.Logger;
import java.util.Locale;

/* renamed from: com.huawei.hms.framework.network.grs.d.a */
public class C23577a {
    /* renamed from: a */
    public static String m85540a() {
        return BuildConfig.VERSION_NAME;
    }

    /* renamed from: a */
    public static String m85541a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.m85376w("AgentUtil", "", e);
            return "";
        }
    }

    /* renamed from: a */
    public static String m85542a(Context context, String str, String str2) {
        if (context == null) {
            return String.format(Locale.ROOT, str + "/%s", m85540a());
        }
        String packageName = context.getPackageName();
        String a = m85541a(context);
        String str3 = Build.VERSION.RELEASE;
        String str4 = Build.MODEL;
        Locale locale = Locale.ROOT;
        String str5 = "%s/%s (Linux; Android %s; %s) " + str + "/%s %s";
        Object[] objArr = new Object[6];
        objArr[0] = packageName;
        objArr[1] = a;
        objArr[2] = str3;
        objArr[3] = str4;
        objArr[4] = m85540a();
        if (TextUtils.isEmpty(str2)) {
            str2 = "no_service_name";
        }
        objArr[5] = str2;
        return String.format(locale, str5, objArr);
    }

    /* renamed from: b */
    public static String m85543b(Context context, String str, String str2) {
        return m85542a(context, str, str2);
    }
}