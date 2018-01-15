//package com.springboot.study;
//
//import com.springboot.study.domain.AccessToken;
//import com.springboot.study.domain.City;
//import com.springboot.study.service.CityService;
//import com.springboot.study.service.impl.CityServiceImpl;
//import com.springboot.study.xxxcommons.util.SendMail;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class StudyApplicationTests {
//	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
//
//	private String to = "710368779@qq.com";
//
//	@Autowired
//	private SendMail sendMail;
//
//
//
////	@Async
////	@Scheduled(cron = "0 0 14 * * ?")
////	public void refreshToken() {
////		LOGGER.info("刷新token开始");
////		AccessToken accessToken = WxJsUtils.getAccessToken();
////	}
//
//
//	@Autowired
//	private CityService cityService;
//
////	@Autowired
////	Configuration configuration; //freeMarker configuration
//
//	@Test
//	public void contextLoads() {
//		Integer id = 2;
//		for (int i = 0; i < 101; i++) {
//			City cityById = cityService.getCityById(id);
//			if (cityById.getProvinceId()!=0){
//				Thread test1 = new Thread(() -> { test1(cityById); }, "test1"  );
//
//				test1.start();
//			}else {
//				LOGGER.info("sorry ! 抢完了");
//			}
//		}
//	}
//	public void test1(City city) {
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(Thread.currentThread().getName() + " : " + "我来了");
//		Long aLong = cityService.updateCity(city);
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		if (aLong==0){
//			LOGGER.info("抢购失柏"+(aLong));
//		}else {
//			LOGGER.info("抢购成功");
//		}
//	}
//	@Test
//	public void test02(){
//		City city = new City();
//		city.setCurrentPage(2);
//		city.setPageSize(5);
//		cityService.listCity(city);
//	}
//
//	@Test
//	public void sendSimpleMail() {
//		sendMail.sendSimpleMail(to, "主题：简单邮件", "测试邮件内容");
//	}
//
//
//
//	@Test
//	public void sendHtmlMailUsingFreeMarker() throws Exception {
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("time", new Date());
//		model.put("message", "这是测试的内容。。。");
//		model.put("toUserName", "张三");
//		model.put("fromUserName", "老许");
//
//		Template t = configuration.getTemplate("welcome.ftl"); // freeMarker template
//		String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
//
//		LOGGER.debug(content);
//		sendMail.sendHtmlMail(to, "主题：html邮件", content);
//	}
//
//	@Test
//	public void sendAttachmentsMail() {
//		sendMail.sendAttachmentsMail(to, "主题：带附件的邮件", "有附件，请查收！", "C:\\Users\\Administratior\\Pictures\\QQ截图20170918135527.png");
//	}
//
//	@Test
//	public void sendInlineResourceMail() {
//		String rscId = "rscId001";
//		sendMail.sendInlineResourceMail(to,
//				"主题：嵌入静态资源的邮件",
//				"<html><body>这是有嵌入静态资源：<img src=\'cid:" + rscId + "\' ></body></html>",
//				"C:\\Users\\Administratior\\Pictures\\QQ截图20170918135527.png",
//				rscId);
//	}
//}
