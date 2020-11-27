package io.github.batetolast1.wedderforecast.model.entity.results;

import io.github.batetolast1.wedderforecast.model.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass

@NoArgsConstructor
@Getter
@Setter
public class UserResult extends Result {

    @ManyToOne
    private User user;

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
