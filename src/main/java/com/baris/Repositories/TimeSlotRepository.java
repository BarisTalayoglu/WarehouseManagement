package com.baris.Repositories;
import com.baris.Entity.Terminal;
import com.baris.Entity.TimeSlot;
import com.baris.Entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    // Find available time slots for a given terminal
    List<TimeSlot> findByTerminalAndBookedFalse(Terminal terminal);

    // Find available time slots for a given truck
    List<TimeSlot> findByTruckAndBookedFalse(Truck truck);

}
