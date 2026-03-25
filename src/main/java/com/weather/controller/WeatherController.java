package com.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.service.WeatherService;

@RestController
@RequestMapping(value = "/api/v1/weather")
public class WeatherController {
	@Autowired
	private WeatherService weatherService;
	
	
	@GetMapping(value = "/current")
	public String getWeather(@RequestParam String city) {
		return weatherService.getCurrentWeather(city);
	}

}
