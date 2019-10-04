package com.pepit.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "providerName", "providerEmail", "site", "adress", "telephone", "presentation", "createdAt", "updatedAt"})
@Entity
@Table(name = "Compagny")
public class Compagny {

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

    @CreatedDate
    @Column(name = "createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedat")
    LocalDateTime updatedAt;
}
