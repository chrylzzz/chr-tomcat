package com.chryl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时任务支持
@EnableScheduling
public class ChrTomcatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChrTomcatApplication.class, args);
	}

}
