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
@Table(name = "t_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @Column(name = "c_id",  nullable = false, updatable = false)
    private UUID id;

    @Column(name = "c_name", nullable = false)
    private String name;

    @Column(name = "c_email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private List<EventEntity> organizedEvents = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "t_user_attending_events",
            joinColumns = @JoinColumn(name = "c_user_id"),
            inverseJoinColumns = @JoinColumn(name = "c_event_id")
    )
    private List<EventEntity> attendingEvents = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "t_user_staffing_events",
            joinColumns = @JoinColumn(name = "c_user_id"),
            inverseJoinColumns = @JoinColumn(name = "c_event_id")
    )
    private List<EventEntity> staffingEvents = new ArrayList<>();

    @CreatedDate
    @Column(name = "c_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "c_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, createdAt, updatedAt);
    }
}

