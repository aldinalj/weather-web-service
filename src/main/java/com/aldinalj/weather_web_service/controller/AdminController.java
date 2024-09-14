package com.aldinalj.weather_web_service.controller;

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

    List<Integer> allowedCodes = Arrays.asList(2, 5, 6, 7, 8);
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

        if (activity.isEmpty()) {
            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.ok().body(activity);

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

    @PutMapping("/update-activity/{id}")
    public ResponseEntity<Void> updateActivityById(
            @PathVariable("id") Long id,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) int code

    ) {

        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isEmpty()) {

            return ResponseEntity.noContent().build();
        }

            activity.get().setName(name);

            if (allowedCodes.contains(code)) {
                activity.get().setCode(code);
            }

            activityRepository.save(activity.get());

            return ResponseEntity.ok().build();

    }
}
