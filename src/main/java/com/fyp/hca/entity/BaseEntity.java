package com.fyp.hca.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
@JsonIgnoreProperties(value = {"createdOn", "updatedOn"}, allowGetters = true)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CREATED_ON", nullable = false, updatable = false)
    @CreatedDate
    private ZonedDateTime createdOn;

    @Column(name = "UPDATED_ON")
    @LastModifiedDate
    private ZonedDateTime updatedOn;

    // Getters and setters...

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public ZonedDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(ZonedDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
