package com.pepit.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "providerName")
    String providerName;

    @Column(name = "providerEmail")
    String providerEmail;

    @Column(name = "site")
    String site;

    @Column(name = "adress")
    String adress;

    @Column(name = "telephone")
    String telephone;

    @Column(name = "presentation")
    String presentation;
    
    @OneToMany
    List<User> members;

    @CreatedDate
    @Column(name = "createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedat")
    LocalDateTime updatedAt;
}
