package com.pepit.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "Filter")
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "idAlert")
    int idAlert;

    @ManyToMany
    private Collection<User> users;

    @Column(name = "typeAlert")
    String typeAlert;

    @ManyToMany
    private Collection<Criteria> criterias;


    @CreatedDate
    @Column(name="createdat")
    LocalDateTime createdat;

    @LastModifiedDate
    @Column(name="updatedat")
    LocalDateTime updatedat;
}
