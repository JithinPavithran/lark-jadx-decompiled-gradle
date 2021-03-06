package com.ss.android.vc.statistics.event;

import com.ss.android.lark.statistics.Statistics;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/ss/android/vc/statistics/event/AddContactRequestEvent;", "", "()V", "sendAuthorizeCollaborationRequest", "", "lib_vc_suiteRelease"}, mo238835k = 1, mv = {1, 1, 16})
/* renamed from: com.ss.android.vc.statistics.event.a */
public final class AddContactRequestEvent {

    /* renamed from: a */
    public static final AddContactRequestEvent f160868a = new AddContactRequestEvent();

    private AddContactRequestEvent() {
    }

    @JvmStatic
    /* renamed from: a */
    public static final void m249939a() {
        Statistics.sendEvent("authorize_collaboration_request", new JSONObject().put("number", 1).put("scene", "vc_ongoing_confirm"));
    }
}
