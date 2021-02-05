package com.examply.xpuremusic.ui.page.adapter;

import android.content.Intent;
import android.net.Uri;

import com.examply.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.examply.xpuremusic.data.bean.LibraryInfo;
import com.examply.xpuremusic.databinding.AdapterLibraryBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.examply.xpuremusic.R;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/2/5 13:44
 */
public class DrawerAdapter extends SimpleDataBindingAdapter<LibraryInfo, AdapterLibraryBinding> {


    public DrawerAdapter() {
        super(R.layout.adapter_library, new DiffUtil.ItemCallback<LibraryInfo>() {
            @Override
            public boolean areItemsTheSame(@NonNull LibraryInfo oldItem, @NonNull LibraryInfo newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull LibraryInfo oldItem, @NonNull LibraryInfo newItem) {
                return oldItem.getTitle().equals(newItem.getTitle());
            }
        });
        /*setOnItemClickListener(new OnItemClickListener<LibraryInfo>() {
            @Override
            public void onItemClick(LibraryInfo item, int position) {
                Uri uri = Uri.parse(item.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            }
        });*/
    }

    @Override
    protected void onBindItem(AdapterLibraryBinding binding, LibraryInfo item, RecyclerView.ViewHolder holder) {
        binding.setInfo(item);
    }
}
