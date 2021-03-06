package com.huawei.hms.common.internal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

public abstract class DialogRedirect implements DialogInterface.OnClickListener {
    /* access modifiers changed from: protected */
    public abstract void redirect();

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            redirect();
        } catch (Throwable th) {
            dialogInterface.dismiss();
            throw th;
        }
        dialogInterface.dismiss();
    }

    public static DialogRedirect getInstance(Activity activity, Intent intent, int i) {
        return new DialogRedirectImpl(intent, activity, i);
    }
}
