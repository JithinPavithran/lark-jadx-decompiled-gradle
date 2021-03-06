package com.ttnet.org.chromium.base.task;

import java.util.concurrent.atomic.AtomicInteger;

public class SequencedTaskRunnerImpl extends TaskRunnerImpl implements SequencedTaskRunner {
    private int mNumUnfinishedNativeTasks;
    private AtomicInteger mPendingTasks = new AtomicInteger();

    @Override // com.ttnet.org.chromium.base.task.TaskRunnerImpl, com.ttnet.org.chromium.base.task.TaskRunner
    public void initNativeTaskRunner() {
        synchronized (this.mLock) {
            migratePreNativeTasksToNative();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.ttnet.org.chromium.base.task.TaskRunnerImpl
    public void runPreNativeTask() {
        super.runPreNativeTask();
        if (this.mPendingTasks.decrementAndGet() > 0) {
            super.schedulePreNativeTask();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.ttnet.org.chromium.base.task.TaskRunnerImpl
    public void schedulePreNativeTask() {
        if (this.mPendingTasks.getAndIncrement() == 0) {
            super.schedulePreNativeTask();
        }
    }

    SequencedTaskRunnerImpl(TaskTraits taskTraits) {
        super(taskTraits, "SequencedTaskRunnerImpl", 1);
        disableLifetimeCheck();
    }

    public /* synthetic */ void lambda$postDelayedTaskToNative$0$SequencedTaskRunnerImpl(Runnable runnable) {
        runnable.run();
        synchronized (this.mLock) {
            int i = this.mNumUnfinishedNativeTasks - 1;
            this.mNumUnfinishedNativeTasks = i;
            if (i == 0) {
                destroyInternal();
            }
        }
    }

    @Override // com.ttnet.org.chromium.base.task.TaskRunnerImpl
    public void postDelayedTaskToNative(Runnable runnable, long j) {
        synchronized (this.mLock) {
            int i = this.mNumUnfinishedNativeTasks;
            this.mNumUnfinishedNativeTasks = i + 1;
            if (i == 0) {
                initNativeTaskRunnerInternal();
            }
            super.postDelayedTaskToNative(new Runnable(runnable) {
                /* class com.ttnet.org.chromium.base.task.$$Lambda$SequencedTaskRunnerImpl$MM54Z1xumXITAO7WxpmXTKoSlTk */
                public final /* synthetic */ Runnable f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SequencedTaskRunnerImpl.this.lambda$postDelayedTaskToNative$0$SequencedTaskRunnerImpl(this.f$1);
                }
            }, j);
        }
    }
}
