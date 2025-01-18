package com.bookstore.composite.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateBookRequestDto {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotNull(message = "Type cannot be null")
    @NotEmpty(message = "Type should not be empty")
    private String type;

    @NotNull(message = "Volume cannot be null")
    private int volume;

    @NotNull(message = "Generation cannot be null")
    private int generation;

    @NotNull(message = "Author Id cannot be null")
    private int authorId;

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

    /**
     * Get Volume
     *
     * @return volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Set Volume
     *
     * @param volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Get Generation
     *
     * @return generation
     */
    public int getGeneration() {
        return generation;
    }

    /**
     * Set Generation
     *
     * @param generation
     */
    public void setGeneration(int generation) {
        this.generation = generation;
    }

    /**
     * Get Author Id
     *
     * @return Author Id
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Set Author Id
     *
     * @param authorId
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
