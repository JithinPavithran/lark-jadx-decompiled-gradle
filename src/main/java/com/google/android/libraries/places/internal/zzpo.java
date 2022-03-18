package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzpo;
import com.google.android.libraries.places.internal.zzpr;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzpo<MessageType extends zzpo<MessageType, BuilderType>, BuilderType extends zzpr<MessageType, BuilderType>> implements zzsk {
    protected int zza;

    /* access modifiers changed from: package-private */
    public int zze() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.libraries.places.internal.zzsk
    public final zzpx zzc() {
        try {
            zzqf zzc = zzpx.zzc(zzh());
            zza(zzc.zzb());
            return zzc.zza();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public final byte[] zzd() {
        try {
            byte[] bArr = new byte[zzh()];
            zzqk zza2 = zzqk.zza(bArr);
            zza(zza2);
            zza2.zzb();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "byte array".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(int i) {
        throw new UnsupportedOperationException();
    }

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzrc.zza(iterable);
        if (iterable instanceof zzrr) {
            List<?> zzb = ((zzrr) iterable).zzb();
            zzrr zzrr = (zzrr) list;
            int size = list.size();
            for (Object obj : zzb) {
                if (obj == null) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(zzrr.size() - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    for (int size2 = zzrr.size() - 1; size2 >= size; size2--) {
                        zzrr.remove(size2);
                    }
                    throw new NullPointerException(sb2);
                } else if (obj instanceof zzpx) {
                    zzrr.zza((zzpx) obj);
                } else {
                    zzrr.add((String) obj);
                }
            }
        } else if (iterable instanceof zzst) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
            }
            int size3 = list.size();
            for (T t : iterable) {
                if (t == null) {
                    StringBuilder sb3 = new StringBuilder(37);
                    sb3.append("Element at index ");
                    sb3.append(list.size() - size3);
                    sb3.append(" is null.");
                    String sb4 = sb3.toString();
                    for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                        list.remove(size4);
                    }
                    throw new NullPointerException(sb4);
                }
                list.add(t);
            }
        }
    }
}