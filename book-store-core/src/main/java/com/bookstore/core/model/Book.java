package com.bookstore.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Book")
@Table(name = "book")
public class Book extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="volume")
    private int volume;

    @Column(name="generation")
    private int generation;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonIgnore
    private Author author;

    /**
     * Get Id
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set Id
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * Get Author
     *
     * @return author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Set Author
     *
     * @param author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }
}
