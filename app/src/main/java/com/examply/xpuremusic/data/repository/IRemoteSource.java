package com.examply.xpuremusic.data.repository;

import com.examply.architecture.data.response.DataResult;
import com.examply.xpuremusic.data.bean.LibraryInfo;

import java.util.List;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/2/4 14:56
 */
public interface IRemoteSource {

    void getLibraryInfo(DataResult<List<LibraryInfo>> result);
}
