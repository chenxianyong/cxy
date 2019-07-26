package com.chenxianyong.enums;

import com.chenxianyong.model.CxyExceptionModel;
import com.chenxianyong.service.CxyExFactor;

/**
 * @author: ChenXianyong
 * @description: 自定义异常类
 * @date: 2019/7/26 17:14
 */
public enum CxyExceptionFactor implements CxyExFactor{

    INTERNAL_ERROR(500, 5000001, "Service internal error!", "");

    final int httpCode;
    final int errCode;
    final String errorMsg;
    final String detailMsg;

    CxyExceptionFactor(int httpCode, int errCode, String errorMsg, String detailMsg) {
        this.httpCode = httpCode;
        this.errCode = errCode;
        this.errorMsg = errorMsg;
        this.detailMsg = detailMsg;
    }

    @Override
    public CxyExceptionModel getCxyExModel() {
        CxyExceptionModel model = new CxyExceptionModel();
        model.setHttpCode(this.httpCode);
        model.setErrCode(this.errCode);
        model.setErrorMsg(this.errorMsg);
        model.setDetailMsg(this.detailMsg);
        return model;
    }


}
