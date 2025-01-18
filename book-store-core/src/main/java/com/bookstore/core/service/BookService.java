package com.bookstore.core.service;

import com.bookstore.core.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    List<Book> getBookByAuthorId(int authorId);
    List<Book> getAll();
    Book getById(int id);
    List<Book> getBookByType(String type);
    List<Book> getBookByVolumeGreaterThan(int noOfVolumes);
    List<Book> getBookByVolumeLessThan(int noOfVolumes);
    List<Book> getBookByGeneration(int generation);
    Book create(Book book, int authorId);
    Book update(Book stock, int id);
    void delete(int id);

}
