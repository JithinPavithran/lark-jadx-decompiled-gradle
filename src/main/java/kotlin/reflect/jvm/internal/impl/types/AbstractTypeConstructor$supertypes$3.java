package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor;

/* access modifiers changed from: package-private */
public final class AbstractTypeConstructor$supertypes$3 extends Lambda implements Function1<AbstractTypeConstructor.Supertypes, Unit> {
    final /* synthetic */ AbstractTypeConstructor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AbstractTypeConstructor$supertypes$3(AbstractTypeConstructor abstractTypeConstructor) {
        super(1);
        this.this$0 = abstractTypeConstructor;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(AbstractTypeConstructor.Supertypes supertypes) {
        invoke(supertypes);
        return Unit.INSTANCE;
    }

    public final void invoke(AbstractTypeConstructor.Supertypes supertypes) {
        List list;
        Intrinsics.checkParameterIsNotNull(supertypes, "supertypes");
        List findLoopsInSupertypesAndDisconnect = this.this$0.getSupertypeLoopChecker().findLoopsInSupertypesAndDisconnect(this.this$0, supertypes.getAllSupertypes(), new AbstractTypeConstructor$supertypes$3$resultWithoutCycles$1(this), new AbstractTypeConstructor$supertypes$3$resultWithoutCycles$2(this));
        List<? extends KotlinType> list2 = null;
        if (findLoopsInSupertypesAndDisconnect.isEmpty()) {
            KotlinType defaultSupertypeIfEmpty = this.this$0.defaultSupertypeIfEmpty();
            if (defaultSupertypeIfEmpty != null) {
                list = CollectionsKt.listOf(defaultSupertypeIfEmpty);
            } else {
                list = null;
            }
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            findLoopsInSupertypesAndDisconnect = list;
        }
        this.this$0.getSupertypeLoopChecker().findLoopsInSupertypesAndDisconnect(this.this$0, findLoopsInSupertypesAndDisconnect, new Function1<TypeConstructor, Collection<? extends KotlinType>>(this) {
            /* class kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3.C692692 */
            final /* synthetic */ AbstractTypeConstructor$supertypes$3 this$0;

            {
                this.this$0 = r1;
            }

            public final Collection<KotlinType> invoke(TypeConstructor typeConstructor) {
                Intrinsics.checkParameterIsNotNull(typeConstructor, "it");
                return this.this$0.this$0.computeNeighbours(typeConstructor, true);
            }
        }, new Function1<KotlinType, Unit>(this) {
            /* class kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3.C692703 */
            final /* synthetic */ AbstractTypeConstructor$supertypes$3 this$0;

            {
                this.this$0 = r1;
            }

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType) {
                invoke(kotlinType);
                return Unit.INSTANCE;
            }

            public final void invoke(KotlinType kotlinType) {
                Intrinsics.checkParameterIsNotNull(kotlinType, "it");
                this.this$0.this$0.reportScopesLoopError(kotlinType);
            }
        });
        if (findLoopsInSupertypesAndDisconnect instanceof List) {
            list2 = findLoopsInSupertypesAndDisconnect;
        }
        List<? extends KotlinType> list3 = list2;
        if (list3 == null) {
            list3 = CollectionsKt.toList(findLoopsInSupertypesAndDisconnect);
        }
        supertypes.setSupertypesWithoutCycles(list3);
    }
}
