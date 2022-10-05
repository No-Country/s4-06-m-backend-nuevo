package ecommerce.eco.service;


import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.entity.User;


import ecommerce.eco.config.filters.ProductSpecifications;

import ecommerce.eco.model.mapper.ProductMapper;
import ecommerce.eco.model.request.ProductFilterRequest;
import ecommerce.eco.model.request.ProductRequest;
import ecommerce.eco.model.response.ProductResponse;
import ecommerce.eco.repository.ColorRepository;
import ecommerce.eco.repository.ProductRepository;
import ecommerce.eco.repository.SizeRepository;
import ecommerce.eco.service.abstraction.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final UserService userService;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final ColorRepository colorRepository;
    private final ColorService colorService;
    private final SizeService sizeService;
    private final SizeRepository sizeRepository;
    private final ProductSpecifications productSpecifications;

    private final CategoryService categoryService;

    @Override
    @Transactional
    public ProductResponse add(List<MultipartFile> postImage, ProductRequest request) {
        try {
            User user = userService.getInfoUser();
            if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not logged in");
            /*Color*/
            if (!colorService.checkList(request.getColors())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Color not valid");

            if(!sizeService.checkList(request.getSizes()))  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size not valid");
            /*new product*/
            Product product = productMapper.dtoToProduct(request, user);
            product.setColors(colorService.stringToEnty(request.getColors()));

            for (int i = 0; i < product.getSizes().size(); i++) {
                LOGGER.warn(product.getSizes().get(i).getName());

               productRepository.save(product);
            }
            //salvando relacion color
            for (int i = 0; i <product.getColors().size() ; i++) {
                LOGGER.warn(product.getColors().get(i).getName());
               productRepository.save(product);
            }
            /*imagenes*/
            product.setCarrousel(imageService.imagesPost(postImage));
            /*agrego category*/
            Category category = categoryService.findById(request.getCategoryId());
            product.setCategory(category);

            //salvando relacion imagen
            for (int i = 0; i < product.getCarrousel().size() ; i++) {
                LOGGER.warn(product.getCarrousel().get(i).getFileName());
                productRepository.save(product);
            }
            //salvando relacion  Size

           // product.getColor().forEach(p->productRepository.save(product));
            return productMapper.entityToDto(productRepository.save(product));
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image product already registered");
        }

    }

    @Override
    public Product findById(Long idProduct) {
        return getProductForCategory(idProduct);
    }

    private Product getProductForCategory(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isEmpty() || product.get().isSoftDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found or has been deleted");
        }
        return product.get();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }


    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        if (product.isSoftDeleted()) {
            throw new EntityNotFoundException("User not found or deleted");
        }
        return productMapper.entityToDto(product);
    }

    @Override
    public void delete(Long id) {
        Product product = getProduct(id);
        product.setSoftDeleted(true);
        productRepository.save(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream().filter(p -> !p.isSoftDeleted()).map(productMapper::entityToDto).collect(Collectors.toList());
    }


    private Product getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        if (product.isSoftDeleted()) {
            throw new EntityNotFoundException("User not found or has been deleted");
        }
        return product;
    }

    @Override
    public List<ProductResponse> findByDetailsOrTitle(String title, String order) {
        List<Product> productList = productRepository.findAll(productSpecifications.getFiltered(new ProductFilterRequest(title, order)));
        return productList.stream().filter(p -> !p.isSoftDeleted()).map(productMapper::entityToDto).collect(Collectors.toList());
    }


}

