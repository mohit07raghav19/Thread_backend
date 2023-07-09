package com.example.thread.User.repo;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.thread.User.model.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, String> {

        @Override
        List<User> findAll(Sort sort);

        User findByEmail(String email);

        User findByUserName(String userName);

        @Query(value = "select * from user where "
                        + "( email like :email"
                        + " and securityq like :securityq )", nativeQuery = true)
        User findByUserDetails(@Param("email") String email, @Param("securityq") String securityq);

        @Query(value = "select * from user where user_name IN "
                        + "(select connection_to from connections where connector like :userName)", nativeQuery = true)
        List<User> findConnections(@Param("userName") String userName);

        @Query(value = "select * from user where user_name NOT IN "
                        + "(select connection_to from connections where connector like :userName)", nativeQuery = true)
        List<User> findNonConnections(@Param("userName") String userName);

}
