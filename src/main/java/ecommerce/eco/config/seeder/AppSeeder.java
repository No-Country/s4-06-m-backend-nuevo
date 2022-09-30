package ecommerce.eco.config.seeder;

import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.entity.Role;
import ecommerce.eco.model.entity.Size;
import ecommerce.eco.model.enums.ColorEnum;
import ecommerce.eco.model.enums.SizeEnum;
import ecommerce.eco.repository.CategoryRepository;
import ecommerce.eco.repository.ColorRepository;
import ecommerce.eco.repository.RoleRepository;
import ecommerce.eco.model.enums.RolesEnum;
import ecommerce.eco.repository.SizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Service
public class AppSeeder {

    private final String[] categoryName = {"HOMBRE", "MUJER", "NIÑOS", "OFERTAS"};
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        // create role
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            createRoles();
        }
        List<Color> colors = colorRepository.findAll();
        if (colors.isEmpty()) {
            createColors();
        }
        List<Size> sizeList=sizeRepository.findAll();
        if (colors.isEmpty()) {
            createSizes();
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
    private void createColors() {
        createColor(1L, ColorEnum.LIGHT_BLUE);
        createColor(2L, ColorEnum.RED);
        createColor(3L, ColorEnum.ROSE);
        createColor(4L, ColorEnum.YELLOW);
    }
    private void createSizes() {
        createSize(1L, SizeEnum.L);
        createSize(2L, SizeEnum.M);
        createSize(3L, SizeEnum.S);
        createSize(4L, SizeEnum.XS);
        createSize(5L, SizeEnum.NULL);
    }
    private void createSize(long id, SizeEnum sizeEnum) {
        Size size = new Size();
        size.setId(id);
        size.setName(sizeEnum.getName());
        size.setDescription(sizeEnum.getName());
        size.setTimestamp(new Timestamp(System.currentTimeMillis()));
        sizeRepository.save(size);
    }
    private void createColor(long id, ColorEnum colorEnum) {
        Color color = new Color();
        color.setId(id);
        color.setName(colorEnum.getName());
        color.setDescription(colorEnum.getName());
        color.setTimestamp(new Timestamp(System.currentTimeMillis()));
        colorRepository.save(color);
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
