package com.cangu.app.persistence.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhengFeiFei on 2017/12/1.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "BaseVo")
public class BaseVo implements Serializable {
    @ApiModelProperty(value = "id")
    protected Long id;
}
