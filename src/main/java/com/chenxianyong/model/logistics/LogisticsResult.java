package com.chenxianyong.model.logistics;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ChenXianyong
 * @description: LogisticsResult
 * @date: 2019/8/1 14:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsResult {

  String number;
  String type;
  List<LogisticsDetail> list;
  /**
   * 0,快递收件(揽件);
   * 1,在途中;
   * 2,正在派件;
   * 3,已签收;
   * 4,派送失败;
   * 5,疑难件;
   * 6,退件签收。
   */
  String deliverystatus;
  /**
   * 是否签收
   */
  String issign;
  String expName;
  String expSite;
  String expPhone;
}
