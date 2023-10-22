package com.baris.Services;
import com.baris.Entity.Terminal;
import com.baris.Entity.TimeSlot;
import com.baris.Entity.Truck;
import com.baris.Repositories.TimeSlotRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SchedulingService {
    private final TimeSlotRepository timeSlotRepository;

    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    public List<TimeSlot> getAvailableTimeSlotsForTerminal(Terminal terminal) {
        return timeSlotRepository.findByTerminalAndBookedFalse(terminal);
    }

    public List<TimeSlot> getAvailableTimeSlotsForTruck(Truck truck) {
        return timeSlotRepository.findByTruckAndBookedFalse(truck);
    }

    public TimeSlot createScheduledTimeSlot(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }

    public TimeSlot updateScheduledTimeSlot(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }

    public void deleteScheduledTimeSlot(Long id) {
        timeSlotRepository.deleteById(id);
    }
}
