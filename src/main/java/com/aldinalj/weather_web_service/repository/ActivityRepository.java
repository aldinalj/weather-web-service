package com.aldinalj.weather_web_service.repository;

import com.aldinalj.weather_web_service.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findActivitiesById();
}
