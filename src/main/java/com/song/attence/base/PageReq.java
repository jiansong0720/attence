package com.song.attence.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * 分页基础参数
 */
@Data
@ApiModel("基础分页-请求")
public class PageReq {

    @Min(value = 1, message = "每页显示条数必须大于等于1")
    @ApiModelProperty(value = "每页多少条记录,默认为10")
    private Integer size = 10;

    @Min(value = 0, message = "当前页必须大于等于0")
    @ApiModelProperty(value = "当前页码，从0开始，默认为0")
    private Integer page = 0;

}
