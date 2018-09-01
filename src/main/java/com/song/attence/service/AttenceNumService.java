package com.song.attence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 考勤数字服务
 */
@Slf4j
@Service
public class AttenceNumService {

    // TODO: 2018/8/31/031 每天早上执行

    /**
     * 生成随机考勤数据
     */
    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void randomAttenceNum() {
        Random random = new Random();
        Integer num = random.nextInt(8999) + 1000;
        log.info("{}考勤数字:{}.", DateFormat.getDateInstance().format(new Date()), num);
    }

}
