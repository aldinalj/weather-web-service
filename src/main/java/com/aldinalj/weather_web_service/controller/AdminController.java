package com.aldinalj.weather_web_service.controller;

import com.aldinalj.weather_web_service.exception.InvalidCodeException;
import com.aldinalj.weather_web_service.model.Activity;
import com.aldinalj.weather_web_service.repository.ActivityRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> postActivity(@Valid @RequestBody Activity activity, BindingResult result) {

        if (!allowedWeatherCodes.contains(activity.getWeatherCode())) {
            return ResponseEntity
                    .status(400)
                    .body("Weather code is invalid. Please use one of these instead: 200, 500, 600, 700, 800.");
        }

        if (result.hasFieldErrors("name")) {

            String errorMessage = result.getFieldError("name").getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
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

    @PatchMapping("/patch-activity/{id}")
    public ResponseEntity<?> patchActivityById(
            @PathVariable("id") Long id,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer weatherCode

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
    public ResponseEntity<?> putActivityById (
            @PathVariable("id") Long id,
            @RequestBody Activity updatedActivity,
            BindingResult result
    ) {

        if (activityRepository.findById(id).isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        if(!allowedWeatherCodes.contains(updatedActivity.getWeatherCode())) {
            throw new InvalidCodeException();
        }

        if (result.hasFieldErrors("name")) {

            String errorMessage = result.getFieldError("name").getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
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

    @DeleteMapping("/truncate-table")
    public ResponseEntity<Void> truncateActivities() {

        activityRepository.truncateTable();

        return ResponseEntity.noContent().build();
    }
}
