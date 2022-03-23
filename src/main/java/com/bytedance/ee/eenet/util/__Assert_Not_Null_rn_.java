package com.bytedance.ee.eenet.util;

/* renamed from: com.bytedance.ee.eenet.util.a */
public class __Assert_Not_Null_rn_ {
    /* renamed from: a */
    public static void __assert_not_null_rn_(Object obj) {
        if (obj == null) {
            throw new AssertionError("object is null");
        }
    }
}
