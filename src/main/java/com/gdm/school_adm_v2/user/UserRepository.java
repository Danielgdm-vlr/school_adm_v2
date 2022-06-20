package com.gdm.school_adm_v2.user;

import com.gdm.school_adm_v2.school.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select case when exists (" +
                "select u from user_table u " +
                "where u.username = ?1 and u.password = ?2) " +
            "then true " +
            "else false end from user_table")
    Boolean checkIfUserExists(String username, String password);

    Optional<User> getByUsername(String username);
    @Query("select s from user_table u inner join school s on s.user.id = ?1")
    Optional<School> getSchoolByUserId(Long id);
}
