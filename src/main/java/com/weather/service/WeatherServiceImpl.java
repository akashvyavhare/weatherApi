package com.weather.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.client.WeatherHttpClient;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	WeatherHttpClient weatherHttpClient;

	private final String BASE_URI = "https://api.weatherapi.com/v1/";
	private final String API_KEY = "a95bf0a0c96048bb8ea151146262303";

	@Override
	public String getCurrentWeather(String city) {
		try {
			String requestUri = BASE_URI+ "current.json" + "?q=" + city + "&key=" + API_KEY;
			HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(requestUri))
					.header("accept", "application/json").GET().build();
			System.out.println("request -- "+httpRequest);
			String response =weatherHttpClient.sendRequest(httpRequest, String.class);
			return response;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

}
