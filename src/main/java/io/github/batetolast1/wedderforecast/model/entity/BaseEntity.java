package io.github.batetolast1.wedderforecast.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass

@NoArgsConstructor
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
        this.updatedOn = null;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }
}
