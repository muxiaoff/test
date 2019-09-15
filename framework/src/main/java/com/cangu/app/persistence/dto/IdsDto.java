package com.cangu.app.persistence.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author ZhengFeiFei on 2017/12/1.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "IdsDto")
public class IdsDto implements Serializable {
    @NotEmpty(message = "ids不能为空")
    @ApiModelProperty(value = "ids")
    protected List<Long> ids = Lists.newArrayList();
}
