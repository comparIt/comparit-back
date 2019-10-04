package com.pepit.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "Model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "objectName")
    String objectName;

    @Column
    boolean isActivated;

    @ManyToMany
    private Collection<Criteria> criterias;

    @CreatedDate
    @Column(name="createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updatedat")
    LocalDateTime updatedAt;

}
