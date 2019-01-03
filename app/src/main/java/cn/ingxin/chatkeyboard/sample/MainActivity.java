package cn.ingxin.chatkeyboard.sample;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ingxin.chatkeyboard.lib.Kb;
import cn.ingxin.chatkeyboard.lib.callback.OnEmoticonClickListener;
import cn.ingxin.chatkeyboard.lib.data.PageSet;
import cn.ingxin.chatkeyboard.lib.ui.EmoticonPageFragment;
import cn.ingxin.emoticions.xlh.XlhEmoticonUtils;
import cn.ingxin.emoticions.xlh.XlhResolverFactory;
import cn.ingxin.emoticons.def.DefEmoticonUtils;
import cn.ingxin.emoticons.def.DefResolverFactory;
import cn.ingxin.emoticons.emoticons.Emoticon;
import cn.ingxin.emoticons.emoticons.XEmoticon;

public class MainActivity extends AppCompatActivity {

    private ViewGroup faceContainer;
    private Kb mKb;
    private TextView tv;
    private EditText etInput;

    XEmoticon xEmoticon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        faceContainer = (ViewGroup) findViewById(R.id.ckb_face_container);
        etInput = (EditText) findViewById(R.id.et_input);
        findViewById(R.id.emoji).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKb.getKbState() == Kb.KEYBOARD_STATE_BOTH) {
                    mKb.closeSoftKeyboard();
                } else if (mKb.getKbState() == Kb.KEYBOARD_STATE_FUNC) {
                    mKb.hideKb();
                } else if (mKb.getKbState() == Kb.KEYBOARD_STATE_NONE) {
                    mKb.showKb();
                }
            }
        });

        findViewById(R.id.pop_soft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKb.openSoftKeyboard(etInput);
            }
        });

        findViewById(R.id.hide_soft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKb.closeSoftKeyboard();
            }
        });

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etInput.getText().toString();
                xEmoticon.displayEmoticon(tv, s);
            }
        });

        MyOrientationListener listener = new MyOrientationListener(this);
        listener.enable();

        initKb();
    }

    private void initKb() {

        //添加表情解析方法
        xEmoticon = new XEmoticon.Builder()
                .addResolverFactory(XlhResolverFactory.create())
                .addResolverFactory(DefResolverFactory.create())
                .build();

        //显示带表情的文本
        xEmoticon.displayEmoticon(tv, "我是包含表情的文本adaf[好爱哦]，[好爱哦]ss[好爱哦],表情的[抠鼻屎]文本[抠鼻屎],表情[吃惊]");
        xEmoticon.displayEmoticon(etInput, "表情的[抠鼻屎]");

        //将表情库添加到键盘里
        ArrayList<Emoticon> emoticons = DefEmoticonUtils.getEmoticonList();
        ArrayList<Emoticon> emoticons2 = XlhEmoticonUtils.getEmoticonList();

        //第一页的表情
        PageSet pageSet = new PageSet.Builder()
                .setColumn(7)
                .setEmoticons(emoticons)
                .setIcon(R.mipmap.d_weixiao)
                .build();

        //第二页表情
        PageSet pageSet2 = new PageSet.Builder()
                .setColumn(7)
                .setEmoticons(emoticons2)
                .setIcon(R.mipmap.lxh_xiaohaha)
                .build();

        //创建表情管理
        mKb = new Kb.Builder(this)
                .addPageSet(pageSet)
                .addPageSet(pageSet2)
                .setOnEmoticonClickListener(new OnEmoticonClickListener() {
                    @Override
                    public void onEmoticonClick(Emoticon item, int position, int page) {
                        xEmoticon.insert2View(etInput, item);
                    }

                    @Override
                    public void onOperation(String operationName) {
                        if (EmoticonPageFragment.DELETE.equals(operationName)) {
                            XEmoticon.deleteEvent(etInput);
                        }
                    }
                })
                .with(faceContainer)
                //自定义表情展示容器
                .setCustomFragment(EmoticonPageFragment.newInstance())
                .build();
    }

    @Override
    public void onBackPressed() {
        if (!mKb.onBackPressed()) {
            super.onBackPressed();
        }
    }

    /**
     * 方向切换监听
     */
    private class MyOrientationListener extends OrientationEventListener {

        MyOrientationListener(Context context) {
            super(context);
        }

        public MyOrientationListener(Context context, int rate) {
            super(context, rate);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            int screenOrientation = getRequestedOrientation();
            int requestedOrientation = -999;
            if (((orientation >= 0) && (orientation < 45)) || (orientation > 315)) {//设置竖屏
                if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT && orientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                }
            } else if (orientation > 225 && orientation < 315) { //设置横屏
                if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                }
            } else if (orientation > 45 && orientation < 135) {// 设置反向横屏
                if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                }
            } else if (orientation > 135 && orientation < 225) {
                if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                }
            }
            if (requestedOrientation != -999) {
                setRequestedOrientation(requestedOrientation);
            }
        }
    }


}
