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
@ToString(of = {"id", "realName", "realTableName", "isActivated", "websiteConfiguration", "listModelProperty", "createdAt", "updatedAt"})
@Entity
@Table(name = "Model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "realName")
    String realName;

    @Column(name = "realTableName")
    String realTableName;

    @Column(name = "isActivated")
    boolean isActivated;

    @OneToMany
    WebsiteConfiguration websiteConfiguration;

    @ManyToOne
    List<ModelProperty> listModelProperty;

    @CreatedDate
    @Column(name = "createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedat")
    LocalDateTime updatedAt;

}
