package com.chenxianyong.service;

import com.chenxianyong.enums.LogisticsCompany;
import com.chenxianyong.model.logistics.Logistics;

/**
 * @author: ChenXianyong
 * @description: 物流接口
 * @date: 2019/8/1 14:23
 */
public interface LogisticsService {

  Logistics query(String logisticsNo, LogisticsCompany logisticsCompany);
}
