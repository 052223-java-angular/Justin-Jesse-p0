package com.Revature.eCommerce.services;

import com.Revature.eCommerce.dao.RoleDAO;
import com.Revature.eCommerce.models.Role;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class RoleServiceTest extends TestCase {
    @Mock
    private RoleService roleService;
    @Mock
    private RoleDAO roleDao;
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        roleService = new RoleService(roleDao);
    }


    public void testFindByName() {
        String role = "USER";
        Role invalidRole;
        Role validRole = new Role("id", role);
        when(roleDao.findByName(role)).thenReturn(Optional.of(validRole));
        Optional<Role> result = Optional.ofNullable(roleService.findByName(role));

        assertTrue(result.isPresent());
        assertEquals(role, validRole.getRoleName());

    }
}