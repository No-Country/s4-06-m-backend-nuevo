package ecommerce.eco.config.seeder;

import ecommerce.eco.model.entity.*;
import ecommerce.eco.model.enums.ColorEnum;
import ecommerce.eco.model.enums.SizeEnum;
import ecommerce.eco.repository.*;
import ecommerce.eco.model.enums.RolesEnum;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Service
public class AppSeeder {
    private static final String PASSWORD = "12345678";
    private final String[] categoryName = {"HOMBRE", "MUJER", "NIÑOS", "OFERTAS","NUEVAS OFERTAS","OFERTAS RELAMPAGO"};
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final UserRepository userRepository;
<<<<<<< HEAD
<<<<<<< HEAD
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;
=======
>>>>>>> category heroku
=======
    private final ImageRepository imageRepository;
>>>>>>> category heroku
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
        if (sizeList.isEmpty()) {
            createSizes();
        }
        List<Image> images=imageRepository.findAll();
        if(images.isEmpty()){
            createImage();
        }
        // create Category
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()){
            createCategories();
        }
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> category heroku
=======

>>>>>>> category heroku
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
           createUsers();
        }
<<<<<<< HEAD
    }
    private void createImage(User user){
        Image img= new Image();
            img.setImageUrl("https://group6nocountrygnavarro.s3.amazonaws.com/1664630878400_user.webp");
            img.setFileName("admin_img");
            Image create=imageRepository.save(img);
       user.setImage(create );
       userRepository.save(user);
=======
>>>>>>> category heroku
    }
    private void createImage(){
        Image img= Image.builder()
                .imageUrl("https://group6nocountrygnavarro.s3.amazonaws.com/1664630878400_user.webp")
                .fileName("admin_img")
                .build();
        imageRepository.save(img);
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
    private void createUsers(){
<<<<<<< HEAD
     /*  List<Image>  imgs=imageRepository.findAll();
       Image img=imageRepository.findById(1L).get();*/
        User user=new User();
        user.setEmail("admin@eco-sport.com");
        user.setFullName("Eco-Sport");
        user.setPassword(passwordEncoder.encode(PASSWORD));
        user.setSoftDeleted(Boolean.FALSE);
        user.setRole(roleRepository.findById(1L).get());
        user.setImage(null);
        createImage(user);


=======
       User user= User.builder()
               .role(roleRepository.findById(1L).get())
               .email("admin@eco-sport.com")
               .fullName("Eco-Sport")
               .password("12345678")
               .softDeleted(Boolean.FALSE)
               .image(imageRepository.getReferenceById(1L))
               .build();
       userRepository.save(user);
>>>>>>> category heroku
    }

}
