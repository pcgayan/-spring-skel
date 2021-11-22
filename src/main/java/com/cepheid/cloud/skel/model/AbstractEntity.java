package com.cepheid.cloud.skel.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Version
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Integer version;

    protected Timestamp createdTimeStamp;

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(getVersion(), that.getVersion())
                && Objects.equals(getCreatedTimeStamp(), that.getCreatedTimeStamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getVersion(), getCreatedTimeStamp());
    }
}
