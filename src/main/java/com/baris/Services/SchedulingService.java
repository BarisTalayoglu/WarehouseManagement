package com.baris.Services;

import com.baris.Entity.Terminal;
import com.baris.Entity.TimeSlot;
import com.baris.Repositories.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulingService {
    private final TimeSlotRepository timeSlotRepository;

    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    public Optional<TimeSlot> getTimeSlotById(Long id) {
        return timeSlotRepository.findById(id);
    }

    public TimeSlot createNewTimeSlot(TimeSlot timeSlot) {
        // Convert time to the warehouse time zone
        timeSlot.setStartTime(timeSlot.getStartTime().withZoneSameInstant(ZoneId.of("Europe/Warsaw")));
        timeSlot.setEndTime(timeSlot.getEndTime().withZoneSameInstant(ZoneId.of("Europe/Warsaw")));

        // Check if the time slot is available
        Terminal terminal = timeSlot.getTerminal();
        if (!isTimeSlotAvailable(terminal, timeSlot.getStartTime(), timeSlot.getEndTime())) {
            throw new IllegalArgumentException("Time slot not available for the specified terminal");
        }

        // Save the time slot to the database
        return timeSlotRepository.save(timeSlot);
    }

    public void deleteTimeSlot(Long id) {
        timeSlotRepository.deleteById(id);
    }

    public List<TimeSlot> getTimeSlotsByTerminal(Terminal terminal) {
        return timeSlotRepository.findByTerminal(terminal);
    }

    public boolean isTimeSlotAvailable(Terminal terminal, ZonedDateTime startTime, ZonedDateTime endTime) {
        // Query the database to check for overlapping time slots
        List<TimeSlot> overlappingTimeSlots = timeSlotRepository.findByTerminalAndTimeRange(terminal, startTime, endTime);

        // Check if there are any overlapping time slots
        if (overlappingTimeSlots.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
