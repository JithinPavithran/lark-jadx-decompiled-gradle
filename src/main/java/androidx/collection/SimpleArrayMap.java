package androidx.collection;

import java.util.ConcurrentModificationException;
import java.util.Map;

public class SimpleArrayMap<K, V> {
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;
    Object[] mArray;
    int[] mHashes;
    int mSize;

    public int size() {
        return this.mSize;
    }

    public boolean isEmpty() {
        if (this.mSize <= 0) {
            return true;
        }
        return false;
    }

    public SimpleArrayMap() {
        this.mHashes = C0515b.f1869a;
        this.mArray = C0515b.f1871c;
    }

    public int hashCode() {
        int i;
        int[] iArr = this.mHashes;
        Object[] objArr = this.mArray;
        int i2 = this.mSize;
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            int i6 = iArr[i4];
            if (obj == null) {
                i = 0;
            } else {
                i = obj.hashCode();
            }
            i5 += i ^ i6;
            i4++;
            i3 += 2;
        }
        return i5;
    }

    public void clear() {
        int i = this.mSize;
        if (i > 0) {
            int[] iArr = this.mHashes;
            Object[] objArr = this.mArray;
            this.mHashes = C0515b.f1869a;
            this.mArray = C0515b.f1871c;
            this.mSize = 0;
            freeArrays(iArr, objArr, i);
        }
        if (this.mSize > 0) {
            throw new ConcurrentModificationException();
        }
    }

    /* access modifiers changed from: package-private */
    public int indexOfNull() {
        int i = this.mSize;
        if (i == 0) {
            return -1;
        }
        int binarySearchHashes = binarySearchHashes(this.mHashes, i, 0);
        if (binarySearchHashes < 0 || this.mArray[binarySearchHashes << 1] == null) {
            return binarySearchHashes;
        }
        int i2 = binarySearchHashes + 1;
        while (i2 < i && this.mHashes[i2] == 0) {
            if (this.mArray[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        int i3 = binarySearchHashes - 1;
        while (i3 >= 0 && this.mHashes[i3] == 0) {
            if (this.mArray[i3 << 1] == null) {
                return i3;
            }
            i3--;
        }
        return ~i2;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            K keyAt = keyAt(i);
            if (keyAt != this) {
                sb.append((Object) keyAt);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V valueAt = valueAt(i);
            if (valueAt != this) {
                sb.append((Object) valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public K keyAt(int i) {
        return (K) this.mArray[i << 1];
    }

    public boolean containsKey(Object obj) {
        if (indexOfKey(obj) >= 0) {
            return true;
        }
        return false;
    }

    public boolean containsValue(Object obj) {
        if (indexOfValue(obj) >= 0) {
            return true;
        }
        return false;
    }

    public int indexOfKey(Object obj) {
        if (obj == null) {
            return indexOfNull();
        }
        return indexOf(obj, obj.hashCode());
    }

    public V remove(Object obj) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return removeAt(indexOfKey);
        }
        return null;
    }

    public V valueAt(int i) {
        return (V) this.mArray[(i << 1) + 1];
    }

    public SimpleArrayMap(int i) {
        if (i == 0) {
            this.mHashes = C0515b.f1869a;
            this.mArray = C0515b.f1871c;
        } else {
            allocArrays(i);
        }
        this.mSize = 0;
    }

    public SimpleArrayMap(SimpleArrayMap<K, V> simpleArrayMap) {
        this();
        if (simpleArrayMap != null) {
            putAll(simpleArrayMap);
        }
    }

    public void ensureCapacity(int i) {
        int i2 = this.mSize;
        int[] iArr = this.mHashes;
        if (iArr.length < i) {
            Object[] objArr = this.mArray;
            allocArrays(i);
            if (this.mSize > 0) {
                System.arraycopy(iArr, 0, this.mHashes, 0, i2);
                System.arraycopy(objArr, 0, this.mArray, 0, i2 << 1);
            }
            freeArrays(iArr, objArr, i2);
        }
        if (this.mSize != i2) {
            throw new ConcurrentModificationException();
        }
    }

    /* access modifiers changed from: package-private */
    public int indexOfValue(Object obj) {
        int i = this.mSize * 2;
        Object[] objArr = this.mArray;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: androidx.collection.SimpleArrayMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    public void putAll(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        int i = simpleArrayMap.mSize;
        ensureCapacity(this.mSize + i);
        if (this.mSize != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                put(simpleArrayMap.keyAt(i2), simpleArrayMap.valueAt(i2));
            }
        } else if (i > 0) {
            System.arraycopy(simpleArrayMap.mHashes, 0, this.mHashes, 0, i);
            System.arraycopy(simpleArrayMap.mArray, 0, this.mArray, 0, i << 1);
            this.mSize = i;
        }
    }

    private void allocArrays(int i) {
        if (i == 8) {
            synchronized (SimpleArrayMap.class) {
                Object[] objArr = mTwiceBaseCache;
                if (objArr != null) {
                    this.mArray = objArr;
                    mTwiceBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    mTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (SimpleArrayMap.class) {
                Object[] objArr2 = mBaseCache;
                if (objArr2 != null) {
                    this.mArray = objArr2;
                    mBaseCache = (Object[]) objArr2[0];
                    this.mHashes = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    mBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[i];
        this.mArray = new Object[(i << 1)];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SimpleArrayMap) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
            if (size() != simpleArrayMap.size()) {
                return false;
            }
            for (int i = 0; i < this.mSize; i++) {
                try {
                    K keyAt = keyAt(i);
                    V valueAt = valueAt(i);
                    Object obj2 = simpleArrayMap.get(keyAt);
                    if (valueAt == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(keyAt)) {
                            return false;
                        }
                    } else if (!valueAt.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.mSize; i2++) {
                try {
                    K keyAt2 = keyAt(i2);
                    V valueAt2 = valueAt(i2);
                    Object obj3 = map.get(keyAt2);
                    if (valueAt2 == null) {
                        if (obj3 != null || !map.containsKey(keyAt2)) {
                            return false;
                        }
                    } else if (!valueAt2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public V removeAt(int i) {
        Object[] objArr = this.mArray;
        int i2 = i << 1;
        V v = (V) objArr[i2 + 1];
        int i3 = this.mSize;
        int i4 = 0;
        if (i3 <= 1) {
            freeArrays(this.mHashes, objArr, i3);
            this.mHashes = C0515b.f1869a;
            this.mArray = C0515b.f1871c;
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.mHashes;
            int i6 = 8;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i < i5) {
                    int i7 = i + 1;
                    int i8 = i5 - i;
                    System.arraycopy(iArr, i7, iArr, i, i8);
                    Object[] objArr2 = this.mArray;
                    System.arraycopy(objArr2, i7 << 1, objArr2, i2, i8 << 1);
                }
                Object[] objArr3 = this.mArray;
                int i9 = i5 << 1;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                if (i3 > 8) {
                    i6 = i3 + (i3 >> 1);
                }
                allocArrays(i6);
                if (i3 == this.mSize) {
                    if (i > 0) {
                        System.arraycopy(iArr, 0, this.mHashes, 0, i);
                        System.arraycopy(objArr, 0, this.mArray, 0, i2);
                    }
                    if (i < i5) {
                        int i10 = i + 1;
                        int i11 = i5 - i;
                        System.arraycopy(iArr, i10, this.mHashes, i, i11);
                        System.arraycopy(objArr, i10 << 1, this.mArray, i2, i11 << 1);
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            }
            i4 = i5;
        }
        if (i3 == this.mSize) {
            this.mSize = i4;
            return v;
        }
        throw new ConcurrentModificationException();
    }

    public V getOrDefault(Object obj, V v) {
        int indexOfKey = indexOfKey(obj);
        return indexOfKey >= 0 ? (V) this.mArray[(indexOfKey << 1) + 1] : v;
    }

    public V putIfAbsent(K k, V v) {
        V v2 = get(k);
        if (v2 == null) {
            return put(k, v);
        }
        return v2;
    }

    public V replace(K k, V v) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey >= 0) {
            return setValueAt(indexOfKey, v);
        }
        return null;
    }

    public V setValueAt(int i, V v) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.mArray;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    public boolean remove(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey < 0) {
            return false;
        }
        V valueAt = valueAt(indexOfKey);
        if (obj2 != valueAt && (obj2 == null || !obj2.equals(valueAt))) {
            return false;
        }
        removeAt(indexOfKey);
        return true;
    }

    /* access modifiers changed from: package-private */
    public int indexOf(Object obj, int i) {
        int i2 = this.mSize;
        if (i2 == 0) {
            return -1;
        }
        int binarySearchHashes = binarySearchHashes(this.mHashes, i2, i);
        if (binarySearchHashes < 0 || obj.equals(this.mArray[binarySearchHashes << 1])) {
            return binarySearchHashes;
        }
        int i3 = binarySearchHashes + 1;
        while (i3 < i2 && this.mHashes[i3] == i) {
            if (obj.equals(this.mArray[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        int i4 = binarySearchHashes - 1;
        while (i4 >= 0 && this.mHashes[i4] == i) {
            if (obj.equals(this.mArray[i4 << 1])) {
                return i4;
            }
            i4--;
        }
        return ~i3;
    }

    public V put(K k, V v) {
        int i;
        int i2;
        int i3 = this.mSize;
        if (k == null) {
            i2 = indexOfNull();
            i = 0;
        } else {
            int hashCode = k.hashCode();
            i = hashCode;
            i2 = indexOf(k, hashCode);
        }
        if (i2 >= 0) {
            int i4 = (i2 << 1) + 1;
            Object[] objArr = this.mArray;
            V v2 = (V) objArr[i4];
            objArr[i4] = v;
            return v2;
        }
        int i5 = ~i2;
        int[] iArr = this.mHashes;
        if (i3 >= iArr.length) {
            int i6 = 4;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i6 = 8;
            }
            Object[] objArr2 = this.mArray;
            allocArrays(i6);
            if (i3 == this.mSize) {
                int[] iArr2 = this.mHashes;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr2, 0, this.mArray, 0, objArr2.length);
                }
                freeArrays(iArr, objArr2, i3);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i5 < i3) {
            int[] iArr3 = this.mHashes;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.mArray;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.mSize - i5) << 1);
        }
        int i8 = this.mSize;
        if (i3 == i8) {
            int[] iArr4 = this.mHashes;
            if (i5 < iArr4.length) {
                iArr4[i5] = i;
                Object[] objArr4 = this.mArray;
                int i9 = i5 << 1;
                objArr4[i9] = k;
                objArr4[i9 + 1] = v;
                this.mSize = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    private static int binarySearchHashes(int[] iArr, int i, int i2) {
        try {
            return C0515b.m2327a(iArr, i, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean replace(K k, V v, V v2) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey < 0) {
            return false;
        }
        V valueAt = valueAt(indexOfKey);
        if (valueAt != v && (v == null || !v.equals(valueAt))) {
            return false;
        }
        setValueAt(indexOfKey, v2);
        return true;
    }

    private static void freeArrays(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (SimpleArrayMap.class) {
                if (mTwiceBaseCacheSize < 10) {
                    objArr[0] = mTwiceBaseCache;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    mTwiceBaseCache = objArr;
                    mTwiceBaseCacheSize++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (SimpleArrayMap.class) {
                if (mBaseCacheSize < 10) {
                    objArr[0] = mBaseCache;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    mBaseCache = objArr;
                    mBaseCacheSize++;
                }
            }
        }
    }
}