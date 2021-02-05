package com.examply.architecture.ui.callback;

import android.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

/**
 * TODO：UnPeekLiveData 的存在是为了在 "重回二级页面" 的场景下，解决 "数据倒灌" 的问题。
 * 对 "数据倒灌" 的状况不理解的小伙伴，可参考《LiveData 数据倒灌 背景缘由全貌 独家解析》文章开头的解析
 * <p>
 * https://xiaozhuanlan.com/topic/6719328450
 * <p>
 * 本类参考了官方 SingleEventLive 的非入侵设计，
 * 以及小伙伴 Flywith24 在 wrapperLiveData 中通过 ViewModelStore 来唯一确定订阅者的思路，
 * <p>
 * TODO：在当前最新版中，我们透过对 ViewModelStore 的内存地址的遍历，
 * 来确保：
 * 1.一条消息能被多个观察者消费
 * 2.消息被所有观察者消费完毕后才开始阻止倒灌
 * 3.可以配置自动/手动将消息从内存中移除
 * 4.让非入侵设计成为可能，遵循开闭原则
 * <p>
 * TODO：增加一层 ProtectedUnPeekLiveData，
 * 用于限制从 Activity/Fragment 篡改来自 "数据层" 的数据，数据层的数据务必通过 "唯一可信源" 来分发，
 * 如果这样说还不理解，详见：
 * https://xiaozhuanlan.com/topic/0168753249 和 https://xiaozhuanlan.com/topic/6719328450
 * <p>
 * Create by KunMinX at 19/9/23
 *
 * 如果这样说还不理解，详见wiki：http://wiki.iflytek.com/pages/viewpage.action?pageId=336936064
 */
public class ProtectedUnPeekLiveData2<T> extends LiveData<T> {

    protected boolean isAllowNullValue;

    protected boolean isAutoClearValue;

    private final HashMap<Integer, Pair<Boolean, LifecycleOwner>> observers = new HashMap<>();

    public void observeInActivity(@NonNull AppCompatActivity activity, @NonNull Observer<? super T> observer) {
        LifecycleOwner owner = activity;
        owner.getLifecycle().getCurrentState();
        Integer storeId = System.identityHashCode(activity.getViewModelStore());
        observe(storeId, owner, observer);
    }

    public void observeInFragment(@NonNull Fragment fragment, @NonNull Observer<? super T> observer) {
        LifecycleOwner owner = fragment.getViewLifecycleOwner();
        owner.getLifecycle().getCurrentState();
        Integer storeId = System.identityHashCode(fragment.getViewModelStore());
        observe(storeId, owner, observer);
    }

    private void observe(@NonNull Integer storeId,
                         @NonNull LifecycleOwner owner,
                         @NonNull Observer<? super T> observer) {

        if (observers.get(storeId) == null) {
            observers.put(storeId, new Pair<>(true,owner));
        }

        super.observe(owner, t -> {
            if (observers.get(storeId) != null && !observers.get(storeId).first) {
                observers.put(storeId, new Pair<>(true,owner));
                if (t != null || isAllowNullValue) {
                    observer.onChanged(t);
                    /* 开启自动清除数据功能，当所有的观察者都接收到通知后，清除liveData携带数据 */
                    if(isAutoClearValue && observersNotified(observers)){
                        clear();
                    }
                }
            }
        });
    }

    /**
     * 重写的 setValue 方法，默认不接收 null
     * 可通过 Builder 配置允许接收
     * 可通过 Builder 配置消息延时清理的时间
     * <p>
     * override setValue, do not receive null by default
     * You can configure to allow receiving through Builder
     * And also, You can configure the delay time of message clearing through Builder
     *
     * @param value
     */
    @Override
    protected void setValue(T value) {
        if (value != null || isAllowNullValue) {
            Iterator<Map.Entry<Integer, Pair<Boolean, LifecycleOwner>>> iterator = observers.entrySet().iterator();
            Map.Entry<Integer, Pair<Boolean, LifecycleOwner>> entry;
            while (iterator.hasNext()){
                entry = iterator.next();
                LifecycleOwner owner = entry.getValue().second;
                /* 当owner已经被销毁，从当前map中移除该观察者 */
                if(owner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED){
                    iterator.remove();
                }
                /* 当owner未被销毁，再次通知该观察者 */
                else {
                    entry.setValue(new Pair<>(false, entry.getValue().second));
                }
            }
            super.setValue(value);
        }
    }


    protected void clear() {
        super.setValue(null);
    }

    /**
     * 所有的观察者是否都已经收到通知
     */
    protected boolean observersNotified(HashMap<Integer, Pair<Boolean, LifecycleOwner>> observers){
        for (Map.Entry<Integer, Pair<Boolean, LifecycleOwner>> entry : observers.entrySet()) {
            if(!entry.getValue().first){
                return false;
            }
        }
        return true;
    }
}
