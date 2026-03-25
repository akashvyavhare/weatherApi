package com.weather.client;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

import tools.jackson.databind.ObjectMapper;

@Component
public class WeatherHttpClient {

	private final HttpClient client;
	private final ObjectMapper objectMapper;

	public WeatherHttpClient(HttpClient client, ObjectMapper objectMapper) {
		this.client = client;
		this.objectMapper = objectMapper;
	}

	public <T> T sendRequest(HttpRequest httpRequest, Class<T> responseHandler)
			throws IOException, InterruptedException {
		HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		int statusCode = response.statusCode();
		if (statusCode >= 200 && statusCode < 300) {
			if (responseHandler == String.class) {
				return responseHandler.cast(response.body());
			}
			return objectMapper.readValue(response.body(), responseHandler);
		} else {
			throw new RuntimeException();
		}

	}

}
