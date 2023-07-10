package com.example.thread.Connections.model;

import javax.persistence.*;

import com.example.thread.User.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "connections")
public class Connections {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "connection_to")
    private User connection;

    @ManyToOne
    @JoinColumn(name = "connector")
    private User connector;

    public Connections() {
        super();
    }

    public Connections(User connector, User connection) {
        super();
        this.connector = connector;
        this.connection = connection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getConnector() {
        return connector;
    }

    public void setConnector(User connector) {
        this.connector = connector;
    }

    public User getConnection() {
        return connection;
    }

    public void setConnection(User connection) {
        this.connection = connection;
    }

}
