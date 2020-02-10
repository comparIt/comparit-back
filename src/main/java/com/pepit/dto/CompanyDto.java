package com.pepit.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    int id;

    String providerName;

    String providerEmail;

    String site;

    String adress;

    String telephone;

    String presentation;

    List<Long> members;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
