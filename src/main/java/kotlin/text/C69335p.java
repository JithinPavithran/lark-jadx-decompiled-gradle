package kotlin.text;

import java.util.regex.Pattern;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b¨\u0006\u0003"}, d2 = {"toRegex", "Lkotlin/text/Regex;", "Ljava/util/regex/Pattern;", "kotlin-stdlib"}, mo238835k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/text/StringsKt")
/* renamed from: kotlin.text.p */
class C69335p extends C69331o {
    private static final Regex toRegex(Pattern pattern) {
        return new Regex(pattern);
    }
}
