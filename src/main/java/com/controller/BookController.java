package com.controller;

import com.model.Book;
import com.service.BookServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/books")
public class BookController {
    BookServiceImpl bookService;
    public BookController() {
        bookService= new BookServiceImpl();
    }

    @GET
    @Path("/books-select")
    public void getBook() {
       bookService.getBook();
    }

    @DELETE
    @Path("/book-delete")//book-delete;bookıd=5; gibi
    public void removeBook(@MatrixParam("bookId") int bookId) {
        bookService.removeBook(bookId);
    }

    @PUT
    @Path("/book-update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBook(Book book) {
        bookService.updateBook(book);
    }

    @POST
    @Path("/book-insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addBook(Book book) {

        bookService.addBook(book);

    }
    @GET
    @Path("/book-select-join")
    public void getJoinBook() {
        bookService.getJoinBook();
    }
}
