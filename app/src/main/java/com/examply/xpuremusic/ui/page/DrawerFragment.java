package com.examply.xpuremusic.ui.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.examply.architecture.ui.adapter.BaseDataBindingAdapter;
import com.examply.architecture.ui.base.DataBindingConfig;
import com.examply.xpuremusic.BR;
import com.examply.xpuremusic.R;
import com.examply.xpuremusic.data.bean.LibraryInfo;
import com.examply.xpuremusic.ui.base.BaseFragment;
import com.examply.xpuremusic.ui.page.adapter.DrawerAdapter;
import com.examply.xpuremusic.ui.state.DrawerViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/1/5 11:40
 */
public class DrawerFragment extends BaseFragment {

    private DrawerViewModel drawerViewModel;

    @Override
    protected void initViewModel() {
        drawerViewModel = getFragmentScopeViewModel(DrawerViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {

        DrawerAdapter drawerAdapter = new DrawerAdapter();
        drawerAdapter.setOnItemClickListener(new BaseDataBindingAdapter.OnItemClickListener<LibraryInfo>() {
            @Override
            public void onItemClick(LibraryInfo item, int position) {
                Uri uri = Uri.parse(item.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                getContext().startActivity(intent);
            }
        });
        return new DataBindingConfig(R.layout.fragment_drawer, BR.vm, drawerViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.adapter, new DrawerAdapter());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doObserver();
        doBusiness();
    }

    private void doBusiness() {
        drawerViewModel.infoRequest.requestLibraryInfo();
    }

    private void doObserver() {
        drawerViewModel.infoRequest.getLibraryLiveData().observe(getViewLifecycleOwner(), new Observer<List<LibraryInfo>>() {
            @Override
            public void onChanged(List<LibraryInfo> libraryInfos) {
                drawerViewModel.list.setValue(libraryInfos);
            }
        });
    }

    public class ClickProxy{
        public void logoClick() {
            openUrlInBrowser("https://github.com/KunMinX/Jetpack-MVVM-Best-Practice");
        }
    }
}
