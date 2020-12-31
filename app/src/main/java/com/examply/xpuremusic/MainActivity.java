package com.examply.xpuremusic;

import android.os.Bundle;
import com.examply.architecture.ui.base.DataBindingConfig;
import com.examply.xpuremusic.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void initViewModel() {

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}