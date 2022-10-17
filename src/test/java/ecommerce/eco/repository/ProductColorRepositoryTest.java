package ecommerce.eco.repository;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductColorRepositoryTest {
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void when_save_new_color_then_product_is_saved_correctly() {
        Product p = new Product();
       p.setTitle("hola mundo");
        var color = new Color();
        p.getColors().add(color);
        productRepository.save(p);

    }
}
