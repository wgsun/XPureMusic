package com.examply.architecture.domain;

import com.examply.architecture.data.response.NetState;
import com.examply.architecture.ui.callback.ProtectedUnPeekLiveData;
import com.examply.architecture.ui.callback.UnPeekLiveData;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/2/3 15:45
 */
public class BaseRequest {

    protected final UnPeekLiveData<NetState> netStateEvent = new UnPeekLiveData<>();

    //TODO tip 向 ui 层提供的 request LiveData，使用抽象的 ProtectedUnPeekLiveData 而不是 UnPeekLiveData
    // 如此是为了来自数据层的数据，在 ui 层中只读，以避免团队新手不可预期的误用

    // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/6719328450 文末加餐的解析

    public ProtectedUnPeekLiveData<NetState> getNetStateEvent() {
        return netStateEvent;
    }
}
