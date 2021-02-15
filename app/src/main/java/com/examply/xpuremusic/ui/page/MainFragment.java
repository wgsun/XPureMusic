package com.examply.xpuremusic.ui.page;

import android.os.Bundle;
import android.view.View;

import com.examply.architecture.ui.base.DataBindingConfig;
import com.examply.xpuremusic.BR;
import com.examply.xpuremusic.ui.base.BaseFragment;
import com.examply.xpuremusic.ui.page.adapter.PlayListAdapter;
import com.examply.xpuremusic.ui.state.MainViewModel;
import com.examply.xpuremusic.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/2/5 17:27
 */
public class MainFragment extends BaseFragment {

    private MainViewModel mMainState;

    @Override
    protected void initViewModel() {
        mMainState = getFragmentScopeViewModel(MainViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_main, BR.vm, mMainState)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.adapter, new PlayListAdapter());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public class ClickProxy{

        public void openMenu() {

        }

        public void login() {

        }

        public void search() {

        }
    }
}
