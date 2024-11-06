package com.aldinalj.weather_web_service.user.repository;

import com.aldinalj.weather_web_service.user.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
}
