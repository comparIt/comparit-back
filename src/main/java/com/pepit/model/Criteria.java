package com.pepit.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "criteria", "type", "isFilter", "isAvancedFilter", "isHidden", "createdAt", "updatedAt"})
@Entity
@Table(name = "Criteria")
public class Criteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedat")
    LocalDateTime updatedAt;
}
