package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.calls.Caller;

/* access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001\"\u0006\b\u0000\u0010\u0002 \u0001\"\u0006\b\u0001\u0010\u0002 \u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/calls/Caller;", "R", "invoke"}, mo238835k = 3, mv = {1, 1, 16})
public final class KPropertyImpl$Getter$caller$2 extends Lambda implements Function0<Caller<?>> {
    final /* synthetic */ KPropertyImpl.Getter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KPropertyImpl$Getter$caller$2(KPropertyImpl.Getter getter) {
        super(0);
        this.this$0 = getter;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Caller<?> invoke() {
        return KPropertyImplKt.computeCallerForAccessor(this.this$0, true);
    }
}
