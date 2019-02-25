package com.jaagro.cbs.api.dto.progress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author :Gavin
 * @date :2019/02/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BreedingRecordDto implements Serializable {

 private Integer coopId;
}
