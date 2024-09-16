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


    @GetMapping("/activity/{id}")
    public ResponseEntity<Optional<Activity>> findActivityById(@PathVariable("id") Long id) {

        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(activity);
    }

    @GetMapping("/activities/{code}")
    public ResponseEntity<List<Activity>> findActivitiesByCode(@PathVariable("code") Integer code) {

        List<Activity> activities = activityRepository.findActivitiesByCode(code);

        if (activities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(activities);
    }
}
