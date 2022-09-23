package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Role;

public interface RoleService {
    Role findBy(String fullRoleName);
}
