package com.bookstore.core.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.sql.Timestamp;

public class BaseEntity {

    @Column(name="created_date",nullable = false,updatable = false)
    @CreatedDate
    private Timestamp createdDate;
    @Column(name="modified_date",nullable = true,updatable = true)
    @LastModifiedDate
    private Timestamp modifiedDate;

    /**
     * Get Created Date
     *
     * @return createdDate
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * Set Created Date
     *
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Get Modified Date
     *
     * @return modifiedDate
     */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Set Modified Date
     *
     * @param modifiedDate
     */
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
