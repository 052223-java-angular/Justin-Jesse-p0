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


    /*public void testFindByName()
    {
        String role = "USER";
        Role mockRole = new Role("id", role);
        when(roleService.findByName(role)).thenReturn(Optional.of(new Role()));

// Invoke the code under test
        Optional<Role> result = Optional.ofNullable(roleService.findByName(role));

// Verify the result using assertions
        assertTrue(result.isPresent());
        assertEquals(mockRole, result.get());
    }*/
}