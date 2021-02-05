package com.examply.xpuremusic.data.repository;

import com.examply.architecture.data.response.DataResult;
import com.examply.architecture.data.response.NetState;
import com.examply.architecture.utils.Utils;
import com.examply.xpuremusic.data.bean.LibraryInfo;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.List;
import com.examply.xpuremusic.R;
import com.google.gson.reflect.TypeToken;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/2/4 15:18
 */
public enum DataRepository implements ILocalSource, IRemoteSource {

    INSTANCE;

    public static DataRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void getLibraryInfo(DataResult<List<LibraryInfo>> result) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<LibraryInfo>>() {
        }.getType();
        List<LibraryInfo> list = gson.fromJson(Utils.getApp().getString(R.string.library_json), type);
        result.setResult(list, new NetState());
    }
}
