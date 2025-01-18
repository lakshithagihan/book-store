package com.bookstore.core.configuration;

import com.bookstore.core.repository.AuthorDetailRepository;
import com.bookstore.core.repository.BookDetailRepository;
import com.bookstore.core.service.BookService;
import com.bookstore.core.service.DefaultBookService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BookStoreServiceSpringConfiguration implements WebMvcConfigurer {

    /**
     * Model Mapper Bean
     *
     * @return New Model Mapper
     */
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    /**
     * Book Service Bean
     *
     * @param bookDetailRepository
     * @param authorDetailRepository
     * @return Book Service
     */
    @Bean
    BookService getBookService(BookDetailRepository bookDetailRepository, AuthorDetailRepository authorDetailRepository){
       return new DefaultBookService(bookDetailRepository, authorDetailRepository);
    }
}
