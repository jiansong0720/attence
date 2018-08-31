package com.song.attence.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 考勤数字实体
 */
@Getter
@Setter
@Entity
public class AttenceNum {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime = new Date();

    /**
     * 考勤数字
     */
    private Integer num;

}
