package ecommerce.eco.service;

import ecommerce.eco.exception.InvalidPostImageException;
import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.mapper.ProductMapper;
import ecommerce.eco.model.request.ProductRequest;
import ecommerce.eco.model.response.ProductResponse;
import ecommerce.eco.repository.ProductRepository;
import ecommerce.eco.service.abstraction.ProductService;
import ecommerce.eco.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final UserService userService;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    @Override
    @Transactional
    public ProductResponse add(List<MultipartFile> postImage, ProductRequest request) {
        try {
            User user = userService.getInfoUser();

            if (postImage.isEmpty()) {
                LOGGER.error("La lista imagen esta vacia");
                }
            }catch( InvalidPostImageException e) {
            LOGGER.warn("Error de imagen, imagen requerida");
            }

           /* if (chechListFile(multipartFiles)){

                p.setPostImages(new ArrayList<>());

            }else {
                LOGGER.error("La lista no esta vacia");
                //generacion de List de imagenes
                p.setPostImages(imageService.imagesPost(multipartFiles));
                for (int i = 0; i <p.getPostImages().size()-1 ; i++) {
                    productRepository.save(p);
                }
            }
            return productMapper.responseToProperty( propertyRepository.save(p));
        }catch (InvalidPropertyException e){
            throw new InvalidPropertyException("Error creating a property: "+e.getMessage());
        }*/
            return null;

        }

    @Override
    public Product findById(Long idProduct) {
        return getProduct(idProduct);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    private Product getProduct(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isEmpty() || product.get().isSoftDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found or has been deleted");
        }
        return product.get();
    }
}

