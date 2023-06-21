package com.recruitment.lingventy.weather.app.weather.app.dao;

import com.recruitment.lingventy.weather.app.weather.app.dao.entity.WeatherHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherHistoryRepository extends JpaRepository<WeatherHistory, Long> {
}
