package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0001H&J\u0016\u0010\r\u001a\u0004\u0018\u00010\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0004H&R\u001e\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/internal/AtomicDesc;", "", "()V", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "setAtomicOp", "(Lkotlinx/coroutines/internal/AtomicOp;)V", "complete", "", "op", "failure", "prepare", "kotlinx-coroutines-core"}, mo238835k = 1, mv = {1, 1, 16})
/* renamed from: kotlinx.coroutines.internal.b */
public abstract class AtomicDesc {

    /* renamed from: a */
    public AtomicOp<?> f173818a;

    /* renamed from: a */
    public abstract void mo242981a(AtomicOp<?> dVar, Object obj);

    /* renamed from: b */
    public abstract Object mo242982b(AtomicOp<?> dVar);

    /* renamed from: a */
    public final AtomicOp<?> mo242979a() {
        AtomicOp<?> dVar = this.f173818a;
        if (dVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("atomicOp");
        }
        return dVar;
    }

    /* renamed from: a */
    public final void mo242980a(AtomicOp<?> dVar) {
        this.f173818a = dVar;
    }
}
