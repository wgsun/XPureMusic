package com.examply.xpuremusic;

import android.os.Bundle;
import android.view.View;
import com.examply.architecture.ui.base.DataBindingConfig;
import com.examply.xpuremusic.ui.base.BaseActivity;
import com.examply.xpuremusic.ui.state.MainActivityViewModel;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends BaseActivity {

    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void initViewModel() {
        mMainActivityViewModel = getActivityScopeViewModel(MainActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, BR.vm, mMainActivityViewModel)
                .addBindingParam(BR.event, new EventHandler());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class EventHandler extends DrawerLayout.SimpleDrawerListener{

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);

        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);

        }
    }
}