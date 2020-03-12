package com.pepit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pepit.constants.Roles;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserDto {

    Integer id;
    String email;
    String password;
    String lastName;
    String firstName;
    List<Long> filters;
    Long companyId;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;
    Roles role;

}
