package com.bookstore.composite.dto;

import com.bookstore.core.model.Book;
import org.json.simple.JSONObject;

import java.util.Date;

public class GetBookResponseDto {

    private Book book;

    public GetBookResponseDto(Book book) {
        this.book = book;
    }

    public JSONObject getData(){
        return toJson();
    }

    /**
     * Converts to JSON
     *
     * @return JSON Object
     */
    private JSONObject toJson(){
        JSONObject data = new JSONObject();
        JSONObject bookObject = new JSONObject();
        bookObject.put("id",book.getId());
        bookObject.put("name",book.getName());
        bookObject.put("type", book.getType());
        bookObject.put("volume", book.getVolume());
        bookObject.put("generation", book.getGeneration());
        bookObject.put("author", book.getAuthor().getName());
        data.put("timestamp", new Date());
        data.put("book",bookObject);
        return data;
    }
}
