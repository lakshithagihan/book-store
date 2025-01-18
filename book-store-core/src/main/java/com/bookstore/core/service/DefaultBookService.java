package com.bookstore.core.service;

import com.bookstore.core.model.Book;
import com.bookstore.core.repository.AuthorDetailRepository;
import com.bookstore.core.repository.BookDetailRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class DefaultBookService implements BookService{

    private final BookDetailRepository bookDetailRepository;

    private final AuthorDetailRepository authorDetailRepository;

    public DefaultBookService(BookDetailRepository bookDetailRepository, AuthorDetailRepository authorDetailRepository) {
        this.bookDetailRepository = bookDetailRepository;
        this.authorDetailRepository = authorDetailRepository;
    }

    /**
     * Get Book By Author Id
     *
     * @param authorId
     * @return List of Books
     */
    @Override
    public List<Book> getBookByAuthorId(int authorId) {
        if(authorId == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return bookDetailRepository.getBookByAuthorId(authorId);
    }

    /**
     * Get All Books
     *
     * @return List of Books
     */
    @Override
    public List<Book> getAll() {
        return bookDetailRepository.findAll();
    }

    /**
     * Get Book By Id
     *
     * @param id
     * @return Single Book Object
     */
    @Override
    public Book getById(int id) {
        Optional<Book> book = this.bookDetailRepository.findById(id);
        return book.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    /**
     * Get Books By Type
     *
     * @param type
     * @return List of Books
     */
    @Override
    public List<Book> getBookByType(String type) {
        if(type.equals(" ")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return bookDetailRepository.getBookByType(type);
    }

    /**
     * Get Books By Volume Greater Than
     *
     * @param noOfVolumes
     * @return List of Books
     */
    @Override
    public List<Book> getBookByVolumeGreaterThan(int noOfVolumes) {
        if(noOfVolumes < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return bookDetailRepository.getBookByVolumeGreaterThan(noOfVolumes);
    }

    /**
     * Get Books By Volume Less Than
     *
     * @param noOfVolumes
     * @return List of Books
     */
    @Override
    public List<Book> getBookByVolumeLessThan(int noOfVolumes) {
        if(noOfVolumes <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return bookDetailRepository.getBookByVolumeLessThan(noOfVolumes);
    }

    /**
     * Get Books By Generation
     *
     * @param generation
     * @return List of Books
     */
    @Override
    public List<Book> getBookByGeneration(int generation) {
        if(generation == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return bookDetailRepository.getBookByGeneration(generation);
    }

    /**
     * Create New Book
     *
     * @param book
     * @param authorId
     * @return Created Book Object
     */
    @Override
    public Book create(Book book, int authorId) {
        if(this.bookDetailRepository.existsBookByName(book.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return authorDetailRepository.findById(authorId).map(author -> {
            book.setAuthor(author);
            return bookDetailRepository.save(book);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    /**
     * Update an Existing Book
     *
     * @param book
     * @param id
     * @return Updated Book Object
     */
    @Override
    public Book update(Book book, int id) {
        Optional<Book> currentBook = this.bookDetailRepository.findById(id);
        if(currentBook.isPresent()){
            Book currentBookRecord = currentBook.get();
            if(book.getName() == null){
                currentBookRecord.setName(currentBookRecord.getName());
                currentBookRecord.setType(book.getType());
                currentBookRecord.setVolume(currentBookRecord.getVolume());
                currentBookRecord.setGeneration(currentBookRecord.getGeneration());
            }else if(book.getType() == null){
                currentBookRecord.setName(book.getName());
                currentBookRecord.setType(currentBookRecord.getType());
                currentBookRecord.setVolume(currentBookRecord.getVolume());
                currentBookRecord.setGeneration(currentBookRecord.getGeneration());
            }else{
                currentBookRecord.setName(book.getName());
                currentBookRecord.setType(book.getType());
                currentBookRecord.setVolume(currentBookRecord.getVolume());
                currentBookRecord.setGeneration(currentBookRecord.getGeneration());
            }
            return bookDetailRepository.save(currentBookRecord);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete an Existing Book By Id
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        Book book = this.getById(id);
        this.bookDetailRepository.delete(book);
    }
}
