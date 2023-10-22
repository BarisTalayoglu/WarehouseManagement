package com.baris.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Data
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    @Future(message = "Start time must be in the future")
    private ZonedDateTime startTime;

    @Column(name = "end_time")
    @Future(message = "End time must be in the future")
    private ZonedDateTime endTime;

    @ManyToOne
    @NotNull(message = "Terminal is required")
    private Terminal terminal;

    @ManyToOne
    private Truck truck;

    private boolean booked;

    @NotBlank(message = "Status is required")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public ZonedDateTime toWarehouseTimeZone() {
        return startTime.withZoneSameInstant(ZoneId.of("Europe/Warsaw"));
    }


    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

}
