package com.song.attence.repository;

import com.song.attence.domain.AttenceNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/** 
 * @Author jiansong0720@gmail.com
 * @Describe 考勤
 * @Date 2018-09-01
 */
public interface AttenceNumRepository extends JpaRepository<AttenceNum, Long>, JpaSpecificationExecutor<AttenceNum> {

}