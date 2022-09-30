package ecommerce.eco.service;


import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import ecommerce.eco.config.filters.ProductSpecifications;
import ecommerce.eco.model.entity.*;
import ecommerce.eco.model.enums.ColorEnum;
import ecommerce.eco.model.mapper.ProductMapper;
import ecommerce.eco.model.request.ProductFilterRequest;
import ecommerce.eco.model.request.ProductRequest;
import ecommerce.eco.model.response.ProductResponse;
import ecommerce.eco.repository.ColorRepository;
import ecommerce.eco.repository.ProductRepository;
import ecommerce.eco.repository.SizeRepository;
import ecommerce.eco.service.abstraction.ImageService;
import ecommerce.eco.service.abstraction.ProductService;
import ecommerce.eco.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
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
    private final SizeRepository sizeRepository;
    private final ProductSpecifications productSpecifications;

    @Override
    @Transactional
    public ProductResponse add(List<MultipartFile> postImage, ProductRequest request) {
        try {
            User user = userService.getInfoUser();
            if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not logged in");

            Product product = productMapper.dtoToProduct(request, user);
            /*Color*/
            if(colorRepository.findByName(request.getColor().toUpperCase())!=null){
                product.setColor(colorRepository.findByName(request.getColor()));
            }else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Color not valid");}
            /*Talle*/
            if(sizeRepository.findByName(request.getSize().toUpperCase())!=null){
                product.setSize(sizeRepository.findByName(request.getSize()));
                LOGGER.error("El talle es valido" + product.getSize().getName());
            }else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size not valid");}
            /*imagenes*/
            product.setCarrousel(imageService.imagesPost(postImage));

            /* product.getCarrousel().forEach(p->productRepository.save(product));*/
            for (int i = 0; i < product.getCarrousel().size() - 1; i++) {
                productRepository.save(product);
            }
            return productMapper.entityToDto(productRepository.save(product));
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image product already registered");
        }

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
        return productRepository.findAll().stream()
                .filter(p -> !p.isSoftDeleted())
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());
    }


    private Product getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        if (product.isSoftDeleted()) {
            throw new EntityNotFoundException("User not found or has been deleted");
        }
        return product;
    }
    @Override
    public List<ProductResponse> findByDetailsOrTitle( String title, String order) {
            List<Product> productList = productRepository.findAll( productSpecifications.getFiltered(new ProductFilterRequest(title, order)));
            return productList.stream()
                    .filter(p -> !p.isSoftDeleted())
                    .map(productMapper::entityToDto)
                    .collect(Collectors.toList());

        /*return productRepository.findByDetailsOrTitle(details,title).stream()
                .filter(p -> !p.isSoftDeleted())
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());*/
    }



}