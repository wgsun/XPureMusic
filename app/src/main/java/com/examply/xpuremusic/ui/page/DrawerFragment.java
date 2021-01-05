package com.examply.xpuremusic.ui.page;

import com.examply.architecture.ui.base.DataBindingConfig;
import com.examply.xpuremusic.BR;
import com.examply.xpuremusic.R;
import com.examply.xpuremusic.ui.base.BaseFragment;
import com.examply.xpuremusic.ui.state.DrawerViewModel;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/1/5 11:40
 */
public class DrawerFragment extends BaseFragment {

    private DrawerViewModel drawerViewModel;

    @Override
    protected void initViewModel() {
        drawerViewModel = getFragmentScopeViewModel(DrawerViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_drawer, BR.vm, drawerViewModel);
    }
}
