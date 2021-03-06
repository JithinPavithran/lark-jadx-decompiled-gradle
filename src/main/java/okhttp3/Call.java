package okhttp3;

import java.io.IOException;
import okhttp3.internal.connection.StreamAllocation;

public interface Call extends Cloneable {

    public interface Factory {
        Call newCall(Request request);
    }

    void cancel();

    @Override // java.lang.Object
    Call clone();

    void enqueue(Callback callback);

    Response execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    Request request();

    StreamAllocation streamAllocation();
}
