package com.pepit.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Criteria")
public class Criteria {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "criteria")
    String criteria;

    @Column(name = "type")
    String type;

    @Column(name = "isFilter")
    boolean isFilter;

    @Column(name = "isAvancedFilter")
    boolean isAvancedFilter;

    @Column(name = "isHidden")
    boolean isHidden;

    @Column(name="createdat")
    LocalDateTime createdat;

    @LastModifiedDate
    @Column(name="updatedat")
    LocalDateTime updatedat;
}
