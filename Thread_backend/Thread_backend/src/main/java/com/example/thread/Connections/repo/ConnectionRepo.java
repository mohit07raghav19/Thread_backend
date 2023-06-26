package com.example.thread.Connections.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.example.thread.Connections.model.Connections;

public interface ConnectionRepo extends JpaRepository<Connections, Integer> {

    @Query(value = "select * from connections", nativeQuery = true)
    List<Connections> findAll();

    @Modifying
    @Transactional
    @Query(value = "delete from connections c where c.connector like :connector and c.connection_to like :connection", nativeQuery = true)
    void deleteConnection(@Param("connector") String connector, @Param("connection") String connection);

    @Query(value = "select * from connections where connector like :connector and connection_to like :connection", nativeQuery = true)
    Connections isConnected(@Param("connector") String connector, @Param("connection") String connection);
    
}
