package com.cepheid.cloud.skel.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@org.hibernate.annotations.Immutable
public class Description extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String description;

    @Column(nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="item_id", nullable = false)
    private Item item;

    // JPA usage only
    private Description() {

    }

    public Description(@NotNull String description, @NotNull String type, @NotNull Item item) {
        this.description = description;
        this.type = type;
        this.createdTimeStamp = Timestamp.valueOf(LocalDateTime.now());

        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Description that = (Description) o;
        return Objects.equals(description, that.description) && Objects.equals(type, that.type) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, type, item);
    }
}
