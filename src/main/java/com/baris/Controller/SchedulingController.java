package com.baris.Controller;

import com.baris.Entity.Terminal;
import com.baris.Entity.TimeSlot;
import com.baris.Services.SchedulingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ResponseStatusException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {
    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @GetMapping("/time-slots")
    public List<TimeSlot> getAllTimeSlots() {
        return schedulingService.getAllTimeSlots();
    }

    @GetMapping("/time-slots/{id}")
    public TimeSlot getTimeSlotById(@PathVariable Long id) {
        Optional<TimeSlot> timeSlot = schedulingService.getTimeSlotById(id);
        if (timeSlot.isPresent()) {
            return timeSlot.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TimeSlot not found");
        }
    }

    @PostMapping("/time-slots")
    public TimeSlot createNewTimeSlot(@RequestBody TimeSlot timeSlot) {
        // Convert time to warehouse time zone
        ZonedDateTime startTime = timeSlot.getStartTime();
        timeSlot.setStartTime(startTime.withZoneSameInstant(ZoneId.of("Europe/Warsaw")));

        ZonedDateTime endTime = timeSlot.getEndTime();
        timeSlot.setEndTime(endTime.withZoneSameInstant(ZoneId.of("Europe/Warsaw")));

        // Check if the time slot is available
        Terminal terminal = timeSlot.getTerminal();
        if (!schedulingService.isTimeSlotAvailable(terminal, timeSlot.getStartTime(), timeSlot.getEndTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Time slot not available for the specified terminal");
        }

        return schedulingService.createNewTimeSlot(timeSlot);
    }

    @DeleteMapping("/time-slots/{id}")
    public void deleteTimeSlot(@PathVariable Long id) {
        schedulingService.deleteTimeSlot(id);
    }
}
