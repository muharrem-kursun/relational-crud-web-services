package com.controller;

import com.model.Book;
import com.service.BookServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/books")
public class BookController {
    BookServiceImpl bookService;
    public BookController() {
        bookService= new BookServiceImpl();
    }

    @GET
    @Path("/books-select")
    public Response getBook() {

        try {
            bookService.getBook();
            return Response.status(200).entity("selected").build();
        }catch (Exception e)
        {
            return Response.status(500).entity("not selected"+e).build();
        }
    }

    @DELETE
    @Path("/book-delete")//book-delete;bookıd=5; gibi
    public Response removeBook(@MatrixParam("bookId") int bookId) {

        try {
            bookService.removeBook(bookId);
            return Response.status(200).entity("removed").build();
        }catch (Exception e)
        {
            return Response.status(500).entity("not removed"+e).build();
        }
    }

    @PUT
    @Path("/book-update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book) {
        try {
            bookService.updateBook(book);
            return Response.status(200).entity("updated").build();
        }catch (Exception e)
        {
            return Response.status(500).entity("not updated"+e).build();
        }

    }

    @POST
    @Path("/book-insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {


        try {
            bookService.addBook(book);
            return Response.status(200).entity("inserted").build();
        }catch (Exception e)
        {
            return Response.status(500).entity("not inserted"+e).build();
        }

    }
    @GET
    @Path("/book-select-join")
    public Response getJoinBook() {

        try {
            bookService.getJoinBook();
            return Response.status(200).entity("select-join successful").build();
        }catch (Exception e)
        {
            return Response.status(500).entity("select-join unsuccessful"+e).build();
        }
    }
}
