package ecommerce.eco.service;


import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import ecommerce.eco.model.entity.Image;
import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.mapper.ProductMapper;
import ecommerce.eco.model.request.ProductRequest;
import ecommerce.eco.model.response.ProductResponse;
import ecommerce.eco.repository.ProductRepository;
import ecommerce.eco.service.abstraction.ImageService;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final UserService userService;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ImageService imageService;
    @Override
    @Transactional
    public ProductResponse add(List<MultipartFile> postImage, ProductRequest request) {
        try {
            User user = userService.getInfoUser();
            if (user==null)  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not logged in");

            Product product=productMapper.dtoToProduct(request, user);
            product.setCarrousel(imageService.imagesPost(postImage));
           /* product.getCarrousel().forEach(p->productRepository.save(product));*/
            for (int i = 0; i <product.getCarrousel().size()-1 ; i++) {
                productRepository.save(product);
            }
            return productMapper.entityToDto( productRepository.save(product));
            }catch(NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image product already registered");
            }

        }

    @Override
    public ProductResponse getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .filter(p-> !p.isSoftDeleted())
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());
    }
}