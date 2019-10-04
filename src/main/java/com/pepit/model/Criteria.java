package com.pepit.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;

@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "users", "alertType", "criteria", "alert", "isEmail", "isAlert", "createdAt", "updatedAt"})
@Entity
@Table(name = "Criteria")
public class Criteria {
}
