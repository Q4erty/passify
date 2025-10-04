package com.portfolio.passify.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "c_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "c_id",  nullable = false, updatable = false)
    private UUID id;

    @Column(name = "c_name", nullable = false)
    private String name;

    @Column(name = "c_start")
    private LocalDateTime start;

    @Column(name = "c_end")
    private LocalDateTime end;

    @Column(name = "c_venue", nullable = false)
    private String venue;

    @Column(name = "c_sales_start")
    private LocalDateTime salesStart;

    @Column(name = "c_sales_end")
    private LocalDateTime salesEnd;

    @Column(name = "c_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_organizer_id")
    private UserEntity organizer;

    @ManyToMany(mappedBy = "attendingEvents")
    private List<UserEntity> attendees = new ArrayList<>();

    @ManyToMany(mappedBy = "staffingEvents")
    private List<UserEntity> staff = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<TicketTypeEntity> ticketTypes = new ArrayList<>();

    @CreatedDate
    @Column(name = "c_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "c_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(venue, that.venue) && Objects.equals(salesStart, that.salesStart) && Objects.equals(salesEnd, that.salesEnd) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, end, venue, salesStart, salesEnd, status, createdAt, updatedAt);
    }
}
