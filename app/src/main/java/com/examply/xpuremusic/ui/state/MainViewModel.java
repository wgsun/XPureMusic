package com.examply.xpuremusic.ui.state;

import com.examply.xpuremusic.data.bean.TestAlbum;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/2/6 10:12
 */
public class MainViewModel extends ViewModel {

    public final ObservableField<String> pageAssetPath = new ObservableField<>();

    public final MutableLiveData<Boolean> notifyCurrentListChanged = new MutableLiveData<>();

    public final MutableLiveData<List<TestAlbum.TestMusic>> list = new MutableLiveData<List<TestAlbum.TestMusic>>();

    {
        pageAssetPath.set("summary.html");
    }
}
