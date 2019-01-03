package cn.ingxin.chatkeyboard.lib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import cn.ingxin.chatkeyboard.lib.R;
import cn.ingxin.chatkeyboard.lib.callback.OnItemClickListener;
import cn.ingxin.emoticons.emoticons.Emoticon;

/**
 * 单个页面表情
 */
public class EmoticonAdapter extends RecyclerView.Adapter<EmoticonAdapter.ViewHolder> {

    private List<Emoticon> emoticonList;
    private OnItemClickListener<Emoticon> itemClickListener;

    public EmoticonAdapter(List<Emoticon> emoticonList) {
        this.emoticonList = emoticonList;
    }

    /**
     * set click listener
     */
    public void setOnItemClickListener(OnItemClickListener<Emoticon> listener) {
        this.itemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ckb_emoticon_item, parent, false);
        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Emoticon emoticon = emoticonList.get(position);
        holder.bindData(emoticon, position);
    }

    @Override
    public int getItemCount() {
        return emoticonList == null ? 0 : emoticonList.size();
    }

    /**
     * inner view holder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private Emoticon item;
        private int position;

        private ImageView imageView;

        ViewHolder(View itemView, final OnItemClickListener<Emoticon> itemClickListener) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(item, position);
                    }
                }
            });
        }

        /**
         * 绑定数据
         *
         * @param item     item
         * @param position position
         */
        private void bindData(Emoticon item, int position) {
            this.item = item;
            this.position = position;
            imageView.setImageResource(item.imageRes);
        }
    }


}
