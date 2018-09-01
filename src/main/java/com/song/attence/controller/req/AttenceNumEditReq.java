package com.song.attence.controller.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * @Author jiansong0720@gmail.com
 * @Describe 考勤
 * @Date 2018-09-01
 */
@Data
@ApiModel("考勤-编辑")
public class AttenceNumEditReq {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty(value = "考勤数字",required = true)
    private Integer num;

}