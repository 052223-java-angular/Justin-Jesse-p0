package com.Revature.eCommerce.services;

import java.util.Optional;
import com.Revature.eCommerce.dao.RoleDAO;
import com.Revature.eCommerce.models.Role;


import com.Revature.eCommerce.utils.custom_exceptions.RoleNotFoundException;
import lombok.AllArgsConstructor;

    @AllArgsConstructor
    public class RoleService {
        private final RoleDAO roleDao;

        /**
         * Finds a role based on userName
         * @param name
         * @return
         */
        public Role findByName(String name) {
            Optional<Role> roleOpt = roleDao.findByName(name);

            if (roleOpt.isEmpty()) {
                throw new RoleNotFoundException();
            }

            return roleOpt.get();
        }
}