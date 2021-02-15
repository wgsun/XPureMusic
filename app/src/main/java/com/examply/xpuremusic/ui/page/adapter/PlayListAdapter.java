package com.examply.xpuremusic.ui.page.adapter;

import android.graphics.Color;

import com.examply.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.examply.xpuremusic.data.bean.TestAlbum;
import com.examply.xpuremusic.databinding.AdapterPlayItemBinding;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.examply.xpuremusic.R;
import com.examply.xpuremusic.player.PlayerManager;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/2/6 15:12
 */
public class PlayListAdapter extends SimpleDataBindingAdapter<TestAlbum.TestMusic, AdapterPlayItemBinding> {


    public PlayListAdapter() {
        super(R.layout.adapter_play_item, new DiffUtil.ItemCallback<TestAlbum.TestMusic>() {
            @Override
            public boolean areItemsTheSame(@NonNull TestAlbum.TestMusic oldItem, @NonNull TestAlbum.TestMusic newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull TestAlbum.TestMusic oldItem, @NonNull TestAlbum.TestMusic newItem) {
                return oldItem.getMusicId().equals(newItem.getMusicId());
            }
        });
        setOnItemClickListener((item, position) -> {
            PlayerManager.getInstance().playAudio(position);
        });
    }

    @Override
    protected void onBindItem(AdapterPlayItemBinding binding, TestAlbum.TestMusic item, RecyclerView.ViewHolder holder) {
        binding.setAlbum(item);
        int currentIndex = PlayerManager.getInstance().getAlbumIndex();
        binding.ivPlayStatus.setColor(currentIndex == holder.getAbsoluteAdapterPosition()
                ? binding.getRoot().getContext().getColor(R.color.gray) : Color.TRANSPARENT);
    }
}
