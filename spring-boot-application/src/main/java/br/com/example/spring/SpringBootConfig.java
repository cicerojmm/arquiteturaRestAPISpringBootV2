package br.com.example.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class SpringBootConfig implements ApplicationRunner {

	private static final Logger logger = LogManager.getLogger(SpringBootConfig.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfig.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("Log4j2 initialized");
	}
}
