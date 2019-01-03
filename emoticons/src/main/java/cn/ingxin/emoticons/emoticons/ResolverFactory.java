package cn.ingxin.emoticons.emoticons;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import cn.ingxin.emoticons.emoticons.pojo.SpanResult;

/**
 * 表情解析器
 */
public interface ResolverFactory {

    /**
     * 解析表情文本，替换表情的部分
     *
     * <p>
     * 找到文本中的表情并封装到{@link SpanResult}中，最终将所有结果封装到List中。
     * 例如解析“示例文本[哈哈]，表情[大笑]”，首先找到“[哈哈]”在文本中的start position 和
     * end position同时需要创建对应表情的drawable，然后封装到{@link SpanResult}，“[大笑]”同理
     * 所有{@link SpanResult}存储在List中返回
     * </p>
     *
     * @param context context
     * @param text    需要解析文本
     */
    @Nullable
    List<SpanResult> scanSpan(@NonNull Context context, @NonNull CharSequence text);

}
