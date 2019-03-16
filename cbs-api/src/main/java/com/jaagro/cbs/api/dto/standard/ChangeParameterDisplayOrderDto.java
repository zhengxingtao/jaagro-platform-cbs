package com.jaagro.cbs.api.dto.standard;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 改变养殖模板参数排序参数
 * @author yj
 * @date 2019/3/16 11:17
 */
@Data
@Accessors(chain = true)
public class ChangeParameterDisplayOrderDto implements Serializable {
    private static final long serialVersionUID = -3496782206369272495L;
    /**
     * 养殖模板id
     */
    @NotNull(message = "{standardId.NotNull}")
    @Min(value = 1,message = "{standardId.Min}")
    private Integer standardId;
    /**
     * 参数名称
     */
    @NotBlank(message = "{paramName.NotBlank}")
    private String paramName;
    /**
     * 参数类型
     */
    @NotNull(message = "{paramType.NotNull}")
    @Min(value = 1,message = "{paramType.Min}")
    private Integer paramType;
    /**
     * 排序类型(1-向上,2-向下)
     */
    @NotNull(message = "{sortType.NotNull}")
    @Min(value = 1,message = "{sortType.Min}")
    private Integer sortType;
    /**
     * 展示顺序
     */
    @NotNull(message = "{displayOrder.NotNull}")
    @Min(value = 1,message = "{displayOrder.Min}")
    private Integer displayOrder;
}
