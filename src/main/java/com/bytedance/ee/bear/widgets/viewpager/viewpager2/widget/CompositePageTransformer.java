package com.bytedance.ee.bear.widgets.viewpager.viewpager2.widget;

import android.view.View;
import com.bytedance.ee.bear.widgets.viewpager.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public final class CompositePageTransformer implements ViewPager2.PageTransformer {
    private final List<ViewPager2.PageTransformer> mTransformers = new ArrayList();

    public void addTransformer(ViewPager2.PageTransformer pageTransformer) {
        this.mTransformers.add(pageTransformer);
    }

    public void removeTransformer(ViewPager2.PageTransformer pageTransformer) {
        this.mTransformers.remove(pageTransformer);
    }

    @Override // com.bytedance.ee.bear.widgets.viewpager.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f) {
        for (ViewPager2.PageTransformer pageTransformer : this.mTransformers) {
            pageTransformer.transformPage(view, f);
        }
    }
}
