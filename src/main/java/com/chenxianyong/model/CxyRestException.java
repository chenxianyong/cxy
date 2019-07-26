package com.chenxianyong.model;

import com.chenxianyong.enums.CxyException;
import com.chenxianyong.enums.CxyExceptionFactor;
import com.chenxianyong.service.CxyExFactor;

/**
 * @author: ChenXianyong
 * @description: 自定义显示异常类
 * @date: 2019/7/26 17:09
 */
public class CxyRestException {

    private CxyExFactor cxyExFactor = CxyExceptionFactor.INTERNAL_ERROR;

    public CxyRestException(CxyExFactor cxyExFactor) {
        this.cxyExFactor = cxyExFactor;
    }
}
