package com.chenxianyong.model.wage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ChenXianyong
 * @description: 工资
 * @date: 2019/8/13 10:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wage {

  /**
   * 基本工资
   */
  Long basicWage;
  /**
   * 个人所得税起征点
   */
  Long personalIncomeTaxThreshold;
  /**
   * 个税专项附加扣除
   */
  Long specialAdditionalTaxDeduction;
  /**
   * 公积金起征点
   */
  Long accumulationFundThreshold;
  /**
   * 公积金缴存比例
   */
  Integer accumulationFund;
  /**
   * 养老保险征点
   */
  Long endowmentInsuranceThreshold;
  /**
   * 养老保险比例
   */
  Integer endowmentInsurance;
  /**
   * 医疗保险征点
   */
  Long medicalInsuranceThreshold;
  /**
   * 医疗保险
   */
  Integer medicalInsurance;
  /**
   * 失业保险征点
   */
  Long unemployedInsuranceThreshold;
  /**
   * 失业保险
   */
  Integer unemployedInsurance;

}
