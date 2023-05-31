package com.Revature.eCommerce.services;

import java.util.Optional;

import com.Revature.eCommerce.models.Role;
import org.mindrot.jbcrypt.BCrypt;
import com.Revature.eCommerce.dao.UserDAO;
import com.Revature.eCommerce.models.User;
//import com.Revature.eCommerce.services.RoleService;

public class UserService {

    private final UserDAO userDao;
    private final RoleService roleServices;

    public UserService(UserDAO userDao, RoleService roleServices)
    {
        this.userDao = userDao;
        this.roleServices = roleServices;
    }

    public User register(String username, String password) {
        Role foundFound = roleServices.findByName("USER");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed, foundFound.getId());
        userDao.save(newUser);
        return newUser;
    }

    //Checks to see if user is in datebase
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
      // return  userDao.checkUser(username, password);
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
