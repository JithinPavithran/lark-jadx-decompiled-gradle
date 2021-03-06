package com.bytedance.apm.agent.instrumentation.transaction;

import com.bytedance.apm.agent.logging.AgentLog;
import com.bytedance.apm.agent.logging.AgentLogManager;
import com.bytedance.apm.util.C3143y;
import org.json.JSONException;
import org.json.JSONObject;

public final class TransactionState {
    private static final AgentLog LOG = AgentLogManager.getAgentLog();
    private long bytesReceived;
    private long bytesSent;
    private String carrier = "unknown";
    private long endTime;
    private int errorCode;
    private JSONObject extraData = new JSONObject();
    private String httpMethod;
    private String rawUrl;
    private long startTime;
    private State state = State.READY;
    private int statusCode;
    private TransactionData transactionData;
    private String url;
    private String wanType = "unknown";

    /* access modifiers changed from: private */
    public enum State {
        READY,
        SENT,
        COMPLETE
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public long getReceivedBytes() {
        return this.bytesReceived;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isComplete() {
        if (this.state.ordinal() >= State.COMPLETE.ordinal()) {
            return true;
        }
        return false;
    }

    public boolean isSent() {
        if (this.state.ordinal() >= State.SENT.ordinal()) {
            return true;
        }
        return false;
    }

    public TransactionData end() {
        addAssistData("startTime", this.startTime + "");
        if (!isComplete()) {
            this.state = State.COMPLETE;
            this.endTime = System.currentTimeMillis();
        }
        return toTransactionData();
    }

    public String toString() {
        return " StartTime " + String.valueOf(this.startTime) + " BytesReceived " + String.valueOf(this.bytesReceived) + " BytesSent " + String.valueOf(this.bytesSent) + " Url " + this.url;
    }

    private TransactionData toTransactionData() {
        if (!isComplete()) {
            LOG.warning("toTransactionData() called on incomplete TransactionState");
        }
        String str = this.url;
        if (str == null) {
            LOG.error("Attempted to convert TransactionData TransactionState instance with no URL into TransactionData TransactionData");
            return null;
        }
        if (this.transactionData == null) {
            TransactionData transactionData2 = new TransactionData(str, this.carrier, this.endTime - this.startTime, this.statusCode, this.errorCode, this.bytesSent, this.bytesReceived, this.wanType, this.httpMethod, this.extraData);
            this.transactionData = transactionData2;
            transactionData2.setRequestEnd(this.endTime);
            this.transactionData.setErrorCode(this.errorCode);
        }
        return this.transactionData;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setBytesReceived(long j) {
        if (!isComplete()) {
            this.bytesReceived = j;
            return;
        }
        AgentLog agentLog = LOG;
        agentLog.warning("setBytesReceived(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public void setBytesSent(long j) {
        if (!isComplete()) {
            this.bytesSent = j;
            this.state = State.SENT;
            return;
        }
        AgentLog agentLog = LOG;
        agentLog.warning("setBytesSent(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public void setCarrier(String str) {
        if (!isSent()) {
            this.carrier = str;
            return;
        }
        AgentLog agentLog = LOG;
        agentLog.warning("setCarrier(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public void setErrorCode(int i) {
        if (!isComplete()) {
            this.errorCode = i;
            return;
        }
        TransactionData transactionData2 = this.transactionData;
        if (transactionData2 != null) {
            transactionData2.setErrorCode(i);
        }
        AgentLog agentLog = LOG;
        agentLog.warning("setErrorCode(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public void setMethod(String str) {
        if (!isSent()) {
            this.httpMethod = str;
            return;
        }
        AgentLog agentLog = LOG;
        agentLog.warning("setHttpMethod(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public void setStatusCode(int i) {
        if (!isComplete()) {
            this.statusCode = i;
            return;
        }
        if (this.statusCode == 0 && i != 0) {
            this.statusCode = i;
        }
        AgentLog agentLog = LOG;
        agentLog.warning("setStatusCode(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public void setUrl(String str) {
        if (this.rawUrl == null) {
            this.rawUrl = str;
        }
        String a = C3143y.m13087a(str);
        if (a != null) {
            if (!isSent()) {
                this.url = a;
                return;
            }
            AgentLog agentLog = LOG;
            agentLog.warning("setUrl(...) called on TransactionState in " + this.state.toString() + " state");
        }
    }

    public void setWanType(String str) {
        if (!isSent()) {
            this.wanType = str;
            return;
        }
        AgentLog agentLog = LOG;
        agentLog.warning("setWanType(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public void addAssistData(String str, String str2) {
        if (isComplete()) {
            AgentLog agentLog = LOG;
            agentLog.warning("addAssistData(...) called on TransactionState in " + this.state.toString() + " state");
        }
        try {
            this.extraData.put(str, str2);
        } catch (JSONException e) {
            LOG.error("Caught error while addAssistData: ", e);
        }
    }
}
