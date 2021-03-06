package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.C69574m;
import kotlinx.coroutines.ak;
import kotlinx.coroutines.al;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u000f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\u0014\u0010\u0016\u001a\u00020\u00122\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J!\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0013\u001a\u00028\u00002\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016¢\u0006\u0002\u0010\u001eJ\u0014\u0010\u001f\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e¨\u0006 "}, d2 = {"Lkotlinx/coroutines/channels/Closed;", "E", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "closeCause", "", "(Ljava/lang/Throwable;)V", "offerResult", "getOfferResult", "()Lkotlinx/coroutines/channels/Closed;", "pollResult", "getPollResult", "receiveException", "getReceiveException", "()Ljava/lang/Throwable;", "sendException", "getSendException", "completeResumeReceive", "", "value", "(Ljava/lang/Object;)V", "completeResumeSend", "resumeSendClosed", "closed", "toString", "", "tryResumeReceive", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "tryResumeSend", "kotlinx-coroutines-core"}, mo238835k = 1, mv = {1, 1, 16})
/* renamed from: kotlinx.coroutines.channels.l */
public final class Closed<E> extends Send implements ReceiveOrClosed<E> {

    /* renamed from: a */
    public final Throwable f173519a;

    @Override // kotlinx.coroutines.channels.Send
    /* renamed from: b */
    public void mo242836b() {
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    /* renamed from: b */
    public void mo242811b(E e) {
    }

    /* renamed from: e */
    public Closed<E> mo242845f() {
        return this;
    }

    /* renamed from: g */
    public Closed<E> mo242833a() {
        return this;
    }

    /* renamed from: c */
    public final Throwable mo242842c() {
        Throwable th = this.f173519a;
        if (th != null) {
            return th;
        }
        return new ClosedSendChannelException("Channel was closed");
    }

    /* renamed from: d */
    public final Throwable mo242843d() {
        Throwable th = this.f173519a;
        if (th != null) {
            return th;
        }
        return new ClosedReceiveChannelException("Channel was closed");
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return "Closed@" + al.m266273a(this) + '[' + this.f173519a + ']';
    }

    public Closed(Throwable th) {
        this.f173519a = th;
    }

    @Override // kotlinx.coroutines.channels.Send
    /* renamed from: a */
    public Symbol mo242834a(LockFreeLinkedListNode.PrepareOp cVar) {
        Symbol abVar = C69574m.f173872a;
        if (cVar != null) {
            cVar.mo243030a();
        }
        return abVar;
    }

    @Override // kotlinx.coroutines.channels.Send
    /* renamed from: a */
    public void mo242835a(Closed<?> lVar) {
        if (ak.m266269a()) {
            throw new AssertionError();
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    /* renamed from: a */
    public Symbol mo242809a(E e, LockFreeLinkedListNode.PrepareOp cVar) {
        Symbol abVar = C69574m.f173872a;
        if (cVar != null) {
            cVar.mo243030a();
        }
        return abVar;
    }
}
