package com.ss.android.vesdk.filterparam;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public class VEVideoCropFilterParam extends VEBaseFilterParam {
    public static final Parcelable.Creator<VEVideoCropFilterParam> CREATOR = new Parcelable.Creator<VEVideoCropFilterParam>() {
        /* class com.ss.android.vesdk.filterparam.VEVideoCropFilterParam.C640161 */

        /* renamed from: a */
        public VEVideoCropFilterParam[] newArray(int i) {
            return new VEVideoCropFilterParam[i];
        }

        /* renamed from: a */
        public VEVideoCropFilterParam createFromParcel(Parcel parcel) {
            return new VEVideoCropFilterParam(parcel);
        }
    };
    public float[] cropNodesCoord;

    @Override // com.ss.android.vesdk.filterparam.VEBaseFilterParam
    public int describeContents() {
        return 0;
    }

    public VEVideoCropFilterParam() {
        this.cropNodesCoord = new float[]{-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        this.filterName = "crop filter";
        this.filterType = 19;
        this.filterDurationType = 1;
    }

    @Override // com.ss.android.vesdk.filterparam.VEBaseFilterParam
    public String toString() {
        return "VEVideoCropFilterParam{cropNodesCoord=" + Arrays.toString(this.cropNodesCoord) + ", filterType=" + this.filterType + ", filterName='" + this.filterName + '\'' + ", filterDurationType=" + this.filterDurationType + '}';
    }

    public VEVideoCropFilterParam(float[] fArr) {
        this();
        if (fArr != null) {
            this.cropNodesCoord = fArr;
        }
    }

    protected VEVideoCropFilterParam(Parcel parcel) {
        super(parcel);
        this.cropNodesCoord = new float[]{-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        this.cropNodesCoord = parcel.createFloatArray();
    }

    @Override // com.ss.android.vesdk.filterparam.VEBaseFilterParam
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloatArray(this.cropNodesCoord);
    }
}
