package com.examply.architecture.data.response;

/**
 * TODO: 专用于数据层返回结果给 domain 层或 ViewModel 用，原因如下：
 * <p>
 * liveData 是专用于页面开发的、用于解决生命周期安全问题的组件，
 * 有时候数据并非一定是通过 liveData 来分发给页面，也可能是通过别的组件去通知给非页面的东西，
 * 这时候 repo 方法中内定通过 liveData 分发就不太合适，不如一开始就规定不在数据层通过 liveData 返回结果。
 * <p>
 * 如果这样说还不理解的话，详见《如何让同事爱上架构模式、少写 bug 多注释》的解析
 * https://xiaozhuanlan.com/topic/8204519736
 * <p>
 * Create by KunMinX at 2020/7/20
 */
public class DataResult<T> {

    private T mT;
    private Result<T> mResult;
    private NetState mNetState;

    public DataResult() {
    }

    public DataResult(Result<T> mResult) {
        this.mResult = mResult;
    }

    public T getResult() {
        return mT;
    }

    public NetState getNetState() {
        return mNetState;
    }

    public void setResult(T t, NetState netState) {
        if (mResult == null) {
            throw new NullPointerException("Need to instantiate the Result<T> first ...");
        }
        if (t == null) {
            throw new NullPointerException("Need to instantiate the T first ...");
        }
        if (netState == null) {
            throw new NullPointerException("Need to instantiate the NetState first ...");
        }
        mT = t;
        mNetState = netState;
        mResult.onResult(t, netState);
    }

    public void onObserve(Result<T> result) {
        if (result == null) {
            throw new NullPointerException("Need to instantiate the Result<T> first ...");
        }
        if (mResult == null) {
            mResult = result;
        }
    }

    public interface Result<T> {
        void onResult(T t, NetState netState);
    }



}
