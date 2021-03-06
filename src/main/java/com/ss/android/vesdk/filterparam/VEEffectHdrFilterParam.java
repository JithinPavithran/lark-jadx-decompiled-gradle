package com.ss.android.vesdk.filterparam;

import android.os.Parcel;
import android.os.Parcelable;

public class VEEffectHdrFilterParam extends VEBaseFilterParam {
    public static final Parcelable.Creator<VEEffectHdrFilterParam> CREATOR = new Parcelable.Creator<VEEffectHdrFilterParam>() {
        /* class com.ss.android.vesdk.filterparam.VEEffectHdrFilterParam.C640051 */

        /* renamed from: a */
        public VEEffectHdrFilterParam[] newArray(int i) {
            return new VEEffectHdrFilterParam[i];
        }

        /* renamed from: a */
        public VEEffectHdrFilterParam createFromParcel(Parcel parcel) {
            return new VEEffectHdrFilterParam(parcel);
        }
    };
    public String filterPath;
    public int hdrType;
    public float intensity;

    @Override // com.ss.android.vesdk.filterparam.VEBaseFilterParam
    public int describeContents() {
        return 0;
    }

    public VEEffectHdrFilterParam() {
        this.filterName = "effect hdr filter";
        this.filterType = 16;
        this.filterPath = "";
        this.hdrType = 1;
        this.intensity = 1.0f;
    }

    @Override // com.ss.android.vesdk.filterparam.VEBaseFilterParam
    public String toString() {
        return "VEEffectHdrFilterParam{filterPath='" + this.filterPath + '\'' + ", hdrType=" + this.hdrType + ", intensity=" + this.intensity + ", filterDurationType=" + this.filterDurationType + '}';
    }

    protected VEEffectHdrFilterParam(Parcel parcel) {
        super(parcel);
        this.filterPath = parcel.readString();
        this.hdrType = parcel.readInt();
        this.intensity = parcel.readFloat();
    }

    @Override // com.ss.android.vesdk.filterparam.VEBaseFilterParam
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.filterPath);
        parcel.writeInt(this.hdrType);
        parcel.writeFloat(this.intensity);
    }
}
