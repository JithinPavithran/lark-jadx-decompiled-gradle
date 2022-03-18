package kotlin.reflect.jvm.internal.impl.load.java.lazy;

public interface JavaResolverSettings {
    public static final Companion Companion = Companion.$$INSTANCE;

    boolean isReleaseCoroutines();

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    public static final class Default implements JavaResolverSettings {
        public static final Default INSTANCE = new Default();

        @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings
        public boolean isReleaseCoroutines() {
            return false;
        }

        private Default() {
        }
    }
}