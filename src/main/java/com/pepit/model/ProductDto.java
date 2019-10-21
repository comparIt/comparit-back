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
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    String _id;

    String Fournisseur;
    String typeProduct;

    @ElementCollection
    @Column(name = "properties")
    private Map<String, Object> properties = new LinkedHashMap<>();

}
