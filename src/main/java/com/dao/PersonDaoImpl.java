package com.dao;

import com.exception.PersonNotFoundException;
import com.model.Person;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class PersonDaoImpl implements PersonDao {
    private final String GET_PERSON = "SELECT * FROM person";
    private final String REMOVE_PERSON = "DELETE FROM person WHERE personId = ?";
    private final String UPDATE_PERSON = "UPDATE person SET personName = ? ,personSurname = ?  WHERE  personId = ?";
    private final String ADD_PERSON = "INSERT INTO person(personId,personName,personSurname) VALUES(?,?,?)";
    private Connection connection= null;
    private boolean isThere;
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
try{
    PreparedStatement preparedStatement = connection.prepareStatement(query);

    for (Object parameters:queryParameters) {
        preparedStatement.setObject(indis,parameters);
        indis++;
    }
    preparedStatement.executeUpdate();
    LOGGER.info("execute metodunda sıkıntı yok.");
}
catch (Exception e)
{
    e.printStackTrace();
}

    }

    public void getPerson() {
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(GET_PERSON);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println("personId  : "+resultSet.getInt(1)+"   personName  : "+resultSet.getString(2)+
                        "  personSurname : "+  resultSet.getString(3));

            }
            LOGGER.info("select islemi basarili.");
        }
        catch (SQLException e)
        {
            LOGGER.info("select islemi basarisiz.");
        }
    }

    public boolean getFindById(int id){

try {

    PreparedStatement preparedStatement =connection.prepareStatement(GET_PERSON);
    ResultSet resultSet = preparedStatement.executeQuery();
    isThere = false;
    while (resultSet.next())
    {

        if(resultSet.getInt(1)==id)
        {
            isThere = true;
            break;
        }

    }


}
catch (SQLException e)
{
   LOGGER.error("getfindbyid hatalı");
}
        return isThere;
    }

    public void removePerson(int personId) throws Exception {
        if (getFindById(personId))
        {
            execute(REMOVE_PERSON,personId);
            LOGGER.info("delete islemi basarili");
        }
        else
        {
            LOGGER.error("delete islemi basarisiz");
            throw new PersonNotFoundException("silinecek boyle bir kayit yok");
        }
    }

    public void updatePerson(Person person) throws Exception {


        if (getFindById(person.getId()))
        {
            execute(UPDATE_PERSON, person.getName(), person.getSurname(), person.getId());
            LOGGER.info("update islemi basarili.");

        }
        else{
            LOGGER.error("update islemi basarisiz.");
            throw new PersonNotFoundException("guncellenecek boyle bir kayit yok");
        }


    }

    public void addPerson(Person person){
        System.out.println(person.toString());
        if (getFindById(person.getId())!=true)
           {
            execute(ADD_PERSON, person.getId(), person.getName(), person.getSurname());
            LOGGER.info("insert islemi basarili");
           }
       else{
            LOGGER.error(" insert işlemi basarisiz zaten boyle bir kayit mevcut ");
            throw new PersonNotFoundException("zaten boyle bir kayit var");
           }

    }
}
