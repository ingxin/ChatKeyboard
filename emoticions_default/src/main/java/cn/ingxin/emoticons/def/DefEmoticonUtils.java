package cn.ingxin.emoticons.def;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.ingxin.emoticon.def.R;
import cn.ingxin.emoticons.emoticons.Emoticon;

final public class DefEmoticonUtils {
    static LinkedHashMap<String, Integer> emoticonMap = new LinkedHashMap<>();
    private static ArrayList<Emoticon> emoticonList;

    //index
    static {
        emoticonMap.put("[微笑]", R.mipmap.d_weixiao);
        emoticonMap.put("[嘻嘻]", R.mipmap.d_xixi);
        emoticonMap.put("[哈哈]", R.mipmap.d_haha);
        emoticonMap.put("[爱你]", R.mipmap.d_aini);
        emoticonMap.put("[挖鼻]", R.mipmap.d_wabishi);
        emoticonMap.put("[吃惊]", R.mipmap.d_chijing);
        emoticonMap.put("[晕]", R.mipmap.d_yun);
        emoticonMap.put("[泪]", R.mipmap.d_lei);
        emoticonMap.put("[馋嘴]", R.mipmap.d_chanzui);
        emoticonMap.put("[抓狂]", R.mipmap.d_zhuakuang);
        emoticonMap.put("[哼]", R.mipmap.d_heng);
        emoticonMap.put("[可爱]", R.mipmap.d_keai);
        emoticonMap.put("[怒]", R.mipmap.d_nu);
        emoticonMap.put("[汗]", R.mipmap.d_han);
        emoticonMap.put("[害羞]", R.mipmap.d_haixiu);
        emoticonMap.put("[睡]", R.mipmap.d_shuijiao);
        emoticonMap.put("[钱]", R.mipmap.d_qian);
        emoticonMap.put("[偷笑]", R.mipmap.d_touxiao);
        emoticonMap.put("[笑cry]", R.mipmap.d_xiaoku);
        emoticonMap.put("[酷]", R.mipmap.d_ku);
        emoticonMap.put("[衰]", R.mipmap.d_shuai);
        emoticonMap.put("[闭嘴]", R.mipmap.d_bizui);
        emoticonMap.put("[鄙视]", R.mipmap.d_bishi);
        emoticonMap.put("[色]", R.mipmap.d_huaxin);
        emoticonMap.put("[鼓掌]", R.mipmap.d_guzhang);
        emoticonMap.put("[悲伤]", R.mipmap.d_beishang);
        emoticonMap.put("[思考]", R.mipmap.d_sikao);
        emoticonMap.put("[生病]", R.mipmap.d_shengbing);
        emoticonMap.put("[亲亲]", R.mipmap.d_qinqin);
        emoticonMap.put("[怒骂]", R.mipmap.d_numa);
        emoticonMap.put("[太开心]", R.mipmap.d_taikaixin);
        emoticonMap.put("[白眼]", R.mipmap.d_landelini);
        emoticonMap.put("[右哼哼]", R.mipmap.d_youhengheng);
        emoticonMap.put("[左哼哼]", R.mipmap.d_zuohengheng);
        emoticonMap.put("[嘘]", R.mipmap.d_xu);
        emoticonMap.put("[委屈]", R.mipmap.d_weiqu);
        emoticonMap.put("[吐]", R.mipmap.d_tu);
        emoticonMap.put("[可怜]", R.mipmap.d_kelian);
        emoticonMap.put("[哈欠]", R.mipmap.d_dahaqi);
        emoticonMap.put("[失望]", R.mipmap.d_shiwang);
        emoticonMap.put("[顶]", R.mipmap.d_ding);
        emoticonMap.put("[疑问]", R.mipmap.d_yiwen);
        emoticonMap.put("[困]", R.mipmap.d_kun);
        emoticonMap.put("[感冒]", R.mipmap.d_ganmao);
        emoticonMap.put("[拜拜]", R.mipmap.d_baibai);
        emoticonMap.put("[黑线]", R.mipmap.d_heixian);
        emoticonMap.put("[阴险]", R.mipmap.d_yinxian);
        emoticonMap.put("[打脸]", R.mipmap.d_dalian);
        emoticonMap.put("[傻眼]", R.mipmap.d_shayan);
        emoticonMap.put("[互粉]", R.mipmap.d_hufen);
        emoticonMap.put("[心]", R.mipmap.d_xin);
        emoticonMap.put("[伤心]", R.mipmap.d_shangxin);
        emoticonMap.put("[doge]", R.mipmap.d_doge);
        emoticonMap.put("[喵喵]", R.mipmap.d_miao);
        emoticonMap.put("[猪头]", R.mipmap.d_zhutou);
        emoticonMap.put("[熊猫]", R.mipmap.d_xiongmao);
        emoticonMap.put("[兔子]", R.mipmap.d_tuzi);
        emoticonMap.put("[握手]", R.mipmap.d_woshou);
        emoticonMap.put("[作揖]", R.mipmap.d_zuoyi);
        emoticonMap.put("[赞]", R.mipmap.d_zan);
        emoticonMap.put("[耶]", R.mipmap.d_ye);
        emoticonMap.put("[good]", R.mipmap.d_good);
        emoticonMap.put("[弱]", R.mipmap.d_ruo);
        emoticonMap.put("[NO]", R.mipmap.d_buyao);
        emoticonMap.put("[ok]", R.mipmap.d_ok);
        emoticonMap.put("[haha]", R.mipmap.d_shouaini);
        emoticonMap.put("[来]", R.mipmap.d_lai);
        emoticonMap.put("[拳头]", R.mipmap.d_quantou);
        emoticonMap.put("[威武]", R.mipmap.d_v5);
        emoticonMap.put("[鲜花]", R.mipmap.d_xianhua);
        emoticonMap.put("[钟]", R.mipmap.d_zhong);
        emoticonMap.put("[浮云]", R.mipmap.d_fuyun);
        emoticonMap.put("[飞机]", R.mipmap.d_feiji);
        emoticonMap.put("[月亮]", R.mipmap.d_yueliang);
        emoticonMap.put("[太阳]", R.mipmap.d_taiyang);
        emoticonMap.put("[微风]", R.mipmap.d_weifeng);
        emoticonMap.put("[下雨]", R.mipmap.d_xiayu);
        emoticonMap.put("[给力]", R.mipmap.d_geili);
        emoticonMap.put("[神马]", R.mipmap.d_shenma);
        emoticonMap.put("[围观]", R.mipmap.d_weiguan);
        emoticonMap.put("[话筒]", R.mipmap.d_huatong);
        emoticonMap.put("[奥特曼]", R.mipmap.d_aoteman);
        emoticonMap.put("[草泥马]", R.mipmap.d_shenshou);
        emoticonMap.put("[萌]", R.mipmap.d_meng);
        emoticonMap.put("[囧]", R.mipmap.d_jiong);
        emoticonMap.put("[织]", R.mipmap.d_zhi);
        emoticonMap.put("[礼物]", R.mipmap.d_liwu);
        emoticonMap.put("[喜]", R.mipmap.d_xi);
        emoticonMap.put("[围脖]", R.mipmap.d_weibo);
        emoticonMap.put("[音乐]", R.mipmap.d_yinyue);
        emoticonMap.put("[绿丝带]", R.mipmap.d_lvsidai);
        emoticonMap.put("[蛋糕]", R.mipmap.d_dangao);
        emoticonMap.put("[蜡烛]", R.mipmap.d_lazhu);
        emoticonMap.put("[干杯]", R.mipmap.d_ganbei);
        emoticonMap.put("[男孩儿]", R.mipmap.d_nanhaier);
        emoticonMap.put("[女孩儿]", R.mipmap.d_nvhaier);
        emoticonMap.put("[肥皂]", R.mipmap.d_feizao);
        emoticonMap.put("[照相机]", R.mipmap.d_zhaoxiangji);
        emoticonMap.put("[浪]", R.mipmap.d_lang);
        emoticonMap.put("[沙尘暴]", R.mipmap.d_shachenbao);
        emoticonMap.put("[马到成功]", R.mipmap.d_madaochenggong);
        emoticonMap.put("[吃元宵]", R.mipmap.d_chitangyuan);
        emoticonMap.put("[马上有对象]", R.mipmap.d_duixiang);
        emoticonMap.put("[发红包]", R.mipmap.d_fahongbao);
        emoticonMap.put("[炸鸡和啤酒]", R.mipmap.d_zhajipijiu);
        emoticonMap.put("[带着微博去旅行]", R.mipmap.d_travel);
        emoticonMap.put("[最右]", R.mipmap.d_zuiyou);
    }

    public static ArrayList<Emoticon> getEmoticonList() {
        if (emoticonList == null) {
            emoticonList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : DefEmoticonUtils.emoticonMap.entrySet()) {
                emoticonList.add(new Emoticon(entry.getKey(), entry.getValue()));
            }
        }
        return emoticonList;
    }

}
