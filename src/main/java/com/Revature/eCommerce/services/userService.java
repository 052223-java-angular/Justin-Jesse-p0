package com.Revature.eCommerce.services;

import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import com.Revature.eCommerce.dao.userDAO;
import com.Revature.eCommerce.models.User;

public class userService {

    private final userDAO userDao;

    public userService(userDAO userDao) {
        this.userDao = userDao;
    }

    public User register(String username, String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed);
        userDao.save(newUser);
        return newUser;
    }

    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userDao.findByUsername(username);

        if (userOpt.isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}