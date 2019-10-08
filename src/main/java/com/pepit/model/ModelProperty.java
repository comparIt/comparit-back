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
@ToString
@Entity
@Table(name = "ModelProperty")
public class ModelProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "realName")
    String realName;

    @Column(name = "realTableName")
    String realTableName;

    @Column(name = "ModelPropertyType")
    String modelPropertyType;

    @Column(name = "isFiltrable")
    boolean isFiltrable;

    @Column(name = "isFiltrableAdvanced")
    boolean isFiltrableAdvanced;

    @Column(name = "isMandatory")
    boolean isMandatory;

    @ManyToOne
    Model model;

    @CreatedDate
    @Column(name = "createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedat")
    LocalDateTime updatedAt;

}
