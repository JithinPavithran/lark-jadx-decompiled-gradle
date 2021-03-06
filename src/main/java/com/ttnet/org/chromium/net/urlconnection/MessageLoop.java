package com.ttnet.org.chromium.net.urlconnection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessageLoop implements Executor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final AtomicBoolean mDisableValidThreadAssert = new AtomicBoolean(false);
    private boolean mLoopFailed;
    private boolean mLoopRunning;
    private InterruptedIOException mPriorInterruptedIOException;
    private RuntimeException mPriorRuntimeException;
    private final BlockingQueue<Runnable> mQueue = new LinkedBlockingQueue();
    private long mThreadId = -1;

    public void quit() {
        this.mLoopRunning = false;
    }

    public void reset() {
        this.mLoopFailed = false;
    }

    public boolean hasLoopFailed() {
        return this.mLoopFailed;
    }

    public boolean isRunning() {
        return this.mLoopRunning;
    }

    public void loop() throws IOException {
        loop(0);
    }

    private boolean calledOnValidThread() {
        if (mDisableValidThreadAssert.get()) {
            return true;
        }
        long j = this.mThreadId;
        if (j == -1) {
            this.mThreadId = Thread.currentThread().getId();
            return true;
        } else if (j == Thread.currentThread().getId()) {
            return true;
        } else {
            return false;
        }
    }

    public static void disableVaildThreadAssert(boolean z) {
        mDisableValidThreadAssert.set(z);
    }

    public void execute(Runnable runnable) throws RejectedExecutionException {
        if (runnable != null) {
            try {
                this.mQueue.put(runnable);
            } catch (InterruptedException e) {
                throw new RejectedExecutionException(e);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void loop(int i) throws IOException {
        long nanoTime = System.nanoTime();
        long convert = TimeUnit.NANOSECONDS.convert((long) i, TimeUnit.MILLISECONDS);
        if (this.mLoopFailed) {
            InterruptedIOException interruptedIOException = this.mPriorInterruptedIOException;
            if (interruptedIOException != null) {
                throw interruptedIOException;
            }
            throw this.mPriorRuntimeException;
        } else if (!this.mLoopRunning) {
            this.mLoopRunning = true;
            while (this.mLoopRunning) {
                if (i == 0) {
                    try {
                        take(false, 0).run();
                    } catch (SocketTimeoutException e) {
                        this.mLoopRunning = false;
                        this.mLoopFailed = true;
                        throw e;
                    } catch (InterruptedIOException e2) {
                        this.mLoopRunning = false;
                        this.mLoopFailed = true;
                        this.mPriorInterruptedIOException = e2;
                        throw e2;
                    } catch (RuntimeException e3) {
                        this.mLoopRunning = false;
                        this.mLoopFailed = true;
                        this.mPriorRuntimeException = e3;
                        throw e3;
                    }
                } else {
                    take(true, (convert - System.nanoTime()) + nanoTime).run();
                }
            }
        } else {
            throw new IllegalStateException("Cannot run loop when it is already running.");
        }
    }

    private Runnable take(boolean z, long j) throws InterruptedIOException {
        Runnable runnable;
        if (!z) {
            try {
                runnable = this.mQueue.take();
            } catch (InterruptedException e) {
                InterruptedIOException interruptedIOException = new InterruptedIOException();
                interruptedIOException.initCause(e);
                throw interruptedIOException;
            }
        } else {
            runnable = this.mQueue.poll(j, TimeUnit.NANOSECONDS);
        }
        if (runnable != null) {
            return runnable;
        }
        throw new SocketTimeoutException();
    }
}
