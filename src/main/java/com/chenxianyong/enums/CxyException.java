package com.chenxianyong.enums;

import com.chenxianyong.model.CxyExceptionModel;
import com.chenxianyong.service.CxyExFactor;

/**
 * @author: ChenXianyong
 * @description: 自定义异常枚举
 * @date: 2019/7/26 16:17
 */
public enum CxyException implements CxyExFactor {

    USER_NOT_EXIST(1, "user not exist", "用户不存在");

    public static final int http_code = 404;
    public static final int biz_code = 56;

    int httpCode = http_code;
    int errCode;
    String errorMsg;
    String detailMsg;

    CxyException(int errCode, String errorMsg, String detailMsg) {
        this.errCode = http_code * 100000 + biz_code * 1000 + errCode;
        this.errorMsg = errorMsg;
        this.detailMsg = detailMsg;
    }

    @Override
    public CxyExceptionModel getCxyExModel() {
        CxyExceptionModel exceptionModel = new CxyExceptionModel();
        exceptionModel.setHttpCode(this.httpCode);
        exceptionModel.setErrCode(this.errCode);
        exceptionModel.setErrorMsg(this.errorMsg);
        exceptionModel.setDetailMsg(this.detailMsg);
        return exceptionModel;
    }

}
