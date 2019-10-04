package com.pepit.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne
    private Filter filter;

    @ManyToOne
    private User user;

    private List<Integer> listIdResult;

    //J'arrive pas a lire

    @CreatedDate
    @Column(name="createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updatedat")
    LocalDateTime updatedAt;


}
