package com.bookstore.composite.controller;

import com.bookstore.composite.dto.CreateBookRequestDto;
import com.bookstore.composite.dto.GetBookResponseDto;
import com.bookstore.composite.dto.GetBooksResponseDto;
import com.bookstore.composite.dto.UpdateBookRequestDto;
import com.bookstore.core.model.Book;
import com.bookstore.core.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "book", description = "The Book Store API")
@RestController
@RequestMapping("/")
public class BookController {

    private static final String GET_AND_CREATE_BOOK_BY_AUTHOR = "/v1/author/{authorId}/books";
    private static final String GET_BOOK_BY_TYPE = "/v1/books/type/{type}";
    private static final String GET_BOOK_BY_VOLUME_GREATER_THAN = "/v1/books/volumes/gt/{noOfVolumes}";
    private static final String GET_BOOK_BY_VOLUME_LESS_THAN = "/v1/books/volumes/lt/{noOfVolumes}";
    private static final String GET_BOOK_BY_GENERATION = "/v1/books/gen/{generation}";
    private static final String GET_ALL_BOOKS = "/v1/books";
    private static final String BOOK_BY_ID = "/v1/books/{id}";

    @Autowired
    BookService bookService;

    @Autowired
    ModelMapper mapper;

    /**
     * Get All Books
     *
     * @return List of books
     */
    @Operation(summary = "Get All Books", description = "Returns a book array", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content)
    })
    @GetMapping(value = GET_ALL_BOOKS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBooksResponseDto> getAll(){
        GetBooksResponseDto response = new GetBooksResponseDto(this.bookService.getAll());
        return ResponseEntity.ok(response);
    }

    /**
     * Get Book By Id
     *
     * @param id
     * @return Single book object
     */
    @Operation(summary = "Get Book By Id", description = "Returns a single book", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book is not found", content = @Content)
    })
    @GetMapping(value = BOOK_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBookResponseDto> getBookById(@PathVariable Integer id){
        GetBookResponseDto response = new GetBookResponseDto(this.bookService.getById(id));
        return ResponseEntity.ok(response);
    }

    /**
     * Get Books By Author
     *
     * @param authorId
     * @return List of books
     */
    @Operation(summary = "Get Books By Author", description = "Returns a book array", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content)
    })
    @GetMapping(value = GET_AND_CREATE_BOOK_BY_AUTHOR, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBooksResponseDto> getBooksByAuthor(@PathVariable Integer authorId){
        GetBooksResponseDto response = new GetBooksResponseDto(this.bookService.getBookByAuthorId(authorId));
        return ResponseEntity.ok(response);
    }

    /**
     * Get Books By Type
     *
     * @param type
     * @return List of books
     */
    @Operation(summary = "Get Books By Type", description = "Returns a book array", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content)
    })
    @GetMapping(value = GET_BOOK_BY_TYPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBooksResponseDto> getBooksByType(@PathVariable String type){
        GetBooksResponseDto response = new GetBooksResponseDto(this.bookService.getBookByType(type));
        return ResponseEntity.ok(response);
    }

    /**
     * Get Books By Volume Grater Than Given Volume
     *
     * @param noOfVolumes
     * @return List of books
     */
    @Operation(summary = "Get Books By Volume Grater Than Given Volume", description = "Returns a book array", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content)
    })
    @GetMapping(value = GET_BOOK_BY_VOLUME_GREATER_THAN, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBooksResponseDto> getBooksByVolumeGreaterThan(@PathVariable Integer noOfVolumes){
        GetBooksResponseDto response = new GetBooksResponseDto(this.bookService.getBookByVolumeGreaterThan(noOfVolumes));
        return ResponseEntity.ok(response);
    }

    /**
     * Get Books By Volume Less Than Given Volume
     *
     * @param noOfVolumes
     * @return List of books
     */
    @Operation(summary = "Get Books By Volume Less Than Given Volume", description = "Returns a book array", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content)
    })
    @GetMapping(value = GET_BOOK_BY_VOLUME_LESS_THAN, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBooksResponseDto> getBooksByVolumeLessThan(@PathVariable Integer noOfVolumes){
        GetBooksResponseDto response = new GetBooksResponseDto(this.bookService.getBookByVolumeLessThan(noOfVolumes));
        return ResponseEntity.ok(response);
    }

    /**
     * Get Books By Generation
     *
     * @param generation
     * @return List of books
     */
    @Operation(summary = "Get Books By Generation", description = "Returns a book array", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content)
    })
    @GetMapping(value = GET_BOOK_BY_GENERATION, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBooksResponseDto> getBooksByGeneration(@PathVariable Integer generation){
        GetBooksResponseDto response = new GetBooksResponseDto(this.bookService.getBookByGeneration(generation));
        return ResponseEntity.ok(response);
    }

    /**
     * Create New Book
     *
     * @param authorId
     * @param bookRequestDto
     * @param builder
     * @return Single book object
     */
    @Operation(summary = "Create New Book", description = "Returns the created book", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Book Already Exists", content = @Content)
    })
    @PostMapping(value = GET_AND_CREATE_BOOK_BY_AUTHOR, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBookResponseDto> create(@PathVariable Integer authorId, @Valid @RequestBody CreateBookRequestDto bookRequestDto, UriComponentsBuilder builder){
        Book book = this.mapper.map(bookRequestDto, Book.class);
        Book createBook = bookService.create(book, authorId);
        UriComponents componentsBuilder = builder.path(BOOK_BY_ID).buildAndExpand(createBook.getId());
        GetBookResponseDto response = new GetBookResponseDto(createBook);
        return ResponseEntity.created(componentsBuilder.toUri()).body(response);
    }

    /**
     * Update Existing Book
     *
     * @param id
     * @param bookRequestDto
     * @param builder
     * @return Single book object
     */
    @Operation(summary = "Update Existing Book By Id", description = "Returns the updated book", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created", content = @Content),
            @ApiResponse(responseCode = "404", description = "Updating Book Not Found", content = @Content)
    })
    @PutMapping(value = BOOK_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBookResponseDto> updateById(@PathVariable Integer id, @Valid @RequestBody UpdateBookRequestDto bookRequestDto, UriComponentsBuilder builder){
        Book book = this.mapper.map(bookRequestDto, Book.class);
        Book createBook = bookService.update(book, id);
        UriComponents componentsBuilder = builder.path(BOOK_BY_ID).buildAndExpand(createBook.getId());
        GetBookResponseDto response = new GetBookResponseDto(createBook);
        return ResponseEntity.created(componentsBuilder.toUri()).body(response);
    }

    /**
     * Delete an Existing Book
     *
     * @param id
     * @return null
     */
    @Operation(summary = "Delete Existing Book", description = "No content is return", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully Deleted", content = @Content)
    })
    @DeleteMapping(value = BOOK_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Integer id){
        this.bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
