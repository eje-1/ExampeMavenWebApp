package com.example.demo.controller;

import java.sql.*;

public class FirstController {

    Connection conn = null;

    public void testDB(){

        try {
            //db parameter
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/simpledb";
            String user = "ernest";
            String password = "admin";

            //Create connection to the db
            conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();

            //Simple exemple test on database with neu entry
            st.executeUpdate("INSERT INTO users (name, vorname, email, telefon, strasse, ort, plz, sex, geburtstag, spitzname)" +
                    "VALUES ('Test', 'Tollek', 'mail@mail.de', '0125545654546', 'mustermannstarsse', 'musterort', '12236', 'male', '12.12.2012', 'leek')");
            System.out.println("user add");
            conn.close();

        }catch (ClassNotFoundException | SQLException e){

            System.out.println(e.getMessage());

        }


    }

}
