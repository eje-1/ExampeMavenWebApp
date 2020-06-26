package com.example.demo.repository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT name, vorname, email, telefon, strasse, ort, plz, sex, geburtstag, spitzname FROM users " +
            "WHERE to_tsvector(name) @@ to_tsquery('?')")
    public List<User> search(String keyword);
}