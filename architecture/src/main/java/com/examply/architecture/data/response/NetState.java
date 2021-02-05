package com.examply.architecture.data.response;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/2/4 14:20
 */
public class NetState {

    private String responseCode;
    private boolean success = true;

    public NetState(String responseCode, boolean success) {
        this.responseCode = responseCode;
        this.success = success;
    }

    public NetState() {
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
