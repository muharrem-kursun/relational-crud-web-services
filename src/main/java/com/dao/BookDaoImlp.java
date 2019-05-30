package com.dao;

import com.model.Book;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class BookDaoImlp implements BookDao {
    private final String GET_BOOK = "SELECT * FROM book";
    private final String ADD_BOOK =  "INSERT INTO book(personId,bookId,bookName,bookAuthor) VALUES(?,?,?,?)";
    private final String UPDATE_BOOK = "UPDATE book SET  personId = ?,bookName = ?,bookAuthor = ? WHERE bookId = ?";
    private final String REMOVE_BOOK = "DELETE FROM book WHERE bookId = ?";
    private final String GET_JOIN_BOOK = "SELECT book.bookId,person.personName FROM book INNER JOIN person ON (book.personId = person.personId) ";
   private Connection connection= null;
    private final Logger LOGGER = Logger.getLogger(BookDaoImlp.class);
    public BookDaoImlp() {
        connection = getConnection();
    }

    private Connection getConnection() {
        Properties properties = new Properties();
        InputStream prop ;
        InputStream log;
        log = getClass().getResourceAsStream("/write.properties");
        PropertyConfigurator.configure(log);
        prop = getClass().getResourceAsStream("/db.properties");
        try {
            properties.load(prop);
            LOGGER.info("db.properties dosyasi basarili bir sekilde yuklendi");
        }
        catch (IOException e)
        {
            LOGGER.error("db properties dosyasi yuklenemedi.");
        }
        String userName = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClassName = properties.getProperty("driverClassName");
        properties.setProperty("user",userName);
        properties.setProperty("password",password);
        properties.setProperty("useUnicode","yes");
        properties.setProperty("characterEncoding","UTF-8");
        properties.setProperty("serverTimezone","UTC");
        properties.setProperty("autoReconnect","true");
        properties.setProperty("useSSL","false");
        try{
            Class.forName(driverClassName);
            LOGGER.info("driverClassName basarili bir sekilde yuklendi.");
        }
        catch (ClassNotFoundException e)
        {
            LOGGER.error("driverClassName yuklenemedi");
        }
        try{
            connection = DriverManager.getConnection(url,properties);
            LOGGER.info("veritabanina başarili bir şekilde bağlanildi.");
        }
        catch (Exception e)
        {
            LOGGER.error("veritabanina bağlanirken bir hata oluştu.");
        }

        return connection;
    }

    private void execute(String query,Object ... queryParameters)
    {

        int indis =1;
        try {
            PreparedStatement  preparedStatement = connection.prepareStatement(query);
            for (Object parameters:queryParameters) {
                preparedStatement.setObject(indis,parameters);
                indis++;
            }
            preparedStatement.executeUpdate();
            LOGGER.info("execute metodunda sikinti yok.");
        }
        catch (SQLException e)
        {
            LOGGER.error("execute metodunda hata var.");
        }

    }
    public void getJoinBook() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_JOIN_BOOK);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println("kitap id :  "+resultSet.getInt(1)+"  person name : "+resultSet.getString(2));
            }
            LOGGER.info("join islemi basarili");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            LOGGER.error("join islemi basarisiz");
        }


    }

    public void getBooks() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println("personId : " +resultSet.getInt(1)+"   bookId :  "+resultSet.getInt(2)+
                        "  bookName : "+resultSet.getString(3)+"   bookAuthor : "+ resultSet.getString(4));
            }
            LOGGER.info("select islemi basarili");
        }
        catch (SQLException e)
        {
            LOGGER.error("select islemi basarisiz");
        }


    }

    public void removeBooks(int bookId) {
        execute(REMOVE_BOOK,bookId);
    }

    public void updateBook(Book book) {
        execute(UPDATE_BOOK, book.getPersonId(), book.getName(), book.getAuthor(), book.getId());
    }

    public void addBook(Book book) {
        execute(ADD_BOOK, book.getPersonId(), book.getId(), book.getName(), book.getAuthor());
    }
}
