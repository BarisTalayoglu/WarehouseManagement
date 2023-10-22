package com.baris.Repositories;
import com.baris.Entity.Terminal;
import com.baris.Entity.TimeSlot;
import com.baris.Entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    // Find available time slots for a given terminal
    List<TimeSlot> findByTerminalAndBookedFalse(Terminal terminal);

    @Query("SELECT t FROM TimeSlot t WHERE t.terminal = :terminal AND t.startTime <= :endTime AND t.endTime >= :startTime")
    List<TimeSlot> findByTerminalAndTimeRange(@Param("terminal") Terminal terminal, @Param("startTime") ZonedDateTime startTime, @Param("endTime") ZonedDateTime endTime);
    List<TimeSlot> findByTerminal(Terminal terminal);
    // Find available time slots for a given truck
    List<TimeSlot> findByTruckAndBookedFalse(Truck truck);

}
