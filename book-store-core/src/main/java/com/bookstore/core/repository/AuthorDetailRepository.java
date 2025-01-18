package com.bookstore.core.repository;

import com.bookstore.core.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDetailRepository extends JpaRepository<Author, Integer> {
}
