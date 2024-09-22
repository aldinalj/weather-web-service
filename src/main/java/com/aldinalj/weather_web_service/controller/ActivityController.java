package com.aldinalj.weather_web_service.controller;

import com.aldinalj.weather_web_service.model.Activity;
import com.aldinalj.weather_web_service.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class ActivityController {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> getAllActivities() {

        List<Activity> allActivities = activityRepository.findAll();

        return ResponseEntity.ok().body(allActivities);
    }

    @GetMapping("/activity/{id}")
    public ResponseEntity<Optional<Activity>> findActivityById(@PathVariable("id") Long id) {

        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(activity);
    }

    @GetMapping("/activities/{code}")
    public ResponseEntity<List<Activity>> findActivitiesByCode(@PathVariable("code") Integer WeatherCode) {

        List<Activity> activities = activityRepository.findActivitiesByWeatherCode(WeatherCode);

        return ResponseEntity.ok(activities);
    }
}
