package com.bookstore.composite.controller;

import com.bookstore.composite.dto.CreateBookRequestDto;
import com.bookstore.composite.dto.GetBookResponseDto;
import com.bookstore.composite.dto.GetBooksResponseDto;
import com.bookstore.composite.dto.UpdateBookRequestDto;
import com.bookstore.core.model.Author;
import com.bookstore.core.model.Book;
import com.bookstore.core.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class BookControllerTest {

    GetBooksResponseDto getBooksResponseDto;
    GetBookResponseDto getBookResponseDto;
    CreateBookRequestDto createBookRequestDto;
    UpdateBookRequestDto updateBookRequestDto;
    BookService bookService;
    UriComponentsBuilder componentsBuilder;
    ModelMapper mapper;
    @InjectMocks
    BookController bookController;

    @Before
    public void beforeMethod(){
        getBooksResponseDto = Mockito.mock(GetBooksResponseDto.class);
        getBookResponseDto = Mockito.mock(GetBookResponseDto.class);
        createBookRequestDto = Mockito.mock(CreateBookRequestDto.class);
        updateBookRequestDto = Mockito.mock(UpdateBookRequestDto.class);
        bookService = Mockito.mock(BookService.class);
        mapper = Mockito.mock(ModelMapper.class);
        componentsBuilder = Mockito.mock(UriComponentsBuilder.class);
        bookController = new BookController();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void get_all_books_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookService.getAll()).thenReturn(books);
        ResponseEntity<GetBooksResponseDto> response = bookController.getAll();
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void get_book_by_id_should_return_book_object(){
        Mockito.when(bookService.getById(Mockito.anyInt())).thenReturn(bookTestData());
        ResponseEntity<GetBookResponseDto> response = bookController.getBookById(1);
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void get_books_by_author_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookService.getBookByAuthorId(Mockito.anyInt())).thenReturn(books);
        ResponseEntity<GetBooksResponseDto> response = bookController.getBooksByAuthor(1);
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void get_books_by_type_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookService.getBookByType(Mockito.anyString())).thenReturn(books);
        ResponseEntity<GetBooksResponseDto> response = bookController.getBooksByType("ebook");
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void get_books_by_volume_greater_than_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookService.getBookByVolumeGreaterThan(Mockito.anyInt())).thenReturn(books);
        ResponseEntity<GetBooksResponseDto> response = bookController.getBooksByVolumeGreaterThan(1);
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void get_books_by_volume_less_than_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookService.getBookByVolumeLessThan(Mockito.anyInt())).thenReturn(books);
        ResponseEntity<GetBooksResponseDto> response = bookController.getBooksByVolumeLessThan(3);
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void get_books_by_generation_should_return_book_list(){
        List<Book> books = new ArrayList<>();
        books.add(bookTestData());
        Mockito.when(bookService.getBookByGeneration(Mockito.anyInt())).thenReturn(books);
        ResponseEntity<GetBooksResponseDto> response = bookController.getBooksByGeneration(5);
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void create_book_should_return_new_book(){
        CreateBookRequestDto bookRequestDto = new CreateBookRequestDto();
        bookRequestDto.setName("David Copperfield");
        bookRequestDto.setType("paperback");
        bookRequestDto.setVolume(3);
        bookRequestDto.setGeneration(6);
        bookRequestDto.setAuthorId(1);
        Mockito.when(bookService.create(Mockito.any(), Mockito.anyInt())).thenReturn(bookTestData());
        Mockito.when(componentsBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath("/v1/books"));
        ResponseEntity<GetBookResponseDto> response = bookController.create(1,bookRequestDto,componentsBuilder);
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.CREATED.value());
    }

    @Test
    public void update_book_should_update_the_existing_book(){
        UpdateBookRequestDto updateDto = new UpdateBookRequestDto();
        updateDto.setType("paperback");
        Mockito.when(bookService.update(Mockito.any(),Mockito.anyInt())).thenReturn(bookTestData());
        Mockito.when(componentsBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath("/v1/books"));
        ResponseEntity<GetBookResponseDto> response = bookController.updateById(1,updateDto,componentsBuilder);
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.CREATED.value());
    }

    @Test
    public void delete_book_should_delete_existing_book(){
        Mockito.doNothing().when(this.bookService).delete(1);
        ResponseEntity response = this.bookController.delete(1);
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.NO_CONTENT.value());
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
