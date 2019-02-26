package com.jaagro.cbs.api.dto.progress;

import com.jaagro.cbs.api.model.BreedingRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author :Gavin
 * @date :2019/02/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BreedingRecordDto implements Serializable {

 /**
  *养殖计划的鸡舍在某日龄上的喂料记录
  */
 private List<BreedingRecord> feedFoodList;
 /**
  * 养殖计划的鸡舍在某日龄上的喂水记录
  */
 private List<BreedingRecord> feedWaterList;
 /**
  * 养殖计划的鸡舍在某日龄上的喂药记录
  */
 private List<BreedingRecord> feedMedicineList;
 /**
  * 养殖计划的鸡舍在某日龄上的喂料总次数
  */
 private Integer feedFoodTimes;
 /**
  * 养殖计划的鸡舍在某日龄上的喂料耗料总量
  */
 private BigDecimal feedFoodWeight;
 /**
  * 养殖计划的鸡舍在某日龄上的喂水总次数
  */
 private Integer feedWaterTimes;
 /**
  * 养殖计划的鸡舍在某日龄上的喂药总次数
  */
 private Integer feedMedicineTimes;
 /**
  * 养殖计划的鸡舍在某日龄上的喂药总量
  */
 private BigDecimal feedMedicineWeight;



}
