package com.portfolio.passify.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "t_ticket_validation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketValidationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "c_id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "c_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketValidationStatusEnum status;

    @Column(name = "c_validation_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketValidationMethodEnum validationMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_ticket_id")
    private TicketEntity ticket;

    @CreatedDate
    @Column(name = "c_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "c_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketValidationEntity that = (TicketValidationEntity) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, createdAt, updatedAt);
    }
}
