package com.bookstore.composite.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateBookRequestDto {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotNull(message = "Type cannot be null")
    @NotEmpty(message = "Type should not be empty")
    private String type;

    /**
     * Get Name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Type
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Set Type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
