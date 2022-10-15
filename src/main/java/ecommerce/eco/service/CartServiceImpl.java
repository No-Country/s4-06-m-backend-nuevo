package ecommerce.eco.service;

import ecommerce.eco.model.entity.Cart;
import ecommerce.eco.repository.CartRepository;
import ecommerce.eco.service.abstraction.CartService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
