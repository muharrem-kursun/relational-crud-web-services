package com.controller;

import com.model.BookModel;
import com.service.BookServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/books")
public class BookControllerImpl implements BookController {
   BookServiceImpl bookService = new BookServiceImpl();

    @GET
    @Path("/books-select")
    public void bookSelectController() {
       bookService.bookSelectService();
    }

    @DELETE
    @Path("/book-delete")//book-delete;bookıd=5; gibi
    public void bookDeleteController(@MatrixParam("bookId") int bookId) {
        bookService.bookDeleteService(bookId);
    }

    @PUT
    @Path("/book-update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void bookUpdateController(BookModel bookModel) {
        System.out.println(bookModel.toString());
        bookService.bookUpdateService(bookModel);
    }

    @POST
    @Path("/book-insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public void bookInsertController(BookModel bookModel) {

        bookService.bookInsertService(bookModel);
        System.out.println(bookModel.toString());

    }
    @GET
    @Path("/book-select-join")
    public void bookSelectJoincontroller() {
        bookService.bookSelectJoinService();
    }
}
