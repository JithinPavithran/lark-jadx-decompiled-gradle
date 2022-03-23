package com.larksuite.framework.http.p1191f;

import android.util.Pair;
import androidx.collection.ArrayMap;
import com.larksuite.framework.http.p1187b.AbstractC25990b;
import com.larksuite.framework.http.request.Http_Request_rn_;
import com.larksuite.framework.http.request.FormBody;
import com.larksuite.framework.http.request.HttpMethod;
import com.larksuite.framework.http.request.RequestBody;
import com.larksuite.framework.http.response.BaseResponse;
import com.ss.android.lark.log.Log;
import java.util.List;

/* renamed from: com.larksuite.framework.http.f.b */
public class C26004b {
    /* renamed from: a */
    public static BaseResponse m94125a(AbstractC25990b bVar, Http_Request_rn_<BaseResponse> aVar) {
        if (aVar != null) {
            return (BaseResponse) bVar.mo92412a(aVar).mo92407a(BaseResponse.class);
        }
        Log.m165383e("HTTPChannelUtils", "BaseRequest should not be NULL");
        return null;
    }

    /* renamed from: a */
    public static Http_Request_rn_ m94122a(String str, ArrayMap<String, String> arrayMap) {
        Http_Request_rn_ b = new Http_Request_rn_().mo92477b(str);
        if (arrayMap != null) {
            int size = arrayMap.size();
            for (int i = 0; i < size; i++) {
                b.get_headers_rn_().put(arrayMap.keyAt(i), arrayMap.valueAt(i));
            }
        }
        return b;
    }

    /* renamed from: a */
    public static Http_Request_rn_ m94123a(String str, List<Pair<String, String>> list, ArrayMap<String, String> arrayMap) {
        Http_Request_rn_ aVar = new Http_Request_rn_(HttpMethod.POST, str);
        FormBody.C26008a aVar2 = new FormBody.C26008a();
        if (list != null) {
            for (Pair<String, String> pair : list) {
                aVar2.mo92430a((String) pair.first, (String) pair.second);
            }
            aVar.set_POST_request_body_rn_(aVar2.mo92431a());
        }
        if (arrayMap != null) {
            int size = arrayMap.size();
            for (int i = 0; i < size; i++) {
                aVar.mo92473a(arrayMap.keyAt(i), arrayMap.valueAt(i));
            }
        }
        return aVar;
    }

    /* renamed from: a */
    public static Http_Request_rn_ m94124a(String str, byte[] bArr, String str2, ArrayMap<String, String> arrayMap) {
        Http_Request_rn_ aVar = new Http_Request_rn_(HttpMethod.POST, str);
        if (!(bArr == null || str2 == null)) {
            aVar.set_POST_request_body_rn_(RequestBody.create(str2, bArr));
        }
        if (arrayMap != null) {
            int size = arrayMap.size();
            for (int i = 0; i < size; i++) {
                aVar.mo92473a(arrayMap.keyAt(i), arrayMap.valueAt(i));
            }
        }
        return aVar;
    }
}
