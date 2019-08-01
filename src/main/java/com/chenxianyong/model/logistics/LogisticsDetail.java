package com.chenxianyong.model.logistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ChenXianyong
 * @description: LogisticsDetail
 * @date: 2019/8/1 14:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsDetail {
  String time;
  String status;
}
