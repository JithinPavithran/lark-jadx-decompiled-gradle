package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"lifecycleScope", "Landroidx/lifecycle/LifecycleCoroutineScope;", "Landroidx/lifecycle/LifecycleOwner;", "getLifecycleScope", "(Landroidx/lifecycle/LifecycleOwner;)Landroidx/lifecycle/LifecycleCoroutineScope;", "lifecycle-runtime-ktx_release"}, mo238835k = 2, mv = {1, 1, 15})
/* renamed from: androidx.lifecycle.p */
public final class C1170p {
    /* renamed from: a */
    public static final LifecycleCoroutineScope m5399a(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "$this$lifecycleScope");
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkExpressionValueIsNotNull(lifecycle, "lifecycle");
        return C1169o.m5398a(lifecycle);
    }
}
