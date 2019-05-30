package com.dao;

import com.model.PersonModel;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class PersonDaoImpl implements PersonDao {
    private final String PERSON_SELECT = "SELECT * FROM person";
    private final String PERSON_DELETE = "DELETE FROM person WHERE personId = ?";
    private final String PERSON_UPDATE = "UPDATE person SET personName = ? ,personSurname = ?  WHERE  personId = ?";
    private final String PERSON_INSERT = "INSERT INTO person(personId,personName,personSurname) VALUES(?,?,?)";
    private Connection connection= null;
    private final Logger LOGGER = Logger.getLogger(BookDaoImlp.class);


    public PersonDaoImpl() {
        connection = getConnection();
    }

    private Connection getConnection() {
        Properties properties = new Properties();
        InputStream inputStream ;
        inputStream = getClass().getResourceAsStream("/write.properties");
        PropertyConfigurator.configure(inputStream);
        inputStream = getClass().getResourceAsStream("/db.properties");
        try {
            properties.load(inputStream);
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
            LOGGER.info("veritabanına başarılı bir şekilde bağlanıldı.");
        }
        catch (Exception e)
        {
            LOGGER.error("veritabanına bağlanırken bir hata oluştu.");
        }

        return connection;
    }

    private void execute(String query,Object ... queryParameters)
    {

        int indis =1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (Object parameters:queryParameters) {
                preparedStatement.setObject(indis,parameters);
                indis++;
            }
            preparedStatement.executeUpdate();
            LOGGER.info("execute metodunda sıkıntı yok.");
        }
        catch (SQLException e)
        {
            LOGGER.error("execute metodunda hata var.");
        }

    }

    public void personSelectDao() {
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(PERSON_SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println("personId  : "+resultSet.getInt(1)+"   personName  : "+resultSet.getString(2)+
                        "  personSurname : "+  resultSet.getString(3));

            }
            LOGGER.info("select islemi basarili. ");
        }
        catch (SQLException e)
        {
            LOGGER.info("select islemi basarisiz.");
        }
    }

    public void personDeleteDao(int personId) {
            execute(PERSON_DELETE,personId);
            LOGGER.info("delete islemi basarli");
    }

    public void personUpdateDao(PersonModel personModel) {
        execute(PERSON_UPDATE,personModel.getPersonName(),personModel.getPersonSurname(),personModel.getPersonId());
        LOGGER.info("update  islemi basarli");
    }

    public void personInsertDao(PersonModel personModel) {
        execute(PERSON_INSERT,personModel.getPersonId(),personModel.getPersonName(),personModel.getPersonSurname());
        LOGGER.info("insert islemi basarli");

    }
}
