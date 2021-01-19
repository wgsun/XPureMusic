package com.examply.xpuremusic.ui.base;

import android.content.res.Resources;
import android.os.Bundle;

import com.examply.architecture.data.response.manager.NetworkStateManager;
import com.examply.architecture.ui.base.DataBindingActivity;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

/**
 * @author wgsun
 * @descrbe
 * @since 2020/12/30 10:18
 */
public abstract class BaseActivity extends DataBindingActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        //BarUtils.setStatusBarLightMode(this, true);
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(NetworkStateManager.getInstance());
    }

    protected <T extends ViewModel> T getActivityScopeViewModel(@Nullable Class<T> modelClass) {
        return super.getActivityScopeViewModel(modelClass);
    }

    protected <T extends ViewModel> T getApplicationScopeViewModel(@Nullable Class<T> modelClass) {
        return super.getApplicationScopeViewModel(modelClass);
    }

    /**
     * todo 屏幕适配方案添加
     * @return
     */
    @Override
    public Resources getResources() {
        return super.getResources();
    }

}
