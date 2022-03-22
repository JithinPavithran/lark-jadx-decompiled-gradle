package com.ss.android.lark.share.impl.systemshare;

import android.content.Context;
import android.net.Uri;
import com.larksuite.framework.utils.LarkUriUtil;

/* renamed from: com.ss.android.lark.share.impl.systemshare.d */
public class C54767d {
    /* renamed from: a */
    public static boolean if_URI_has_image_MIME_type_rn_(Context context, Uri uri) {
        String f = LarkUriUtil.get_mime_type_from_uri_rn_(context, uri);
        if (f != null) {
            return f.contains("image/");
        }
        return false;
    }

    /* renamed from: b */
    public static boolean if_URI_has_video_MIME_type_rn_(Context context, Uri uri) {
        String f = LarkUriUtil.get_mime_type_from_uri_rn_(context, uri);
        if (f != null) {
            return f.contains("video/");
        }
        return false;
    }
}
