package com.Antoine.Jerry.note_app.repository;

import com.Antoine.Jerry.note_app.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM Users WHERE email = :email", nativeQuery = true)
    User getUserByEmail(@Param("email") String email);
}
