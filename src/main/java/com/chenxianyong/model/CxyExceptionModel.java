package com.chenxianyong.model;

import lombok.Data;

/**
 * @author: ChenXianyong
 * @description: 自定义异常类
 * @date: 2019/7/26 16:25
 */
@Data
public class CxyExceptionModel {
    int httpCode;
    int errCode;
    String errorMsg;
    String detailMsg;
    String requestUri;

}
