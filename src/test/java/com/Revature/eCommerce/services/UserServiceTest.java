package com.Revature.eCommerce.services;

import com.Revature.eCommerce.dao.UserDAO;
import com.Revature.eCommerce.models.Role;
import com.Revature.eCommerce.models.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
    public class UserServiceTest extends TestCase {

    private UserService userService;
    @Mock
    private UserDAO userDao;
    @Mock
    private RoleService roleService;
    @Before
    public void setUp() throws Exception {
        // Initialize the Mockito framework
        MockitoAnnotations.openMocks(this);

        // Create a new instance of the UserService class with the mocked dependencies
        userService = new UserService(userDao, roleService);
    }

    public void testRegister() {
        // Define the test input values
        String username = "testUser";
        String password = "testPassword";
        Role role = new Role("cd7a196a-b4a1-4f2a-a6fc-902cc887ab71", "USER");
        User user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()), role.getId());

        // Mock the behavior of the roleService and userDao objects
        when(roleService.findByName("USER")).thenReturn(role);
        doNothing().when(userDao).save(any(User.class));

        // Call the register method of the userService object with the test input values
        User result = userService.register(username, password);

        // Verify that the userDao.save method was called once with any User object as
        // an argument
        verify(userDao, times(1)).save(any(User.class));

        // Verify that the result object has the expected username value
        assertEquals(username, result.getUsername());
    }

    public void testCheckUser() {
    }

    public void testIsValidUsername()
    {
        String validUsername = "validUser";
        String invalidUsername = "";

        assertTrue(userService.isValidUsername(validUsername));
        assertFalse(userService.isValidUsername(invalidUsername));
    }

    public void testIsUniqueUsername() {
    }

    public void testIsValidPassword() {
    }

    public void testIsSamePassword() {
    }
}