package org.demo.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

	//17H 45M 00S
	@Scheduled(cron = "00 45 17 * * ?")
	public void priReportjob() {
		System.out.println("17:45:00");
	}
		
	//@Scheduled(fixedRate = 1000)
	public void checkMailServer() throws Exception {
		System.out.println(1);
	}
}
