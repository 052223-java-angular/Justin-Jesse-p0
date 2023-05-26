package com.Revature.eCommerce.services;

import java.util.Optional;
import com.Revature.eCommerce.dao.RoleDAO;
import com.Revature.eCommerce.models.Role;
import com.Revature.eCommerce.utils.custom_exceptions.CategoryNotFoundException;

import com.Revature.eCommerce.utils.custom_exceptions.RoleNotFoundException;
import lombok.AllArgsConstructor;

    @AllArgsConstructor
    public class RoleService {
        private final RoleDAO roleDao;

        public Role findByName(String name) {
            Optional<Role> roleOpt = roleDao.findByName(name);

            if (roleOpt.isEmpty()) {
                throw new RoleNotFoundException();
            }

            return roleOpt.get();
        }
}