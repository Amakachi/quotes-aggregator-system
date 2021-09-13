package com.trade.republic.quotesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class QuoteSystemApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(QuoteSystemApplication.class)
//				.web(WebApplicationType.NONE)
				.run(args);
	}

}
