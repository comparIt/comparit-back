package com.pepit.model;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    private String name;
/*
    public String getName() {
        return name;
    }*/
/*
    @Column(name = "fournid")
    private String fournId;
*/
    @Column(name = "category")
    private String category;

    @ElementCollection
    @Column(name = "data")
    private Map<String, Object> details = new LinkedHashMap<>();

    public Map<String, Object> getDetails() {
        return details;
    }

    @JsonAnySetter
    public void setDetail(String key, Object value) {
        details.put(key, value);
    }

/*    @CreatedDate
    @Column(name = "createdat")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedat")
    LocalDateTime updatedAt;*/
}
