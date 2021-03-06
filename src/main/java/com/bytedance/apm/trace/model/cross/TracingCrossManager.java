package com.bytedance.apm.trace.model.cross;

import com.bytedance.apm.trace.api.C3085d;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TracingCrossManager {
    private static Map<Long, C3085d> sCrossTracingContext = new ConcurrentHashMap();

    public static void unRegisterCross(long j) {
        sCrossTracingContext.remove(Long.valueOf(j));
    }

    public static void registerCross(long j, C3085d dVar) {
        sCrossTracingContext.put(Long.valueOf(j), dVar);
    }

    public static void onPassBackAsChild(String str, String str2, ArrayList<NativeSpan> arrayList) {
        C3085d dVar = sCrossTracingContext.get(Long.valueOf(Long.parseLong(str)));
        if (dVar != null) {
            sCrossTracingContext.remove(Long.valueOf(Long.parseLong(str)));
            dVar.mo12959a(str2, arrayList);
        }
    }

    public static void onPassBackAsReference(String str, String str2, ArrayList<NativeSpan> arrayList) {
        C3085d dVar = sCrossTracingContext.get(Long.valueOf(Long.parseLong(str)));
        if (dVar != null) {
            sCrossTracingContext.remove(Long.valueOf(Long.parseLong(str)));
            dVar.mo12961b(str2, arrayList);
        }
    }
}
