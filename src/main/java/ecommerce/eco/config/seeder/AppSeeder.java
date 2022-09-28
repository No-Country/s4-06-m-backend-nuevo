package ecommerce.eco.config.seeder;

import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.entity.Role;
import ecommerce.eco.repository.CategoryRepository;
import ecommerce.eco.repository.RoleRepository;
import ecommerce.eco.model.enums.RolesEnum;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Service
public class AppSeeder {

    private final String[] categoryName = {"HOMBRE", "MUJER", "NIÑOS", "OFERTAS","NUEVAS OFERTAS","OFERTAS RELAMPAGO"};
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        // create role
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            createRoles();
        }
        // create Category
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty())
            createCategories();

    }

    private void createCategories() {
        for(int i = 0; i < categoryName.length; i++){
            Category category = new Category();
            category.setId((long) i+1);
            category.setDescription(categoryName[i]);
            category.setSoftDeleted(false);
            categoryRepository.save(category);
        }
    }

    private void createRoles() {
        createRole(1L, RolesEnum.ADMIN);
        createRole(2L, RolesEnum.USER);
    }

    private void createRole(long id, RolesEnum rolesEnum) {
        Role role = new Role();
        role.setId(id);
        role.setName(rolesEnum.getFullRoleName());
        role.setDescription(rolesEnum.getName());
        role.setTimestamp(new Timestamp(System.currentTimeMillis()));
        roleRepository.save(role);
    }
}
