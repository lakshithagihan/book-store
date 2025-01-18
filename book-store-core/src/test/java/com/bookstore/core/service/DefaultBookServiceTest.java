package com.bookstore.core.service;

import com.bookstore.core.model.Author;
import com.bookstore.core.model.Book;
import com.bookstore.core.repository.AuthorDetailRepository;
import com.bookstore.core.repository.BookDetailRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultBookServiceTest {

    BookDetailRepository bookDetailRepository;
    AuthorDetailRepository authorDetailRepository;
    DefaultBookService bookService;

    @Before
    public void beforeMethod(){
        bookDetailRepository = Mockito.mock(BookDetailRepository.class);
        authorDetailRepository = Mockito.mock(AuthorDetailRepository.class);
        bookService = new DefaultBookService(bookDetailRepository,authorDetailRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void get_book_by_author_id_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookDetailRepository.getBookByAuthorId(Mockito.anyInt())).thenReturn(books);
        List<Book> booksResponse =bookService.getBookByAuthorId(1);
        Assert.assertEquals(bookTestData().getId(), booksResponse.get(0).getId());
        Assert.assertEquals(bookTestData().getName(), booksResponse.get(0).getName());
        Assert.assertEquals(bookTestData().getAuthor().getId(), booksResponse.get(0).getAuthor().getId());
    }

    @Test
    public void get_all_books_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookDetailRepository.findAll()).thenReturn(books);
        List<Book> booksResponse =bookService.getAll();
        Assert.assertEquals(bookTestData().getId(), booksResponse.get(0).getId());
        Assert.assertEquals(bookTestData().getName(), booksResponse.get(0).getName());
        Assert.assertEquals(bookTestData().getAuthor().getId(), booksResponse.get(0).getAuthor().getId());
    }

    @Test
    public void get_book_by_id_should_return_single_book_object(){
        Mockito.when(bookDetailRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bookTestData()));
        Book booksResponse =bookService.getById(1);
        Assert.assertEquals(bookTestData().getId(), booksResponse.getId());
        Assert.assertEquals(bookTestData().getName(), booksResponse.getName());
        Assert.assertEquals(bookTestData().getAuthor().getId(), booksResponse.getAuthor().getId());
    }

    @Test
    public void get_book_by_type_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookDetailRepository.getBookByType(Mockito.anyString())).thenReturn(books);
        List<Book> booksResponse =bookService.getBookByType("ebook");
        Assert.assertEquals(bookTestData().getId(), booksResponse.get(0).getId());
        Assert.assertEquals(bookTestData().getName(), booksResponse.get(0).getName());
        Assert.assertEquals(bookTestData().getAuthor().getId(), booksResponse.get(0).getAuthor().getId());
    }

    @Test
    public void get_book_by_volume_greater_than_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookDetailRepository.getBookByVolumeGreaterThan(Mockito.anyInt())).thenReturn(books);
        List<Book> booksResponse =bookService.getBookByVolumeGreaterThan(1);
        Assert.assertEquals(bookTestData().getId(), booksResponse.get(0).getId());
        Assert.assertEquals(bookTestData().getName(), booksResponse.get(0).getName());
        Assert.assertEquals(bookTestData().getAuthor().getId(), booksResponse.get(0).getAuthor().getId());
    }

    @Test
    public void get_book_by_volume_less_than_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookDetailRepository.getBookByVolumeLessThan(Mockito.anyInt())).thenReturn(books);
        List<Book> booksResponse =bookService.getBookByVolumeLessThan(3);
        Assert.assertEquals(bookTestData().getId(), booksResponse.get(0).getId());
        Assert.assertEquals(bookTestData().getName(), booksResponse.get(0).getName());
        Assert.assertEquals(bookTestData().getAuthor().getId(), booksResponse.get(0).getAuthor().getId());
    }

    @Test
    public void get_book_by_generation_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookDetailRepository.getBookByGeneration(Mockito.anyInt())).thenReturn(books);
        List<Book> booksResponse =bookService.getBookByGeneration(5);
        Assert.assertEquals(bookTestData().getId(), booksResponse.get(0).getId());
        Assert.assertEquals(bookTestData().getName(), booksResponse.get(0).getName());
        Assert.assertEquals(bookTestData().getAuthor().getId(), booksResponse.get(0).getAuthor().getId());
    }

    @Test
    public void create_book_should_create_a_new_book(){
        Author author = new Author();
        author.setId(1);
        author.setName("Charles Dickens");

        Book book = new Book();
        book.setId(2);
        book.setName("David Copperfield");
        book.setType("paperback");
        book.setVolume(3);
        book.setGeneration(6);
        book.setAuthor(author);

        Mockito.when(bookDetailRepository.save(Mockito.any())).thenReturn(book);
        Mockito.when(authorDetailRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(author));
        Book booksResponse = bookService.create(book,1);
        Assert.assertEquals(book.getId(), booksResponse.getId());
        Assert.assertEquals(book.getName(), booksResponse.getName());
        Assert.assertEquals(book.getAuthor().getId(), booksResponse.getAuthor().getId());
    }

    @Test
    public void create_book_already_exists_and_should_throw_the_exception(){
        try{
            Mockito.when(this.bookDetailRepository.existsBookByName(bookTestData().getName())).thenReturn(true);
            this.bookService.create(bookTestData(),1);
        }catch (ResponseStatusException exception){
            Assert.assertEquals(exception.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    public void delete_book_should_delete_the_existing_book(){
        Mockito.when(this.bookDetailRepository.findById(1)).thenReturn(Optional.of(bookTestData()));
        this.bookService.delete(1);
        Book book = this.bookDetailRepository.getOne(1);
        Assert.assertNull(book);
    }

    private Book bookTestData(){
        Author author = new Author();
        author.setId(1);
        author.setName("Charles Dickens");

        Book book = new Book();
        book.setId(1);
        book.setName("Oliver Twist");
        book.setType("ebook");
        book.setVolume(2);
        book.setGeneration(5);
        book.setAuthor(author);

        return book;
    }



}
