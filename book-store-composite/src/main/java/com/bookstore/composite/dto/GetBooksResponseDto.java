package com.bookstore.composite.dto;

import com.bookstore.core.model.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;
import java.util.List;

public class GetBooksResponseDto {

    private List<Book> books;

    public GetBooksResponseDto(List<Book> books) {
        this.books = books;
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
        data.put("timestamp", new Date());
        JSONArray array = new JSONArray();
        for(Book book : this.books){
            JSONObject bookObject = new JSONObject();
            bookObject.put("id",book.getId());
            bookObject.put("name",book.getName());
            bookObject.put("type", book.getType());
            bookObject.put("volume", book.getVolume());
            bookObject.put("generation", book.getGeneration());
            bookObject.put("author", book.getAuthor().getName());
            array.add(bookObject);
        }
        data.put("books",array);
        return data;
    }
}
