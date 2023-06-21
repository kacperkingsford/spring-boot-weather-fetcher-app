package com.recruitment.lingventy.weather.app.weather.app.service.utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class LocalDateToTimeMapper {

	private static String map(LocalDateTime localDateTime) {
		return localDateTime.getHour() + ":" + localDateTime.getMinute();
	}

	public static List<String> map(List<LocalDateTime> localDateTimeList) {
		return localDateTimeList
				.stream()
				.map(LocalDateToTimeMapper::map)
				.collect(Collectors.toList());
	}
}
