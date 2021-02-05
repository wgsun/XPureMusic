package com.examply.xpuremusic.ui.state;

import com.examply.xpuremusic.data.bean.LibraryInfo;
import com.examply.xpuremusic.domain.InfoRequest;

import java.util.List;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/1/5 13:43
 */
public class DrawerViewModel extends ViewModel {

    //TODO 此处用于绑定的状态，使用 LiveData 而不是 ObservableField，主要是考虑到 ObservableField 具有防抖的特性，不适合该场景。
    //如果这么说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350

    public final MutableLiveData<List<LibraryInfo>> list = new MutableLiveData<>();

    //TODO tip 2：将 request 作为 ViewModel 的成员暴露给 Activity/Fragment，
    // 如此便于语义的明确，以及实现多个 request 在 ViewModel 中的组合和复用。

    public final InfoRequest infoRequest = new InfoRequest();


}
