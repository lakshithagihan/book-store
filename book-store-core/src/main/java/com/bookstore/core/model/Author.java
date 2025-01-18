package com.bookstore.core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Author")
@Table(name = "author")
public class Author extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();

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
     * Get Books
     *
     * @return books
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * Set Books
     *
     * @param books
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
