package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

public class ParcelableSparseBooleanArray extends SparseBooleanArray implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseBooleanArray> CREATOR = new Parcelable.Creator<ParcelableSparseBooleanArray>() {
        /* class com.google.android.material.internal.ParcelableSparseBooleanArray.C223241 */

        /* renamed from: a */
        public ParcelableSparseBooleanArray[] newArray(int i) {
            return new ParcelableSparseBooleanArray[i];
        }

        /* renamed from: a */
        public ParcelableSparseBooleanArray createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            ParcelableSparseBooleanArray parcelableSparseBooleanArray = new ParcelableSparseBooleanArray(readInt);
            int[] iArr = new int[readInt];
            boolean[] zArr = new boolean[readInt];
            parcel.readIntArray(iArr);
            parcel.readBooleanArray(zArr);
            for (int i = 0; i < readInt; i++) {
                parcelableSparseBooleanArray.put(iArr[i], zArr[i]);
            }
            return parcelableSparseBooleanArray;
        }
    };

    public int describeContents() {
        return 0;
    }

    public ParcelableSparseBooleanArray() {
    }

    public ParcelableSparseBooleanArray(int i) {
        super(i);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int[] iArr = new int[size()];
        boolean[] zArr = new boolean[size()];
        for (int i2 = 0; i2 < size(); i2++) {
            iArr[i2] = keyAt(i2);
            zArr[i2] = valueAt(i2);
        }
        parcel.writeInt(size());
        parcel.writeIntArray(iArr);
        parcel.writeBooleanArray(zArr);
    }
}
