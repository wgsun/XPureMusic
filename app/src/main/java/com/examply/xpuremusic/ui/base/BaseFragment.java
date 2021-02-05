package com.examply.xpuremusic.ui.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import com.examply.architecture.ui.base.DataBindingFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

/**
 * @author wgsun
 * @descrbe
 * @since 2020/12/30 10:30
 */
public abstract class BaseFragment extends DataBindingFragment {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    protected boolean mAnimationLoaded;

    protected <T extends ViewModel> T getFragmentScopeViewModel(@NonNull Class<T> modelClass) {
        return super.getFragmentScopeViewModel(modelClass);
    }

    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        return super.getActivityScopeViewModel(modelClass);
    }

    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        return super.getApplicationScopeViewModel(modelClass);
    }

    /*protected NavController Nav() {
        return NavHostFragment.findNavController(this);
    }*/

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //TODO 错开动画转场与 UI 刷新的时机，避免掉帧卡顿的现象
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mAnimationLoaded) {
                    mAnimationLoaded = true;
                }
            }
        }, 300);
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    protected void loadInitData() {

    }

    protected void openUrlInBrowser(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
