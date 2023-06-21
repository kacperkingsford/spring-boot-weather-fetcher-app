package com.recruitment.lingventy.weather.app.weather.app.service.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class WeatherReportResponse {
	private LocalDate day;
	private String sunriseTime;
	private String sunsetTime;
	private double rainSum;
}
