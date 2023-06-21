package com.recruitment.lingventy.weather.app.weather.app.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_history")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime timestamp;
	private Double latitude;
	private Double longitude;
}
