package com.aldinalj.weather_web_service.controller;

import com.aldinalj.weather_web_service.model.Activity;
import com.aldinalj.weather_web_service.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ActivityRepository activityRepository;

    @Autowired
    public AdminController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @PostMapping("/post-activity")
    public ResponseEntity<Activity> postActivity(@RequestBody Activity activity) {

        return ResponseEntity
                .status(201)
                .body(activityRepository.save(activity));

    }

    @GetMapping("/activity/{id}")
    public ResponseEntity<Optional<Activity>> findActivityById(@PathVariable("id") Long id) {

        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isPresent()) {
            return ResponseEntity.ok().body(activity);
        }

            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-activity/{id}")
    public ResponseEntity<Void> deleteActivityById(@PathVariable("id") Long id) {

        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isPresent()) {

            activityRepository.delete(activity.get());
            return ResponseEntity.ok().build();

        }

            return ResponseEntity.noContent().build();
    }
}
