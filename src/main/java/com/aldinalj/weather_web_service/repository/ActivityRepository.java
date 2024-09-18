package com.aldinalj.weather_web_service.repository;

import com.aldinalj.weather_web_service.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findActivitiesByWeatherCode(Integer weatherCode);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE activity RESTART IDENTITY", nativeQuery = true)
    void truncateTable();

}
