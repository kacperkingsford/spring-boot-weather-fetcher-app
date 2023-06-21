package com.recruitment.lingventy.weather.app.weather.app.service;

import com.recruitment.lingventy.weather.app.weather.app.service.model.DailyData;
import com.recruitment.lingventy.weather.app.weather.app.service.model.WeatherApiResponse;
import com.recruitment.lingventy.weather.app.weather.app.service.model.WeatherReportResponse;
import com.recruitment.lingventy.weather.app.weather.app.service.utils.LocalDateToTimeMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class WeatherReportService {
	private final WeatherHistoryService weatherHistoryService;
	private final RestTemplate restTemplate = new RestTemplate();
	private static final String API_URL = "https://api.open-meteo.com/v1/forecast";
	private static final Logger LOG = LogManager.getLogger(WeatherReportService.class);
	@Autowired
	public WeatherReportService(WeatherHistoryService weatherDataRepository) {
		this.weatherHistoryService = weatherDataRepository;
	}

	public List<WeatherReportResponse> getOneWeekWeatherData(double latitude, double longitude) {
		LOG.info("Saving metadata for lattice: {}, longitude: {}", latitude, longitude);
		weatherHistoryService.saveHistoryMetadata(latitude, longitude);
		//here probably it would make sens to somehow handle situation
		//when db save exception is thrown with some business logic
		URI urlTemplate = UriComponentsBuilder
				.fromHttpUrl(API_URL)
				.queryParam("latitude", latitude)
				.queryParam("longitude", longitude)
				.queryParam("daily", "sunrise,sunset,rain_sum")
				.queryParam("timezone", "GMT")
				.build()
				.toUri();

		LOG.info("Fetching weather for 7 days for lattice: {}, longitude: {}", latitude, longitude);

		WeatherApiResponse apiResponse = restTemplate.getForObject(urlTemplate, WeatherApiResponse.class);
		//here should be some logic to handle when no results are returned from API, or less than 7 etc.

		LOG.info("Successfully fetched weather for 7 days for lattice: {}, longitude: {}", latitude, longitude);
		DailyData dailyData = apiResponse.getDailyData();

		List<LocalDate> days = dailyData.getDays();
		List<String> sunrisesTimes = LocalDateToTimeMapper.map(dailyData.getSunrises());
		List<String> sunsetsTimes = LocalDateToTimeMapper.map(dailyData.getSunsets());
		List<Double> rainSums = dailyData.getRainSums();

		return IntStream.range(1, 7)
				.mapToObj(i -> WeatherReportResponse.builder()
						.sunsetTime(sunsetsTimes.get(i))
						.sunriseTime(sunrisesTimes.get(i))
						.rainSum(rainSums.get(i))
						.day(days.get(i))
						.build())
				.collect(Collectors.toList());
	}
}
