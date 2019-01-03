package cn.ingxin.emoticions.xlh;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.ingxin.emoticons.emoticons.Emoticon;

public final class XlhEmoticonUtils {
    static LinkedHashMap<String, Integer> emoticonMap = new LinkedHashMap<>();
    private static ArrayList<Emoticon> emoticonList;

    //index
    static {
        emoticonMap.put("[笑哈哈]", R.mipmap.lxh_xiaohaha);
        emoticonMap.put("[好爱哦]", R.mipmap.lxh_haoaio);
        emoticonMap.put("[噢耶]", R.mipmap.lxh_oye);
        emoticonMap.put("[偷乐]", R.mipmap.lxh_toule);
        emoticonMap.put("[泪流满面]", R.mipmap.lxh_leiliumanmian);
        emoticonMap.put("[巨汗]", R.mipmap.lxh_juhan);
        emoticonMap.put("[抠鼻屎]", R.mipmap.lxh_koubishi);
        emoticonMap.put("[求关注]", R.mipmap.lxh_qiuguanzhu);
        emoticonMap.put("[好喜欢]", R.mipmap.lxh_haoxihuan);
        emoticonMap.put("[崩溃]", R.mipmap.lxh_bengkui);
        emoticonMap.put("[好囧]", R.mipmap.lxh_haojiong);
        emoticonMap.put("[震惊]", R.mipmap.lxh_zhenjing);
        emoticonMap.put("[别烦我]", R.mipmap.lxh_biefanwo);
        emoticonMap.put("[不好意思]", R.mipmap.lxh_buhaoyisi);
        emoticonMap.put("[羞嗒嗒]", R.mipmap.lxh_xiudada);
        emoticonMap.put("[得意地笑]", R.mipmap.lxh_deyidexiao);
        emoticonMap.put("[纠结]", R.mipmap.lxh_jiujie);
        emoticonMap.put("[给劲]", R.mipmap.lxh_geijin);
        emoticonMap.put("[悲催]", R.mipmap.lxh_beicui);
        emoticonMap.put("[甩甩手]", R.mipmap.lxh_shuaishuaishou);
        emoticonMap.put("[好棒]", R.mipmap.lxh_haobang);
        emoticonMap.put("[瞧瞧]", R.mipmap.lxh_qiaoqiao);
        emoticonMap.put("[不想上班]", R.mipmap.lxh_buxiangshangban);
        emoticonMap.put("[困死了]", R.mipmap.lxh_kunsile);
        emoticonMap.put("[许愿]", R.mipmap.lxh_xuyuan);
        emoticonMap.put("[丘比特]", R.mipmap.lxh_qiubite);
        emoticonMap.put("[有鸭梨]", R.mipmap.lxh_youyali);
        emoticonMap.put("[想一想]", R.mipmap.lxh_xiangyixiang);
        emoticonMap.put("[躁狂症]", R.mipmap.lxh_zaokuangzheng);
        emoticonMap.put("[转发]", R.mipmap.lxh_zhuanfa);
        emoticonMap.put("[互相膜拜]", R.mipmap.lxh_xianghumobai);
        emoticonMap.put("[雷锋]", R.mipmap.lxh_leifeng);
        emoticonMap.put("[杰克逊]", R.mipmap.lxh_jiekexun);
        emoticonMap.put("[玫瑰]", R.mipmap.lxh_meigui);
        emoticonMap.put("[hold住]", R.mipmap.lxh_holdzhu);
        emoticonMap.put("[群体围观]", R.mipmap.lxh_quntiweiguan);
        emoticonMap.put("[推荐]", R.mipmap.lxh_tuijian);
        emoticonMap.put("[赞啊]", R.mipmap.lxh_zana);
        emoticonMap.put("[被电]", R.mipmap.lxh_beidian);
        emoticonMap.put("[霹雳]", R.mipmap.lxh_pili);
    }

    public static ArrayList<Emoticon> getEmoticonList() {
        if (emoticonList == null) {
            emoticonList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : XlhEmoticonUtils.emoticonMap.entrySet()) {
                emoticonList.add(new Emoticon(entry.getKey(), entry.getValue()));
            }
        }
        return emoticonList;
    }
}
