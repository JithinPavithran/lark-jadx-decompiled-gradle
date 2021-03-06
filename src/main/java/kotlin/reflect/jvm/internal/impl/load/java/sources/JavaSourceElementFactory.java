package kotlin.reflect.jvm.internal.impl.load.java.sources;

import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;

public interface JavaSourceElementFactory {
    JavaSourceElement source(JavaElement javaElement);
}
