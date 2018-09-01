package com.song.attence.controller.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * @Author jiansong0720@gmail.com
 * @Describe 考勤
 * @Date 2018-09-01
 */
@Data
@ApiModel("考勤-详情")
public class AttenceNumDetailRes {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("考勤数字")
    private Integer num;

}