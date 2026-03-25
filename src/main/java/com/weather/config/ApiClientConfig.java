package com.weather.config;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiClientConfig {
	
	@Bean
	HttpClient httpClient() {
		return HttpClient.newHttpClient();
	}

}
