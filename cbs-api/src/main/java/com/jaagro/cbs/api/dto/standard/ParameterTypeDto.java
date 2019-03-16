package com.jaagro.cbs.api.dto.standard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * 养殖参数类型
 * @author yj
 * @date 2019/3/15 16:04
 */
@Data
@Accessors
@NoArgsConstructor
@AllArgsConstructor
public class ParameterTypeDto implements Serializable {
    private static final long serialVersionUID = -1899816830275828232L;
    /**
     * 养殖参数模板id
     */
    private Integer standardId;
    /**
     * 参数名称
     */
    private String paramName;
    /**
     * 参数类型（10-温度,11-湿度,12-光照强度,13-光照时间,14-氮气,15-二氧化碳,16-通风,17-负压值,20-喂料(次/日),21-喂水(次/日),22-饲喂(克/日),23-体重(克/只/日),24-死淘(只),25-药品/疫苗)
     */
    private Integer paramType;
    /**
     * 展示顺序
     */
    private Integer displayOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        if (!super.equals(o)) {return false;}
        ParameterTypeDto that = (ParameterTypeDto) o;
        return Objects.equals(standardId, that.standardId) &&
                Objects.equals(paramName, that.paramName) &&
                Objects.equals(paramType, that.paramType)&&
                Objects.equals(displayOrder, that.displayOrder);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), standardId, paramName, paramType,displayOrder);
    }
}
