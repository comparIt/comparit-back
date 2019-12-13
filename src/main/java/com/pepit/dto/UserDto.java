package com.pepit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pepit.constants.Roles;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    Long id;
    String email;
    String password;
    String lastName;
    String firstName;
    List<Long> filters;
    Long companyId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Collection<Roles> roles;

}
