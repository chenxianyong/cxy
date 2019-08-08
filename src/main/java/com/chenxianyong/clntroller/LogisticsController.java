package com.chenxianyong.clntroller;

import com.chenxianyong.enums.LogisticsCompany;
import com.chenxianyong.model.logistics.Logistics;
import com.chenxianyong.service.LogisticsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ChenXianyong
 * @description: 物流快递信息查询Controller
 * @date: 2019/8/1 11:31
 */
@RestController
public class LogisticsController {

  @Autowired
  LogisticsService logisticsService;

  @RequestMapping("/query")
  public Logistics queryLogisticsInfo(@RequestParam String logisticsNo,
      @RequestParam(required = false) LogisticsCompany logisticsCompany) {

    Logistics logistics = new Logistics();

    if (StringUtils.isBlank(logisticsNo)) {
      logistics.setMsg("快递单号不能为空");
      return logistics;
    }

    return logisticsService.query(logisticsNo,logisticsCompany);
  }

}
