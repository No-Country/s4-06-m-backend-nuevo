package ecommerce.eco.service;

import ecommerce.eco.model.entity.Role;
import ecommerce.eco.repository.RoleRepository;
import ecommerce.eco.service.abstraction.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role findBy(String fullRoleName) {
        return roleRepository.findByName(fullRoleName);
    }
}
