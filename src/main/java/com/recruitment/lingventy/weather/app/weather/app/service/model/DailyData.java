package com.recruitment.lingventy.weather.app.weather.app.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyData {
	@JsonProperty("time")
	@JsonFormat(pattern="yyyy-MM-dd")
	private List<LocalDate> days;

	@JsonProperty("sunrise")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private List<LocalDateTime> sunrises;


	@JsonProperty("sunset")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private List<LocalDateTime> sunsets;

	@JsonProperty("rain_sum")
	private List<Double> rainSums;

	public List<LocalDate> getDays() {
		return days;
	}

	public List<LocalDateTime> getSunrises() {
		return sunrises;
	}

	public List<LocalDateTime> getSunsets() {
		return sunsets;
	}

	public List<Double> getRainSums() {
		return rainSums;
	}
}
