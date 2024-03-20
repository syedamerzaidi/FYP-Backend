package com.fyp.hca.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties(value = {"createdOn", "updatedOn"}, allowGetters = true)
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "CREATED_ON", nullable = false, updatable = false)
    @CreatedDate
    private ZonedDateTime createdOn;

    @Column(name = "UPDATED_ON")
    @LastModifiedDate
    private ZonedDateTime updatedOn;

    public BaseEntity() {
        this.createdOn = ZonedDateTime.now();
    }

    @PreUpdate
    public void setUpdatedOn() {
        this.updatedOn = ZonedDateTime.now();
    }
}
