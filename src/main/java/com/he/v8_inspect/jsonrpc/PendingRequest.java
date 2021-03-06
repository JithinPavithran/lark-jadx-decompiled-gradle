package com.he.v8_inspect.jsonrpc;

import javax.annotation.Nullable;

public class PendingRequest {
    @Nullable
    public final PendingRequestCallback callback;
    public final long requestId;

    public PendingRequest(long j, @Nullable PendingRequestCallback pendingRequestCallback) {
        this.requestId = j;
        this.callback = pendingRequestCallback;
    }
}
