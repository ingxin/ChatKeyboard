package cn.ingxin.chatkeyboard.lib.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ingxin.chatkeyboard.lib.R;
import cn.ingxin.chatkeyboard.lib.adapter.EmoticonAdapter;
import cn.ingxin.chatkeyboard.lib.callback.OnItemClickListener;
import cn.ingxin.emoticons.emoticons.Emoticon;

/**
 * 单个表情页面内表情展示
 */
public class EmoticonFragment extends Fragment {

    private static final String COLUMN = "column";
    private static final String FACE_LIST = "face_list";
    private static final String CURRENT_PAGE = "current_page";

    //当前页面page
    private int mPage;

    public static EmoticonFragment newInstance(int column, int page, ArrayList<Emoticon> emoticonList) {
        EmoticonFragment fragment = new EmoticonFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(COLUMN, column);
        arguments.putInt(CURRENT_PAGE, page);
        arguments.putParcelableArrayList(FACE_LIST, emoticonList);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ckb_fragment_emoticon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //get data
        Bundle arguments = getArguments();
        int column = arguments.getInt(COLUMN, 7);
        ArrayList<Emoticon> emoticonList = arguments.getParcelableArrayList(FACE_LIST);
        mPage = arguments.getInt(CURRENT_PAGE);

        //inti adapter & recyclerView
        RecyclerView recyclerView = (RecyclerView) view;
        EmoticonAdapter adapter = new EmoticonAdapter(emoticonList);
        adapter.setOnItemClickListener(new OnItemClickListener<Emoticon>() {
            @Override
            public void onItemClick(Emoticon item, int position) {
                Fragment parentFragment = getParentFragment();
                if (parentFragment instanceof XEmoticonPageFragment) {
                    //表情点击事件传递到父类统一处理
                    ((XEmoticonPageFragment) parentFragment).onEmoticonClick(item, mPage, position);
                }
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), column);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
