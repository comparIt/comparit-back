package com.pepit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterCriteriaDto {
    int id;
    String alertType;
    String category;
    HashMap<Integer, String> criterias;
    Boolean isAlert;

}
