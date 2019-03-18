package com.jaagro.cbs.api.dto.standard;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 删除养殖模板参数参数
 * @author yj
 * @date 2019/3/17 14:16
 */
@Data
@Accessors(chain = true)
public class DelBreedingStandardParamDto implements Serializable{
    private static final long serialVersionUID = -5755579744033733709L;
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
}
