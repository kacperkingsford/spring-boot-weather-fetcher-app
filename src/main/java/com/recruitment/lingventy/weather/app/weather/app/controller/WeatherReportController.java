package com.recruitment.lingventy.weather.app.weather.app.controller;

import com.recruitment.lingventy.weather.app.weather.app.service.WeatherReportService;
import com.recruitment.lingventy.weather.app.weather.app.service.model.WeatherReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherReportController {
	private final WeatherReportService weatherReportService;

	@Autowired
	public WeatherReportController(WeatherReportService weatherService) {
		this.weatherReportService = weatherService;
	}

	@GetMapping("/{latitude}/{longitude}")
	public ResponseEntity<List<WeatherReportResponse>> getWeatherData(@PathVariable double latitude, @PathVariable double longitude) {
		List<WeatherReportResponse> weatherData = weatherReportService.getOneWeekWeatherData(latitude, longitude);
		return ResponseEntity.ok(weatherData);
	}
}
