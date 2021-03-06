package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;

public interface JavaMethod extends JavaMember, JavaTypeParameterListOwner {
    JavaAnnotationArgument getAnnotationParameterDefaultValue();

    boolean getHasAnnotationParameterDefaultValue();

    JavaType getReturnType();

    List<JavaValueParameter> getValueParameters();

    public static final class DefaultImpls {
        public static boolean getHasAnnotationParameterDefaultValue(JavaMethod javaMethod) {
            if (javaMethod.getAnnotationParameterDefaultValue() != null) {
                return true;
            }
            return false;
        }
    }
}
