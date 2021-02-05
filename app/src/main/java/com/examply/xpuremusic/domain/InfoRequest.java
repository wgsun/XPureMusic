package com.examply.xpuremusic.domain;

import com.examply.architecture.data.response.DataResult;
import com.examply.architecture.data.response.NetState;
import com.examply.architecture.domain.BaseRequest;
import com.examply.xpuremusic.data.bean.LibraryInfo;
import com.examply.xpuremusic.data.repository.DataRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * 信息列表 Request
 * <p>
 * TODO tip 1：Request 通常按业务划分
 * 一个项目中通常存在多个 Request 类，
 * 每个页面配备的 state-ViewModel 实例可根据业务需要持有多个不同的 Request 实例。
 * <p>
 * request 的职责仅限于 数据请求，不建议在此处理 UI 逻辑，
 * UI 逻辑只适合在 Activity/Fragment 等视图控制器中完成，是 “数据驱动” 的一部分，
 * 将来升级到 Jetpack Compose 更是如此。
 * <p>
 * 如果这样说还不理解的话，详见《如何让同事爱上架构模式、少写 bug 多注释》的解析
 * https://xiaozhuanlan.com/topic/8204519736
 * <p>
 * <p>
 * Create by KunMinX at 19/11/2
 */
public class InfoRequest extends BaseRequest {

    private MutableLiveData<List<LibraryInfo>> mLibraryLiveData;

    //TODO tip 2：向 ui 层提供的 request LiveData，使用父类 LiveData 而不是 MutableLiveData，
    //如此达成了 "唯一可信源" 的设计，也即通过访问控制权限实现 "读写分离"，
    //使得对数据请求结果的分发，只能统一来源于 request 这一处，
    //从而避免了团队新手滥用造成的不可预期的后果

    //如果这样说还不理解的话，详见《LiveData 鲜为人知的 身世背景 和 独特使命》中结合实际场合 对"唯一可信源"本质的解析。
    //https://xiaozhuanlan.com/topic/0168753249


    public LiveData<List<LibraryInfo>> getLibraryLiveData() {
        if (mLibraryLiveData == null ) {
            mLibraryLiveData = new MutableLiveData<>();
        }
        return mLibraryLiveData;
    }

    public void requestLibraryInfo() {
        DataRepository.getInstance().getLibraryInfo(new DataResult<>((libraryInfos, netState) -> {
            mLibraryLiveData.setValue(libraryInfos);
        }));
    }
}
