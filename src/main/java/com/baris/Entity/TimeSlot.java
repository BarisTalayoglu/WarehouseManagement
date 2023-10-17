package com.baris.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor @AllArgsConstructor
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Future(message = "Start time must be in the future")
    private Date startTime;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Future(message = "End time must be in the future")
    private Date endTime;

    @ManyToOne
    @NotNull(message = "Terminal is required")
    @Getter @Setter
    private Terminal terminal;

    @ManyToOne
    @Getter @Setter
    private Truck truck;

    @Getter @Setter
    private boolean booked;

    @NotBlank(message = "Status is required")
    @Getter @Setter
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

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
