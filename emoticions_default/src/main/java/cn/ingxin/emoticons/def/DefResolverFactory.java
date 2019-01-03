package cn.ingxin.emoticons.def;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.ingxin.emoticons.emoticons.ResolverFactory;
import cn.ingxin.emoticons.emoticons.pojo.SpanResult;

public class DefResolverFactory implements ResolverFactory {

    private static final Pattern XHS_RANGE = Pattern.compile("\\[[a-zA-Z0-9\\u4e00-\\u9fa5]+\\]");

    private static Matcher getMatcher(CharSequence matchStr) {
        return XHS_RANGE.matcher(matchStr);
    }

    public static DefResolverFactory create() {
        return new DefResolverFactory();
    }

    private DefResolverFactory() {
    }

    @Nullable
    @Override
    public List<SpanResult> scanSpan(@NonNull Context context, @NonNull CharSequence text) {
        List<SpanResult> res = new ArrayList<>();
        Matcher m = getMatcher(text);
        while (m.find()) {
            String key = m.group();
            Drawable drawable = getDrawableFromResource(context, key);
            if (drawable != null) {
                SpanResult sr = new SpanResult();
                sr.start = m.start();
                sr.end = m.end();
                sr.emoticonDrawable = drawable;
                res.add(sr);
            }
        }
        return res;
    }

    /**
     * 从表情资源中获取对应图片
     */
    private Drawable getDrawableFromResource(Context context, String emoticonKey) {
        Integer resId = DefEmoticonUtils.emoticonMap.get(emoticonKey);
        Drawable drawable = null;
        if (resId != null) {
            try {
                Resources resources = context.getResources();
                drawable = Build.VERSION.SDK_INT >= 21 ?
                        resources.getDrawable(resId, null) : resources.getDrawable(resId);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return drawable;
    }


}
