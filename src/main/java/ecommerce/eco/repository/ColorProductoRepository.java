package ecommerce.eco.repository;

import ecommerce.eco.model.entity.ColorProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorProductoRepository<ColorProducto, ID> extends JpaRepository<ColorProducto, ID> {
}