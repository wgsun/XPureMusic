package com.examply.xpuremusic;

import com.examply.architecture.ui.base.BaseApplication;
import com.examply.architecture.utils.Utils;
import com.examply.xpuremusic.player.PlayerManager;

/**
 * @author wgsun
 * @descrbe 程序入口
 * @since 2020/12/29 13:59
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        PlayerManager.getInstance().init(this);
    }
}
