package com.bookstore.core.repository;

import com.bookstore.core.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDetailRepository extends JpaRepository<Book, Integer> {

    List<Book> getBookByAuthorId(int id);
    List<Book> getBookByType(String type);
    List<Book> getBookByVolumeGreaterThan(int noOfVolumes);
    List<Book> getBookByVolumeLessThan(int noOfVolumes);
    List<Book> getBookByGeneration(int generation);
    boolean existsBookByName(String name);
    Optional<Book> findByIdAndAuthorId(int id, int authorId);




}
