package com.bytedance.geckox.utils;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Objects;

/* renamed from: com.bytedance.geckox.utils.i */
public class C14363i {

    /* renamed from: a */
    static final char[] f37729a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: a */
    public static void m57837a(InputStream inputStream, String str) throws Exception {
        if (str != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream.read(bArr, 0, 4096);
                        if (read == -1) {
                            break;
                        }
                        instance.update(bArr, 0, read);
                    }
                    C14355c.m57819a(inputStream);
                    try {
                        byte[] digest = instance.digest();
                        String a = m57836a(digest, 0, digest.length);
                        if (!str.equals(a)) {
                            throw new RuntimeException("md5 check failed file: local md5:" + a + " expect md5:" + str);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("md5 check failed:" + e.getMessage(), e);
                    }
                } catch (Exception e2) {
                    throw new RuntimeException("md5 check failed:" + e2.getMessage(), e2);
                } catch (Throwable th) {
                    C14355c.m57819a(inputStream);
                    throw th;
                }
            } catch (Exception e3) {
                throw new RuntimeException("md5 check failed:" + e3.getMessage(), e3);
            }
        } else {
            throw new RuntimeException("md5 check failed: md5 == null");
        }
    }

    /* renamed from: a */
    private static String m57836a(byte[] bArr, int i, int i2) {
        Objects.requireNonNull(bArr, "bytes is null");
        if (i < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = i2 * 2;
        char[] cArr = new char[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = bArr[i5 + i] & 255;
            int i7 = i4 + 1;
            char[] cArr2 = f37729a;
            cArr[i4] = cArr2[i6 >> 4];
            i4 = i7 + 1;
            cArr[i7] = cArr2[i6 & 15];
        }
        return new String(cArr, 0, i3);
    }
}
