package com.cangu.app.persistence.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhengFeiFei on 2017/12/1.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "BasePageDto")
public class BasePageDto {
    @ApiModelProperty(value = "页码 分页查询时有效 默认 = 1")
    protected Integer pageNo = 1;
    @ApiModelProperty(value = "每页条数 分页查询时有效 默认 = 10")
    protected Integer pageSize = 10;
    @ApiModelProperty(value = "排序条件 例：name DESC 或 name ASC 默认 ASC")
    protected String orderBy;
}
