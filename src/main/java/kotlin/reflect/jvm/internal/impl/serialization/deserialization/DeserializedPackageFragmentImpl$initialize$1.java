package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

final class DeserializedPackageFragmentImpl$initialize$1 extends Lambda implements Function0<List<? extends Name>> {
    final /* synthetic */ DeserializedPackageFragmentImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DeserializedPackageFragmentImpl$initialize$1(DeserializedPackageFragmentImpl deserializedPackageFragmentImpl) {
        super(0);
        this.this$0 = deserializedPackageFragmentImpl;
    }

    /* Return type fixed from 'java.util.List<kotlin.reflect.jvm.internal.impl.name.Name>' to match base method */
    @Override // kotlin.jvm.functions.Function0
    public final List<? extends Name> invoke() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        for (T t : this.this$0.getClassDataFinder().getAllClassIds()) {
            T t2 = t;
            if (t2.isNestedClass() || ClassDeserializer.Companion.getBLACK_LIST().contains(t2)) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                arrayList.add(t);
            }
        }
        ArrayList<ClassId> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (ClassId classId : arrayList2) {
            arrayList3.add(classId.getShortClassName());
        }
        return arrayList3;
    }
}
