package com.song.attence.domain;

import com.song.attence.base.BaseDomain;
import com.song.attence.base.Desc;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 考勤数字实体
 */
@Data
@Entity(name = "song_attence_num")
public class AttenceNum extends BaseDomain {

    @Desc("考勤数字")
    private Integer num;

}
