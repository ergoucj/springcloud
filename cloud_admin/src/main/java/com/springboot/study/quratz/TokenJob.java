package com.springboot.study.quratz;

import com.springboot.study.domain.AccessToken;
import com.springboot.study.util.WxJsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 *
 * @author hcj
 * 2个小时刷新token
 */
@Component
public class TokenJob {
    private static final Logger logger = LoggerFactory.getLogger(TokenJob.class);

    @Async
//    @Scheduled(fixedRate = 7200000)
    @Scheduled(cron = "0 0 14 * * ?")
    public void refreshToken() {
        LocalDate.now();
        System.out.println(LocalDate.now());
        logger.info("刷新token开始");
        AccessToken accessToken = WxJsUtils.getAccessToken();
    }


}
