package com.examply.xpuremusic.ui.state;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/1/5 10:57
 */
public class MainActivityViewModel extends ViewModel {

    public final MutableLiveData<Boolean> openDrawer = new MutableLiveData<>();

    public final MutableLiveData<Boolean> allowDrawerOpen = new MutableLiveData<>();

    {
        allowDrawerOpen.setValue(true);
        openDrawer.setValue(false);
    }
}
