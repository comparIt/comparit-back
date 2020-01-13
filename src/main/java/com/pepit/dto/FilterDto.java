package com.pepit.dto;

import com.pepit.constants.TypeAlertEnum;
import com.pepit.model.Alert;
import com.pepit.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto {

    int id;

    UserDto user;

    TypeAlertEnum alertType;

    String category;

    HashMap<Integer, String> criterias;

    String orderBy;

    List<Alert> alerts;

    boolean isAlert;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
