package com.aldinalj.weather_web_service.controller;

import com.aldinalj.weather_web_service.exception.InvalidCodeException;
import com.aldinalj.weather_web_service.model.Activity;
import com.aldinalj.weather_web_service.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ActivityRepository activityRepository;

    List<Integer> allowedWeatherCodes = Arrays.asList(200, 500, 600, 700, 800);
    @Autowired
    public AdminController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @PostMapping("/post-activity")
    public ResponseEntity<Activity> postActivity(@RequestBody Activity activity) {

        if (!allowedWeatherCodes.contains(activity.getWeatherCode())) {
            return ResponseEntity
                    .status(400)
                    .body(activity);
        }

        return ResponseEntity
                .status(201)
                .body(activityRepository.save(activity));
    }

    @DeleteMapping("/delete-activity/{id}")
    public ResponseEntity<Void> deleteActivityById(@PathVariable("id") Long id) {

        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        activityRepository.delete(activity.get());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update-activity/{id}")
    public ResponseEntity<Void> updateActivityById(
            @PathVariable("id") Long id,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) int weatherCode

    ) {

        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

            activity.get().setName(name);

            if (allowedWeatherCodes.contains(weatherCode)) {
                activity.get().setWeatherCode(weatherCode);
            }

            activityRepository.save(activity.get());

            return ResponseEntity.ok().build();

    }

    @PutMapping("/put-activity/{id}")
    public ResponseEntity<Activity> putActivity (
            @PathVariable("id") Long id,
            @RequestBody Activity updatedActivity
    ) {

        if (activityRepository.findById(id).isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        if(!allowedWeatherCodes.contains(updatedActivity.getWeatherCode())) {
            throw new InvalidCodeException();
        }

        Activity existingActivity = activityRepository.findById(id).get();

        existingActivity.setName(updatedActivity.getName());
        existingActivity.setWeatherCode(updatedActivity.getWeatherCode());
        existingActivity.setDescription(updatedActivity.getDescription());
        existingActivity.setTemperatureMin(updatedActivity.getTemperatureMin());
        existingActivity.setTemperatureMax(updatedActivity.getTemperatureMax());
        existingActivity.setPriceMin(updatedActivity.getPriceMin());
        existingActivity.setPriceMax(updatedActivity.getPriceMax());

        return ResponseEntity.ok(activityRepository.save(existingActivity));
    }
}
