package com.ss.android.vc.lark.rvc;

import com.ss.android.lark.openapi.jsapi.entity.BaseJSModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/ss/android/vc/lark/rvc/LogJSModel;", "Lcom/ss/android/lark/openapi/jsapi/entity/BaseJSModel;", "level", "", "message", "", "(ILjava/lang/String;)V", "getLevel", "()I", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "lib_vc_suiteRelease"}, mo238835k = 1, mv = {1, 1, 16})
public final class LogJSModel implements BaseJSModel {
    private final int level;
    private final String message;

    public static /* synthetic */ LogJSModel copy$default(LogJSModel logJSModel, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = logJSModel.level;
        }
        if ((i2 & 2) != 0) {
            str = logJSModel.message;
        }
        return logJSModel.copy(i, str);
    }

    public final int component1() {
        return this.level;
    }

    public final String component2() {
        return this.message;
    }

    public final LogJSModel copy(int i, String str) {
        return new LogJSModel(i, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogJSModel)) {
            return false;
        }
        LogJSModel logJSModel = (LogJSModel) obj;
        return this.level == logJSModel.level && Intrinsics.areEqual(this.message, logJSModel.message);
    }

    public int hashCode() {
        int i = this.level * 31;
        String str = this.message;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "LogJSModel(level=" + this.level + ", message=" + this.message + ")";
    }

    public final int getLevel() {
        return this.level;
    }

    public final String getMessage() {
        return this.message;
    }

    public LogJSModel(int i, String str) {
        this.level = i;
        this.message = str;
    }
}
