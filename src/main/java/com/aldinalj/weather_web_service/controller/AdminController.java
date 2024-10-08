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
@RequestMapping("/admin/activity")
public class AdminController {

    private final ActivityRepository activityRepository;

    List<Integer> allowedWeatherCodes = Arrays.asList(200, 500, 600, 700, 800);
    @Autowired
    public AdminController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @PostMapping()
    public ResponseEntity<?> postActivity(@Valid @RequestBody Activity activity, BindingResult result) {

        if (!allowedWeatherCodes.contains(activity.getWeatherCode())) {
            throw new InvalidCodeException();
        }

        if (result.hasFieldErrors()) {

            List<String> errorMessages = result.getFieldErrors().stream()
                    .map(error -> "Invalid input on field '" + error.getField() + "': " + error.getDefaultMessage())
                    .toList();

            return ResponseEntity.badRequest().body(errorMessages);
        }

        return ResponseEntity
                .status(201)
                .body(activityRepository.save(activity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivityById(@PathVariable("id") Long id) {

        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        activityRepository.delete(activity.get());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchActivityById(
            @PathVariable("id") Long id,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer weatherCode
    ) {

        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (name != null && name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Name cannot be empty");
        }

        if (weatherCode != null && !allowedWeatherCodes.contains(weatherCode)) {

            throw new InvalidCodeException();
        }

        activity.get().setWeatherCode(weatherCode);
        activity.get().setName(name);

        return ResponseEntity.ok().body(activityRepository.save(activity.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putActivityById (
            @PathVariable("id") Long id,
            @RequestBody Activity updatedActivity,
            BindingResult result
    ) {

        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if(!allowedWeatherCodes.contains(updatedActivity.getWeatherCode())) {
            throw new InvalidCodeException();
        }

        if (result.hasFieldErrors()) {

            List<String> errorMessages = result.getFieldErrors().stream()
                    .map(error -> "Invalid input on field '" + error.getField() + "': " + error.getDefaultMessage())
                    .toList();

            return ResponseEntity.badRequest().body(errorMessages);
        }

        Activity existingActivity = activity.get();

        existingActivity.setName(updatedActivity.getName());
        existingActivity.setWeatherCode(updatedActivity.getWeatherCode());
        existingActivity.setDescription(updatedActivity.getDescription());
        existingActivity.setTemperatureMin(updatedActivity.getTemperatureMin());
        existingActivity.setTemperatureMax(updatedActivity.getTemperatureMax());
        existingActivity.setPriceMin(updatedActivity.getPriceMin());
        existingActivity.setPriceMax(updatedActivity.getPriceMax());

        return ResponseEntity.ok(activityRepository.save(existingActivity));
    }

    @DeleteMapping("/truncate")
    public ResponseEntity<Void> truncateActivities() {

        activityRepository.truncateTable();

        return ResponseEntity.noContent().build();
    }
}
