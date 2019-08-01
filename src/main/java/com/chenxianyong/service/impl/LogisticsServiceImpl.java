package com.chenxianyong.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chenxianyong.clntroller.LogisticsController;
import com.chenxianyong.enums.LogisticsCompany;
import com.chenxianyong.model.logistics.Logistics;
import com.chenxianyong.service.LogisticsService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author: ChenXianyong
 * @description: LogisticsService实现
 * @date: 2019/8/1 14:24
 */
@Service
public class LogisticsServiceImpl implements LogisticsService {

  private static Logger logger = LoggerFactory.getLogger(LogisticsServiceImpl.class);

  @Override
  public Logistics query(String logisticsNo, LogisticsCompany logisticsCompany) {
    Logistics logistics = new Logistics();

    try {
      /**
       * 【1】请求地址，支持http 和 https 及 WEBSOCKET
       * 【2】后缀
       * 【3】AppCode，你自己的AppCode 在买家中心查看
       * 【4】参数，具体参照api接口参数
       * 【5】参数，具体参照api接口参数
       * 【6】拼接请求链接
       */
      String path = "https://goexpress.market.alicloudapi.com/goexpress";
      String appcode = "你自己的AppCode";
      String urlSend;
      if (Objects.isNull(logisticsCompany)) {
        urlSend = path + "?no=" + logisticsNo;
      } else {
        urlSend = path + "?no=" + logisticsNo + "&type=" + logisticsCompany;
      }

      URL url = new URL(urlSend);
      HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
      /**
       * 注意：格式Authorization:APPCODE (中间是英文空格)
       */
      httpURLConnection.setRequestProperty("Authorization", "APPCODE " + appcode);
      int httpCode = httpURLConnection.getResponseCode();
      if (httpCode == 0) {
        String json = read(httpURLConnection.getInputStream());
        logistics = JSONObject.parseObject(json, Logistics.class);
        return logistics;
      }

      logistics.setStatus(httpCode);
      switch (httpCode) {
        case 201:
          logistics.setMsg("Express a single number is empty, 快递单号为空");
          break;
        case 202:
          logistics.setMsg("Express company is empty, 快递公司为空");
          break;
        case 203:
          logistics.setMsg("Courier company does not exist, 快递公司不存在");
          break;
        case 204:
          logistics.setMsg("Courier companies identify failure, 快递公司识别失败");
          break;
        case 205:
          logistics.setMsg("No information, 没有信息");
          break;
        case 208:
          logistics.setMsg("Odd numbers do not have information, 单号没有信息");
          break;
        default:
          break;
      }
    } catch (IOException e) {
      logger.error(e.toString());
    }
    logger.info(logistics.getMsg());
    return logistics;
  }

  /**
   * 读取返回结果
   */
  private static String read(InputStream is) throws IOException {
    StringBuffer sb = new StringBuffer();
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String line;
    while ((line = br.readLine()) != null) {
      line = new String(line.getBytes(), "utf-8");
      sb.append(line);
    }
    br.close();
    return sb.toString();
  }
}
