package kotlin.reflect.jvm.internal.impl.load.java.sources;

import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;

public interface JavaSourceElement extends SourceElement {
    JavaElement getJavaElement();
}
