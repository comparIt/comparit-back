package com.pepit.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "WebsiteConfiguration")
@Getter
@Setter
@Builder
public class WebsiteConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "color1")
    String color1;

    @Column(name = "color2")
    String color2;

    @Column(name = "color3")
    String color3;

    @Column(name = "analytic")
    boolean analytic;

    @OneToMany
    private Model model;

    @CreatedDate
    @Column(name = "createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedat")
    LocalDateTime updatedAt;

}
