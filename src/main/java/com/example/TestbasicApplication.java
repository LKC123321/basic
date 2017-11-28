package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@SpringBootApplication
public class TestbasicApplication {

	private static final Logger log = LoggerFactory.getLogger(TestbasicApplication.class);
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TestbasicApplication.class, args);
		System.out.println(context.getBean(FooProperties.class));
	}
	public class MyHealthIndicator implements HealthIndicator {
		@Override
		public Health health() {
			int errorCode = check(); 
			// perform some specific health check
			if(errorCode != 0) {
				return Health.down().withDetail("Error Code", errorCode).build();
			}
			return Health.up().build();
		}

		private int check() {
			return 0;
		}
	}
	@Autowired
	private CounterService counterService;
	
	@Bean
	public ApplicationListener<ApplicationEvent> xyzListener() {
		final String HELLO_URL = "/xyz";

		return (ApplicationEvent event) -> {
			if (event instanceof ServletRequestHandledEvent) {
				ServletRequestHandledEvent e = (ServletRequestHandledEvent) event;
				if (e.getRequestUrl().equals(HELLO_URL))
					counterService.increment("xyz.hits");
			}
		};
	}
}
