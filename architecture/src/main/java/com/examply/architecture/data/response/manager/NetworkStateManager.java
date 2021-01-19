package com.examply.architecture.data.response.manager;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.examply.architecture.utils.Utils;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/1/19 10:11
 */
public class NetworkStateManager implements DefaultLifecycleObserver {

    private NetworkStateReceive mNetworkStateReceive;

    public static NetworkStateManager getInstance() {
        return NetworkStateHolder.mInstance;
    }

    private static class NetworkStateHolder{
        public static final NetworkStateManager mInstance = new NetworkStateManager();
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        mNetworkStateReceive = new NetworkStateReceive();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        Utils.getApp().getApplicationContext().registerReceiver(mNetworkStateReceive, intentFilter);
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Utils.getApp().unregisterReceiver(mNetworkStateReceive);
    }
}
