package com.Revature.eCommerce.services;

import java.util.Optional;

import com.Revature.eCommerce.models.Role;
import org.mindrot.jbcrypt.BCrypt;
import com.Revature.eCommerce.dao.UserDAO;
import com.Revature.eCommerce.models.User;

/**
 * This class is a layer between the screen class and DAO class
 */
public class UserService {

    private final UserDAO userDao;
    private final RoleService roleServices;

    public UserService(UserDAO userDao, RoleService roleServices)
    {
        this.userDao = userDao;
        this.roleServices = roleServices;
    }

    /**
     * registers a new username and password
     * @param username-username
     * @param password-password
     * @return newUser
     */
    public User register(String username, String password) {
        Role foundFound = roleServices.findByName("USER");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed, foundFound.getId());
        userDao.save(newUser);
        return newUser;
    }

    /**
     * Checks to see if the user is in the database
     * @param username - username
     * @param password - password
     * @return - user or null
     */
    public User checkUser(String username, String password)
    {
     
       Optional<User> optionalUser = userDao.findByUsername(username);
        if (optionalUser.isPresent())
        {
            User user = optionalUser.get();
            if (BCrypt.checkpw(password, user.getPassword()))
            {
                return user;
            }
        }
        return null;
    }

    /**
     * Checks to see if the username is valid 8-20 char
     * @param username - username
     * @return - true false
     */
    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    /**
     * Checks to see if the username entered is unique in the DB
     * @param username - username
     * @return - true or false
     */
    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userDao.findByUsername(username);

        if (userOpt.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * Checks to see if the password is 8 characters and contains a number
     * @param password - user input
     * @return - true/false
     */
    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    /**
     * Checks to see if the user inputted the same password for registering
     * @param password - user input
     * @param confirmPassword - user input
     * @return true or false
     */
    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
