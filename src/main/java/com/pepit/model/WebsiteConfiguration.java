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
@ToString(of = {"id","color1","color2","color3","analytic","listModel","createdAt","updatedAt"})
@Entity
@Table(name = "WebsiteConfiguration")
public class WebsiteConfiguration {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
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

    @ManyToOne
    private List<Model> listModel;

    @CreatedDate
    @Column(name="createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updatedat")
    LocalDateTime updatedAt;

}
