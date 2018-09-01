package com.song.attence.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * API请求的返回结果
 */
@Data
@ApiModel("基础对象-返回")
public class ResBean<T> {

    @ApiModelProperty("信息")
    private String message;

    @ApiModelProperty("编码")
    private Integer code;

    @ApiModelProperty("数据")
    private T data;

    public ResBean() {
        this.code = Errors.SUCCESS.code;
        this.message = Errors.SUCCESS.message;
    }

    public ResBean(T data) {
        this.code = Errors.SUCCESS.code;
        this.message = Errors.SUCCESS.message;
        this.data = data;
    }

    public ResBean(Errors errors) {
        this.code = errors.code;
        this.message = errors.message;
    }

}
