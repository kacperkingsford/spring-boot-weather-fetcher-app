package com.recruitment.lingventy.weather.app.weather.app.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponse {
	@JsonProperty("daily")
	private DailyData dailyData;

	public DailyData getDailyData() {
		return dailyData;
	}
}
