package com.pepit.dto;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

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
