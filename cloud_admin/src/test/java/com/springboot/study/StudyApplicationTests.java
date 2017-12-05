package com.springboot.study;

import com.springboot.study.domain.City;
import com.springboot.study.service.CityService;
import com.springboot.study.service.impl.CityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);


	@Autowired
	private CityService cityService;

	@Test
	public void contextLoads() {
		Integer id = 2;
		for (int i = 0; i < 101; i++) {
			City cityById = cityService.getCityById(id);
			if (cityById.getProvinceId()!=0){
				Thread test1 = new Thread(() -> test1(cityById), "test1"  );
				test1.start();
			}else {
				LOGGER.info("sorry ! 抢完了");
			}
		}
	}
	public void test1(City city) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " : " + "我来了");
		Long aLong = cityService.updateCity(city);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (aLong==0){
			LOGGER.info("抢购失柏"+(aLong));
		}else {
			LOGGER.info("抢购成功");
		}
	}
	@Test
	public void test02(){
		City city = new City();
		city.setCurrentPage(2);
		city.setPageSize(5);
		cityService.listCity(city);
	}
}
