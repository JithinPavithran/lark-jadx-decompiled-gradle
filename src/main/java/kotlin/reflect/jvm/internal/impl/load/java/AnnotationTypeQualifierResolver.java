package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

public final class AnnotationTypeQualifierResolver {
    private final boolean disabled;
    private final Jsr305State jsr305State;
    private final MemoizedFunctionToNullable<ClassDescriptor, AnnotationDescriptor> resolvedNicknames;

    public enum QualifierApplicabilityType {
        METHOD_RETURN_TYPE,
        VALUE_PARAMETER,
        FIELD,
        TYPE_USE
    }

    public static final class TypeQualifierWithApplicability {
        private final int applicability;
        private final AnnotationDescriptor typeQualifier;

        public final AnnotationDescriptor component1() {
            return this.typeQualifier;
        }

        public final List<QualifierApplicabilityType> component2() {
            QualifierApplicabilityType[] values = QualifierApplicabilityType.values();
            ArrayList arrayList = new ArrayList();
            for (QualifierApplicabilityType qualifierApplicabilityType : values) {
                if (isApplicableTo(qualifierApplicabilityType)) {
                    arrayList.add(qualifierApplicabilityType);
                }
            }
            return arrayList;
        }

        private final boolean isApplicableConsideringMask(QualifierApplicabilityType qualifierApplicabilityType) {
            if (((1 << qualifierApplicabilityType.ordinal()) & this.applicability) != 0) {
                return true;
            }
            return false;
        }

        private final boolean isApplicableTo(QualifierApplicabilityType qualifierApplicabilityType) {
            if (isApplicableConsideringMask(QualifierApplicabilityType.TYPE_USE) || isApplicableConsideringMask(qualifierApplicabilityType)) {
                return true;
            }
            return false;
        }

        public TypeQualifierWithApplicability(AnnotationDescriptor annotationDescriptor, int i) {
            Intrinsics.checkParameterIsNotNull(annotationDescriptor, "typeQualifier");
            this.typeQualifier = annotationDescriptor;
            this.applicability = i;
        }
    }

    public final boolean getDisabled() {
        return this.disabled;
    }

    private final AnnotationDescriptor resolveTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
            return null;
        }
        return this.resolvedNicknames.invoke(classDescriptor);
    }

    public final ReportLevel resolveJsr305AnnotationState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        ReportLevel resolveJsr305CustomState = resolveJsr305CustomState(annotationDescriptor);
        if (resolveJsr305CustomState != null) {
            return resolveJsr305CustomState;
        }
        return this.jsr305State.getGlobal();
    }

    public final AnnotationDescriptor computeTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (!classDescriptor.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_NICKNAME_FQNAME())) {
            return null;
        }
        for (AnnotationDescriptor annotationDescriptor : classDescriptor.getAnnotations()) {
            AnnotationDescriptor resolveTypeQualifierAnnotation = resolveTypeQualifierAnnotation(annotationDescriptor);
            if (resolveTypeQualifierAnnotation != null) {
                return resolveTypeQualifierAnnotation;
            }
        }
        return null;
    }

    public final ReportLevel resolveJsr305CustomState(AnnotationDescriptor annotationDescriptor) {
        String str;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        Map<String, ReportLevel> user = this.jsr305State.getUser();
        FqName fqName = annotationDescriptor.getFqName();
        if (fqName != null) {
            str = fqName.asString();
        } else {
            str = null;
        }
        ReportLevel reportLevel = user.get(str);
        if (reportLevel != null) {
            return reportLevel;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass != null) {
            return migrationAnnotationStatus(annotationClass);
        }
        return null;
    }

    public final AnnotationDescriptor resolveTypeQualifierAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled() || (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) == null) {
            return null;
        }
        if (AnnotationTypeQualifierResolverKt.isAnnotatedWithTypeQualifier(annotationClass)) {
            return annotationDescriptor;
        }
        return resolveTypeQualifierNickname(annotationClass);
    }

    private final ReportLevel migrationAnnotationStatus(ClassDescriptor classDescriptor) {
        ConstantValue<?> constantValue;
        AnnotationDescriptor findAnnotation = classDescriptor.getAnnotations().findAnnotation(AnnotationTypeQualifierResolverKt.getMIGRATION_ANNOTATION_FQNAME());
        if (findAnnotation != null) {
            constantValue = DescriptorUtilsKt.firstArgument(findAnnotation);
        } else {
            constantValue = null;
        }
        if (!(constantValue instanceof EnumValue)) {
            constantValue = null;
        }
        EnumValue enumValue = (EnumValue) constantValue;
        if (enumValue == null) {
            return null;
        }
        ReportLevel migration = this.jsr305State.getMigration();
        if (migration != null) {
            return migration;
        }
        String asString = enumValue.getEnumEntryName().asString();
        int hashCode = asString.hashCode();
        if (hashCode != -2137067054) {
            if (hashCode != -1838656823) {
                if (hashCode == 2656902 && asString.equals("WARN")) {
                    return ReportLevel.WARN;
                }
                return null;
            } else if (asString.equals("STRICT")) {
                return ReportLevel.STRICT;
            } else {
                return null;
            }
        } else if (asString.equals("IGNORE")) {
            return ReportLevel.IGNORE;
        } else {
            return null;
        }
    }

    public final NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability = AnnotationTypeQualifierResolverKt.getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS().get(annotationDescriptor.getFqName());
        if (nullabilityQualifierWithApplicability == null) {
            return null;
        }
        NullabilityQualifierWithMigrationStatus component1 = nullabilityQualifierWithApplicability.component1();
        Collection<QualifierApplicabilityType> component2 = nullabilityQualifierWithApplicability.component2();
        ReportLevel resolveJsr305AnnotationState = resolveJsr305AnnotationState(annotationDescriptor);
        if (resolveJsr305AnnotationState != ReportLevel.IGNORE) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            resolveJsr305AnnotationState = null;
        }
        if (resolveJsr305AnnotationState != null) {
            return new NullabilityQualifierWithApplicability(NullabilityQualifierWithMigrationStatus.copy$default(component1, null, resolveJsr305AnnotationState.isWarning(), 1, null), component2);
        }
        return null;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private final List<QualifierApplicabilityType> mapConstantToQualifierApplicabilityTypes(ConstantValue<?> constantValue) {
        QualifierApplicabilityType qualifierApplicabilityType;
        if (constantValue instanceof ArrayValue) {
            ArrayList arrayList = new ArrayList();
            for (ConstantValue<?> constantValue2 : (Iterable) ((ArrayValue) constantValue).getValue()) {
                CollectionsKt.addAll(arrayList, mapConstantToQualifierApplicabilityTypes(constantValue2));
            }
            return arrayList;
        } else if (!(constantValue instanceof EnumValue)) {
            return CollectionsKt.emptyList();
        } else {
            String identifier = ((EnumValue) constantValue).getEnumEntryName().getIdentifier();
            switch (identifier.hashCode()) {
                case -2024225567:
                    if (identifier.equals("METHOD")) {
                        qualifierApplicabilityType = QualifierApplicabilityType.METHOD_RETURN_TYPE;
                        break;
                    }
                    qualifierApplicabilityType = null;
                    break;
                case 66889946:
                    if (identifier.equals("FIELD")) {
                        qualifierApplicabilityType = QualifierApplicabilityType.FIELD;
                        break;
                    }
                    qualifierApplicabilityType = null;
                    break;
                case 107598562:
                    if (identifier.equals("TYPE_USE")) {
                        qualifierApplicabilityType = QualifierApplicabilityType.TYPE_USE;
                        break;
                    }
                    qualifierApplicabilityType = null;
                    break;
                case 446088073:
                    if (identifier.equals("PARAMETER")) {
                        qualifierApplicabilityType = QualifierApplicabilityType.VALUE_PARAMETER;
                        break;
                    }
                    qualifierApplicabilityType = null;
                    break;
                default:
                    qualifierApplicabilityType = null;
                    break;
            }
            return CollectionsKt.listOfNotNull(qualifierApplicabilityType);
        }
    }

    public final TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        Object obj;
        boolean z;
        List<QualifierApplicabilityType> list;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (!this.jsr305State.getDisabled() && (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) != null) {
            if (!annotationClass.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_DEFAULT_FQNAME())) {
                annotationClass = null;
            }
            if (annotationClass != null) {
                ClassDescriptor annotationClass2 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
                if (annotationClass2 == null) {
                    Intrinsics.throwNpe();
                }
                AnnotationDescriptor findAnnotation = annotationClass2.getAnnotations().findAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_DEFAULT_FQNAME());
                if (findAnnotation == null) {
                    Intrinsics.throwNpe();
                }
                Map<Name, ConstantValue<?>> allValueArguments = findAnnotation.getAllValueArguments();
                ArrayList<QualifierApplicabilityType> arrayList = new ArrayList();
                for (Map.Entry<Name, ConstantValue<?>> entry : allValueArguments.entrySet()) {
                    ConstantValue<?> value = entry.getValue();
                    if (Intrinsics.areEqual(entry.getKey(), JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                        list = mapConstantToQualifierApplicabilityTypes(value);
                    } else {
                        list = CollectionsKt.emptyList();
                    }
                    CollectionsKt.addAll(arrayList, list);
                }
                int i = 0;
                for (QualifierApplicabilityType qualifierApplicabilityType : arrayList) {
                    i |= 1 << qualifierApplicabilityType.ordinal();
                }
                Iterator it = annotationClass.getAnnotations().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (resolveTypeQualifierAnnotation((AnnotationDescriptor) obj) != null) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        break;
                    }
                }
                AnnotationDescriptor annotationDescriptor2 = (AnnotationDescriptor) obj;
                if (annotationDescriptor2 != null) {
                    return new TypeQualifierWithApplicability(annotationDescriptor2, i);
                }
            }
        }
        return null;
    }

    public AnnotationTypeQualifierResolver(StorageManager storageManager, Jsr305State jsr305State2) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(jsr305State2, "jsr305State");
        this.jsr305State = jsr305State2;
        this.resolvedNicknames = storageManager.createMemoizedFunctionWithNullableValues(new AnnotationTypeQualifierResolver$resolvedNicknames$1(this));
        this.disabled = jsr305State2.getDisabled();
    }
}
