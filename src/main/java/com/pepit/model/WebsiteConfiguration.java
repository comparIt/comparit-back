package com.pepit.model;

import freemarker.template.utility.DateUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;

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
@Table(name = "WebsiteConfiguration")
public class WebsiteConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId")
    int adminId;

    @Column(name = "colorPrimary")
    String colorPrimary;

    @Column(name = "colorSecondary")
    String colorSecondary;

    @Column(name = "colorSecondary2")
    String colorSecondary2;

    @Nullable
    @Column(name="logo")
    String logo;
    
    @Column(name = "featAnalytic")
    boolean featAnalytic;

    @OneToMany
    private List<Model> models;

    @CreatedDate
    @Column(name = "createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedat")
    LocalDateTime updatedAt;

    public void update() {
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

}
