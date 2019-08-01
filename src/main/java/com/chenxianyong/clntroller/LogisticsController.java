package com.chenxianyong.clntroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenxianyong.enums.LogisticsCompany;
import com.chenxianyong.model.logistics.Logistics;
import com.chenxianyong.model.logistics.LogisticsDetail;
import com.chenxianyong.service.LogisticsService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ChenXianyong
 * @description: 物流Controller
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
