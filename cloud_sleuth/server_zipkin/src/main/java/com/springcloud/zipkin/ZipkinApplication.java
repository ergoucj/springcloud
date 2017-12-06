/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2017.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording, 
 * or otherwise, without the prior written permission of ShenZhen JiMi Network Technology Co., Ltd.
 *
 * Amendment History:
 * 
 * Date                   By              Description
 * -------------------    -----------     -------------------------------------------
 * 2017年9月4日    Li.Shangzhi         Create the class
 * http://www.jimilab.com/
*/
package com.springcloud.zipkin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.EnableZipkinServer;

/**
 * @FileName ZipkinApplication.java
 * @Description: zipkin数据收集中心
 *
 * @Date 2017年9月4日 10:15:26
 * @author Li.Shangzhi
 * @version 1.0
 */
@SpringBootApplication
@EnableZipkinServer
@EnableEurekaClient
public class ZipkinApplication{
    
	private static final Logger logger = LoggerFactory.getLogger(ZipkinApplication.class);
	
    public static void main(String[] args){
        SpringApplication.run(ZipkinApplication.class, args);
        
        logger.info("====================启动成功!====================");
    }
    
}
