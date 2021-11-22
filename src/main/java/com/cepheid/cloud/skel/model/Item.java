package com.cepheid.cloud.skel.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@org.hibernate.annotations.Immutable
public class Item extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String state;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Description> descriptions;

    // JPA usage only
    private Item() {

    }

    public Item(@NotNull String title, @NotNull String state) {
        this.title = title;
        this.state = state;
        this.createdTimeStamp = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return Objects.equals(title, item.title) && Objects.equals(state, item.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, state);
    }

    /*@Override
    public String toString() {
        return title;
    }*/

    public void setDescriptions(@NotNull Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Set<Description> getDescriptions() {
        return descriptions;
    }
}
