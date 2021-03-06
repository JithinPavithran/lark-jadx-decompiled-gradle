package com.huawei.hms.framework.common;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.android.os.BuildEx;
import com.huawei.libcore.io.ExternalStorageFile;
import com.huawei.libcore.io.ExternalStorageFileInputStream;
import com.huawei.libcore.io.ExternalStorageFileOutputStream;
import com.huawei.libcore.io.ExternalStorageRandomAccessFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CreateFileUtil {
    private static final String BUILDEX_NAME = "com.huawei.android.os.BuildEx";
    private static final String EXTERNAL_FILE_NAME = "com.huawei.libcore.io.ExternalStorageFile";
    private static final String EXTERNAL_INPUTSTREAM_NAME = "com.huawei.libcore.io.ExternalStorageFileInputStream";
    private static final String EXTERNAL_OUTPUTSTREAM_NAME = "com.huawei.libcore.io.ExternalStorageFileOutputStream";
    private static final String RANDOM_ACCESS_FILE_NAME = "com.huawei.libcore.io.ExternalStorageRandomAccessFile";
    private static final String TAG = "CreateFileUtil";

    private static boolean checkCompatible(String str) {
        try {
            tryLoadClass(str);
            return true;
        } catch (Exception unused) {
            Logger.m85375w(TAG, str + "ClassNotFoundException");
            return false;
        }
    }

    public static void deleteSecure(File file) {
        if (file != null && file.exists() && !file.delete()) {
            Logger.m85375w(TAG, "deleteSecure exception");
        }
    }

    public static void deleteSecure(String str) {
        if (!TextUtils.isEmpty(str)) {
            deleteSecure(newFile(str));
        }
    }

    public static String getCacheDirPath(Context context) {
        if (context == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 24) {
            context = context.createDeviceProtectedStorageContext();
        }
        return context.getCacheDir().getPath();
    }

    public static String getCanonicalPath(String str) {
        String str2;
        Throwable e;
        try {
            return newFile(str).getCanonicalPath();
        } catch (IOException e2) {
            e = e2;
            str2 = "the canonicalPath has IOException";
        } catch (SecurityException e3) {
            e = e3;
            str2 = "the canonicalPath has securityException";
        } catch (Exception e4) {
            e = e4;
            str2 = "the canonicalPath has other Exception";
        }
        Logger.m85376w(TAG, str2, e);
        return str;
    }

    public static boolean isPVersion() {
        return checkCompatible(BUILDEX_NAME) && BuildEx.VERSION.EMUI_SDK_INT >= 17;
    }

    public static File newFile(String str) {
        if (str == null) {
            return null;
        }
        return isPVersion() ? checkCompatible(EXTERNAL_FILE_NAME) ? new ExternalStorageFile(str) : new File(str) : new File(str);
    }

    public static FileInputStream newFileInputStream(String str) {
        if (str != null) {
            return isPVersion() ? checkCompatible(EXTERNAL_INPUTSTREAM_NAME) ? new ExternalStorageFileInputStream(str) : new FileInputStream(str) : new FileInputStream(str);
        }
        Logger.m85375w(TAG, "newFileInputStream  file is null");
        throw new FileNotFoundException("file is null");
    }

    public static FileOutputStream newFileOutputStream(File file) {
        if (file != null) {
            return isPVersion() ? checkCompatible(EXTERNAL_OUTPUTSTREAM_NAME) ? new ExternalStorageFileOutputStream(file) : new FileOutputStream(file) : new FileOutputStream(file);
        }
        Logger.m85368e(TAG, "newFileOutputStream  file is null");
        throw new FileNotFoundException("file is null");
    }

    public static RandomAccessFile newRandomAccessFile(String str, String str2) {
        if (str != null) {
            return isPVersion() ? checkCompatible(RANDOM_ACCESS_FILE_NAME) ? new ExternalStorageRandomAccessFile(str, str2) : new RandomAccessFile(str, str2) : new RandomAccessFile(str, str2);
        }
        Logger.m85375w(TAG, "newFileOutputStream  file is null");
        throw new FileNotFoundException("file is null");
    }

    private static void tryLoadClass(String str) {
        ClassLoader classLoader = CreateFileUtil.class.getClassLoader();
        if (classLoader != null) {
            classLoader.loadClass(str);
            return;
        }
        throw new ClassNotFoundException("not found classloader");
    }
}
