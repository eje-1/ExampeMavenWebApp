package com.example.demo.service;

import com.example.demo.model.PersonF;
import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class Connet {

    //properties on database
    private static final String url = "jdbc:postgresql://localhost:5432/simpledb";
    private static final String user = "ernest";
    private static final String pass = "admin";
    private JdbcTemplate jdbcTemplate;
    EntityManagerFactory entityManagerFactory;

    private static Connet instance;
    private Connection connection;

    public static Connet getInstance() {
        if (instance == null) {
            instance = new Connet();
        }
        return instance;
    }

    // create a mysql database connection
    public Connet() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to the SQL server");
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Function that searches the database and save in User List
    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        try {

            String query = "SELECT * FROM users";

            // create a Statement from the connection
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("vorname"),
                        rs.getString("email"),
                        rs.getString("telefon"),
                        rs.getString("strasse"),
                        rs.getString("ort"),
                        rs.getString("plz"),
                        rs.getString("sex"),
                        rs.getString("geburtstag"),
                        rs.getString("spitzname")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    // Save User function
    public boolean saveUser(User user) {
        try {

            // the mysql insert statement
            String query = "INSERT INTO users (name, vorname, email, telefon, strasse, ort, plz, sex, geburtstag, spitzname)" +
                    "VALUES ("
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, user.getName());
            st.setString(2, user.getVorname());
            st.setString(3, user.getEmail());
            st.setString(4, user.getTelefon());
            st.setString(5, user.getStrasse());
            st.setString(6, user.getOrt());
            st.setString(7, user.getPlz());
            st.setString(8, user.getSex());
            st.setString(9, user.getGeburtstag());
            st.setString(10, user.getSpitzname());

            int result = st.executeUpdate();
            st.close();

            if (result != 0) {
                return true;
            } else {
                System.out.println("Error");
            }

            System.out.println("Add");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    // delete funcktion
    public void delete(int id) {

        String query = "DELETE FROM users WHERE id=?";

        try {

            connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public PersonF getId(int id) {

        String query = "SELECT * FROM users WHERE id=?";
        PersonF users = new PersonF();

        try {

            connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                users.setId(rs.getInt("id"));
                users.setName(rs.getString("name"));
                users.setVorname(rs.getString("vorname"));
                users.setEmail(rs.getString("email"));
                users.setTelefon(rs.getString("telefon"));
                users.setStrasse(rs.getString("strasse"));
                users.setOrt(rs.getString("ort"));
                users.setPlz(rs.getString("plz"));
                users.setSex(rs.getString("sex"));
                users.setGeburstag(rs.getString("geburtstag"));
                users.setSpitzname(rs.getString("spitzname"));
            }

        } catch (SQLException e) {

            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
        }
        return users;
    }

    public boolean update(User users) {

        String query = "UPDATE public.users\n" +
                "\tSET name=?, vorname=?, email=?, telefon=?, strasse=?, ort=?, plz=?, sex=?, geburtstag=?, spitzname=?\n" +
                "\tWHERE id=?";

        try {
            connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getVorname());
            preparedStatement.setString(3, users.getEmail());
            preparedStatement.setString(4, users.getTelefon());
            preparedStatement.setString(5, users.getStrasse());
            preparedStatement.setString(6, users.getOrt());
            preparedStatement.setString(7, users.getPlz());
            preparedStatement.setString(8, users.getSex());
            preparedStatement.setString(9, users.getGeburtstag());
            preparedStatement.setString(10, users.getSpitzname());
            preparedStatement.setInt(11, users.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    //"SELECT name, vorname FROM users WHERE MATCH (name, vorname) AGAINST ('test tollek' IN BOOLEAN MODE)";  //themanager
    //String query =  "SELECT to_tsvector('as')";

    public boolean search(String name){

        String query = "SELECT name, vorname, email, telefon, strasse, ort, plz, sex, geburtstag, spitzname FROM users " +
                "WHERE to_tsvector(name) @@ to_tsquery('?')";

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user,pass);
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String userName = rs.getString(name);
                System.out.println("FOUND: " + userName + "'");
            }

            connection.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


}
