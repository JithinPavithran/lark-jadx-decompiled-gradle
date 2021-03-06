package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000e2\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u0016H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u001c\u001a\u00020\u0011H\u0002J\u001e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u001fH\u0016J\b\u0010 \u001a\u00020\u0006H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00018VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lkotlinx/coroutines/internal/MissingMainCoroutineDispatcher;", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "cause", "", "errorHint", "", "(Ljava/lang/Throwable;Ljava/lang/String;)V", "immediate", "getImmediate", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "delay", "", "time", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "isDispatchNeeded", "", "missing", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "kotlinx-coroutines-core"}, mo238835k = 1, mv = {1, 1, 16})
/* renamed from: kotlinx.coroutines.internal.u */
public final class MissingMainCoroutineDispatcher extends MainCoroutineDispatcher implements Delay {

    /* renamed from: a */
    private final Throwable f173861a;

    /* renamed from: d */
    private final String f173862d;

    @Override // kotlinx.coroutines.MainCoroutineDispatcher
    /* renamed from: a */
    public MainCoroutineDispatcher mo242660a() {
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0023, code lost:
        if (r1 != null) goto L_0x0028;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Void m267109b() {
        /*
            r4 = this;
            java.lang.Throwable r0 = r4.f173861a
            if (r0 == 0) goto L_0x0039
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Module with the Main dispatcher had failed to initialize"
            r0.append(r1)
            java.lang.String r1 = r4.f173862d
            if (r1 == 0) goto L_0x0026
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ". "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            if (r1 == 0) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            java.lang.String r1 = ""
        L_0x0028:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.Throwable r2 = r4.f173861a
            r1.<init>(r0, r2)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x0039:
            kotlinx.coroutines.internal.C69572t.m267105a()
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.MissingMainCoroutineDispatcher.m267109b():java.lang.Void");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Main[missing");
        if (this.f173861a != null) {
            str = ", cause=" + this.f173861a;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(']');
        return sb.toString();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: a */
    public boolean mo242639a(CoroutineContext fVar) {
        m267109b();
        throw null;
    }

    public MissingMainCoroutineDispatcher(Throwable th, String str) {
        this.f173861a = th;
        this.f173862d = str;
    }

    /* renamed from: b */
    public Void mo242661a(long j, CancellableContinuation<? super Unit> kVar) {
        m267109b();
        throw null;
    }

    /* renamed from: c */
    public Void mo6045a(CoroutineContext fVar, Runnable runnable) {
        m267109b();
        throw null;
    }
}
