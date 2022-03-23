package com.larksuite.framework.http.p1191f;

import java.util.Random;

/* renamed from: com.larksuite.framework.http.f.d */
public class Time_And_Random_rn_ {
    /* renamed from: a */
    public static String get_current_milli_seconds_plus_insecure_random_rn_() {
        long currentTimeMillis = System.currentTimeMillis();
        int nextInt = new Random(currentTimeMillis).nextInt();
        return String.valueOf(currentTimeMillis) + Math.abs(nextInt);
    }
}
