package com.pepit.constants;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Roles {
    ROLE_CUSTOMER(3),
    ROLE_SUPPLIER(2),
    ROLE_ADMIN(1);

    private final int authority;

    Roles(int authority) {
        this.authority = authority;
    }
}
