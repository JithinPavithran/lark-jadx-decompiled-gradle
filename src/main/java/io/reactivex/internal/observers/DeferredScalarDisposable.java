package io.reactivex.internal.observers;

import io.reactivex.AbstractC69009q;
import io.reactivex.plugins.RxJavaPlugins;

public class DeferredScalarDisposable<T> extends BasicIntQueueDisposable<T> {
    private static final long serialVersionUID = -5502432239815349361L;
    protected final AbstractC69009q<? super T> downstream;
    protected T value;

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        set(4);
        this.value = null;
    }

    @Override // io.reactivex.internal.p3460b.AbstractC68373j
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    @Override // io.reactivex.disposables.Disposable
    public final boolean isDisposed() {
        if (get() == 4) {
            return true;
        }
        return false;
    }

    @Override // io.reactivex.internal.p3460b.AbstractC68373j
    public final boolean isEmpty() {
        if (get() != 16) {
            return true;
        }
        return false;
    }

    public final boolean tryDispose() {
        if (getAndSet(4) != 4) {
            return true;
        }
        return false;
    }

    public final void complete() {
        if ((get() & 54) == 0) {
            lazySet(2);
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.internal.p3460b.AbstractC68373j
    public final T poll() throws Exception {
        if (get() != 16) {
            return null;
        }
        T t = this.value;
        this.value = null;
        lazySet(32);
        return t;
    }

    public DeferredScalarDisposable(AbstractC69009q<? super T> qVar) {
        this.downstream = qVar;
    }

    @Override // io.reactivex.internal.p3460b.AbstractC68369f
    public final int requestFusion(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    public final void error(Throwable th) {
        if ((get() & 54) != 0) {
            RxJavaPlugins.onError(th);
            return;
        }
        lazySet(2);
        this.downstream.onError(th);
    }

    public final void complete(T t) {
        int i = get();
        if ((i & 54) == 0) {
            AbstractC69009q<? super T> qVar = this.downstream;
            if (i == 8) {
                this.value = t;
                lazySet(16);
                qVar.onNext(null);
            } else {
                lazySet(2);
                qVar.onNext(t);
            }
            if (get() != 4) {
                qVar.onComplete();
            }
        }
    }
}
