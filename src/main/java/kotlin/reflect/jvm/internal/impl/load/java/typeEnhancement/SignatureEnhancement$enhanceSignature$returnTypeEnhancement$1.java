package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* access modifiers changed from: package-private */
public final class SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1 extends Lambda implements Function1<CallableMemberDescriptor, KotlinType> {
    public static final SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1 INSTANCE = new SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1();

    SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1() {
        super(1);
    }

    public final KotlinType invoke(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "it");
        KotlinType returnType = callableMemberDescriptor.getReturnType();
        if (returnType == null) {
            Intrinsics.throwNpe();
        }
        return returnType;
    }
}
