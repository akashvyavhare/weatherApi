package com.weather.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

	private final String BASE_URI = "https://api.weatherapi.com/v1/";
	private final String API_KEY = "a95bf0a0c96048bb8ea151146262303";

	@Override
	public void getCurrentWeather(String city) {
		try {
			String requestUri = BASE_URI+ "current.json" + "?q=" + city + "&key=" + API_KEY;
			HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(requestUri))
					.header("accept", "application/json").GET().build();
			System.out.println("request -- "+httpRequest);
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			int statusCode = response.statusCode();
			if(statusCode>=200 && statusCode <= 300) {
				String respBody = response.body();
				System.out.println("respBody \n" +respBody);
			}
			else {
				System.out.println("StatusCode -- "+statusCode);
				throw new RuntimeException();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			//throw new RuntimeException();
		}

	}

}
