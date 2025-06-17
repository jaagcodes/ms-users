package com.plazoleta.users.domain.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleEnumTest {

    @Test
    void shouldReturnCorrectRoleValue() {
        assertEquals("OWNER", RoleEnum.OWNER.getValue());
        assertEquals("ADMIN", RoleEnum.ADMIN.getValue());
        assertEquals("CLIENT", RoleEnum.CLIENT.getValue());
        assertEquals("EMPLOYEE", RoleEnum.EMPLOYEE.getValue());
    }
}
