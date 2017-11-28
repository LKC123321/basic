package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TaijiCongfiguration {

	@Bean
	public FooProperties fooProperties(){
		FooProperties fooProperties = new FooProperties();
		return fooProperties;
	}
	
}
