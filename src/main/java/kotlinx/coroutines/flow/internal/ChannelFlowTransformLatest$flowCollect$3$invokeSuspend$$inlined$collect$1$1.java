package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@¨\u0006\u0007"}, d2 = {"emit", "", "T", "value", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1"}, mo238835k = 3, mv = {1, 1, 16})
@DebugMetadata(mo239172c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1", mo239173f = "Merge.kt", mo239174i = {0, 0, 0, 0, 0}, mo239175l = {135}, mo239176m = "emit", mo239177n = {"this", "value", "continuation", "value", "$this$apply"}, mo239178s = {"L$0", "L$1", "L$2", "L$3", "L$5"})
public final class ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ChannelFlowTransformLatest.C69467a.C69468a this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$1(ChannelFlowTransformLatest.C69467a.C69468a aVar, Continuation cVar) {
        super(cVar);
        this.this$0 = aVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}