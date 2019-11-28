package com.pepit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pepit.model.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductPagineDTO {

    List<ProductDto> productListOnThisPage;
    Integer nbPagesTotal;
    Integer pageActuelle;

}
