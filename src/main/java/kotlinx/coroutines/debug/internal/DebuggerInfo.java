package kotlinx.coroutines.debug.internal;

import com.ss.android.lark.impl.hitpoint.ShareHitPoint;
import java.io.Serializable;
import java.lang.Thread;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\rR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\rR\u0011\u0010\u0019\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\r¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebuggerInfo;", "Ljava/io/Serializable;", ShareHitPoint.f121868c, "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;)V", "coroutineId", "", "getCoroutineId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "dispatcher", "", "getDispatcher", "()Ljava/lang/String;", "lastObservedStackTrace", "", "Ljava/lang/StackTraceElement;", "getLastObservedStackTrace", "()Ljava/util/List;", "lastObservedThreadName", "getLastObservedThreadName", "lastObservedThreadState", "getLastObservedThreadState", "name", "getName", "sequenceNumber", "getSequenceNumber", "()J", "state", "getState", "kotlinx-coroutines-core"}, mo238835k = 1, mv = {1, 1, 16})
public final class DebuggerInfo implements Serializable {
    private final Long coroutineId;
    private final String dispatcher;
    private final List<StackTraceElement> lastObservedStackTrace;
    private final String lastObservedThreadName;
    private final String lastObservedThreadState;
    private final String name;
    private final long sequenceNumber;
    private final String state;

    public final Long getCoroutineId() {
        return this.coroutineId;
    }

    public final String getDispatcher() {
        return this.dispatcher;
    }

    public final List<StackTraceElement> getLastObservedStackTrace() {
        return this.lastObservedStackTrace;
    }

    public final String getLastObservedThreadName() {
        return this.lastObservedThreadName;
    }

    public final String getLastObservedThreadState() {
        return this.lastObservedThreadState;
    }

    public final String getName() {
        return this.name;
    }

    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public final String getState() {
        return this.state;
    }

    public DebuggerInfo(DebugCoroutineInfo aVar) {
        Long l;
        String str;
        String str2;
        Thread.State state2;
        CoroutineId afVar = (CoroutineId) aVar.mo242882c().get(CoroutineId.f173399a);
        String str3 = null;
        if (afVar != null) {
            l = Long.valueOf(afVar.mo242643a());
        } else {
            l = null;
        }
        this.coroutineId = l;
        this.dispatcher = String.valueOf(aVar.mo242882c().get(ContinuationInterceptor.f173125b));
        CoroutineName agVar = (CoroutineName) aVar.mo242882c().get(CoroutineName.f173401a);
        if (agVar != null) {
            str = agVar.mo242651a();
        } else {
            str = null;
        }
        this.name = str;
        this.state = aVar.mo242880a();
        Thread thread = aVar.f173549a;
        if (thread == null || (state2 = thread.getState()) == null) {
            str2 = null;
        } else {
            str2 = state2.toString();
        }
        this.lastObservedThreadState = str2;
        Thread thread2 = aVar.f173549a;
        this.lastObservedThreadName = thread2 != null ? thread2.getName() : str3;
        this.lastObservedStackTrace = aVar.mo242881b();
        this.sequenceNumber = aVar.f173551c;
    }
}
