package com.chenxianyong.model.logistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author: ChenXianyong
 * @description: 物流model
 * @date: 2019/8/1 11:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Logistics {

  int status;
  String msg;
  LogisticsResult result;

}
