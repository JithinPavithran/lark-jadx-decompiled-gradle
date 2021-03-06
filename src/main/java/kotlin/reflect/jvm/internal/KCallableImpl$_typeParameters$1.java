package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0006\b\u0000\u0010\u0003 \u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KTypeParameterImpl;", "R", "invoke"}, mo238835k = 3, mv = {1, 1, 16})
final class KCallableImpl$_typeParameters$1 extends Lambda implements Function0<List<? extends KTypeParameterImpl>> {
    final /* synthetic */ KCallableImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KCallableImpl$_typeParameters$1(KCallableImpl kCallableImpl) {
        super(0);
        this.this$0 = kCallableImpl;
    }

    /* Return type fixed from 'java.util.List<kotlin.reflect.jvm.internal.KTypeParameterImpl>' to match base method */
    @Override // kotlin.jvm.functions.Function0
    public final List<? extends KTypeParameterImpl> invoke() {
        List<TypeParameterDescriptor> typeParameters = this.this$0.getDescriptor().getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "descriptor.typeParameters");
        List<TypeParameterDescriptor> list = typeParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new KTypeParameterImpl(it.next()));
        }
        return arrayList;
    }
}
