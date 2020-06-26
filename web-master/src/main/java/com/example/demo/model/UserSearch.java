package com.example.demo.model;


import java.sql.*;

public class UserSearch {


    /**
     * "SELECT name FROM users" +
     *             "WHERE MACHT (name, vorname, email, telefon, strasse, ort, plz, sex, geburtstag, spitzname) " +
     *             "AGAINST ('a' IN NATURAL LANGUAGE MODE)";
     */

    //properties on database
    private static final String url = "jdbc:postgresql://localhost:5432/simpledb";
    private static final String user = "ernest";
    private static final String pass = "admin123";
    private String query = "SELECT * FROM users\"name\" ;";

    public void test(){

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("connect");
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()){
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               String vorname = resultSet.getString("vorname");
               System.out.printf("id = %s, name = %s, vorname = %s", id, name, vorname);
               System.out.println();
            }
            conn.close();

        }catch(ClassNotFoundException e) {
            e.printStackTrace();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }


}
