package com.pepit.model;

import com.pepit.constants.TypeModelPropertyEnum;
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
@Builder
@Entity
@Table(name = "ModelProperty")
public class ModelProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "technicalName")
    String technicalName;

    @Column(name = "type")
    TypeModelPropertyEnum type;

    @Column(name = "filtrable")
    boolean filtrable;

    @Column(name = "filtrableAdvanced")
    boolean filtrableAdvanced;

    @Column(name = "mandatory")
    boolean mandatory;

    @ManyToOne
    Model model;

    @CreatedDate
    @Column(name = "createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedat")
    LocalDateTime updatedAt;

}
