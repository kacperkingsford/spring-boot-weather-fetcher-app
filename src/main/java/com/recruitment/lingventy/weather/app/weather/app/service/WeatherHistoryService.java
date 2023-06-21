package com.recruitment.lingventy.weather.app.weather.app.service;

import com.recruitment.lingventy.weather.app.weather.app.dao.WeatherHistoryRepository;
import com.recruitment.lingventy.weather.app.weather.app.dao.entity.WeatherHistory;
import com.recruitment.lingventy.weather.app.weather.app.service.model.DailyData;
import com.recruitment.lingventy.weather.app.weather.app.service.model.WeatherApiResponse;
import com.recruitment.lingventy.weather.app.weather.app.service.model.WeatherReportResponse;
import com.recruitment.lingventy.weather.app.weather.app.service.utils.LocalDateToTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class WeatherHistoryService {
	private final WeatherHistoryRepository weatherDataRepository;

	@Autowired
	public WeatherHistoryService(WeatherHistoryRepository weatherDataRepository) {
		this.weatherDataRepository = weatherDataRepository;
	}

	public void saveHistoryMetadata(double latitude, double longitude) {
		weatherDataRepository.save(WeatherHistory
				.builder()
				.latitude(latitude)
				.longitude(longitude)
				.timestamp(LocalDateTime.now())
				.build());
	}
}
