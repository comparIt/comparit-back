package com.pepit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pepit.model.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductPagine {

    List<Product> productListOnThisPage;
    Integer nbPages;
    Integer pageActuel;

}
