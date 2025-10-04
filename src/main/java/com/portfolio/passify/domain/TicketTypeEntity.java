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
@Table(name = "t_ticket_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketTypeEntity {

    @Id
    @Column(name = "c_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "c_name", nullable = false)
    private String name;

    @Column(name = "c_price", nullable = false)
    private Double price;

    @Column(name = "c_total_available")
    private Integer totalAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_event_id")
    private EventEntity event;

    @OneToMany(mappedBy = "ticketType", cascade = CascadeType.ALL)
    private List<TicketEntity> tickets = new ArrayList<>();

    @CreatedDate
    @Column(name = "c_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "c_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketTypeEntity that = (TicketTypeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(totalAvailable, that.totalAvailable) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, totalAvailable, createdAt, updatedAt);
    }
}
