package ecommerce.eco.service;

import ecommerce.eco.exception.UserAlreadyExistException;
import ecommerce.eco.model.entity.Image;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.mapper.UserMapper;
import ecommerce.eco.model.request.UserUpdateRequest;
import ecommerce.eco.model.response.UserResponse;
import ecommerce.eco.repository.UserRepository;
import ecommerce.eco.service.abstraction.ImageService;
import ecommerce.eco.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final UserMapper userMapper;

    @Override
    public User getInfoUser() {
        Object userInstance = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (userInstance instanceof User) {
                String username = ((User) userInstance).getUsername();
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
        return userRepository.findByEmail(userInstance.toString());
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if (user.isSoftDeleted()) {
            throw new EntityNotFoundException("User not found or deleted");
        }
        return userMapper.dtoToEntityUser(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = getUser(id);
        user.setSoftDeleted(true);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .filter(p -> !p.isSoftDeleted())
                .map(userMapper::dtoToEntityUser)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponse update(UserUpdateRequest request, MultipartFile image) {
        Image img;
        User user = getInfoUser();
        if (!user.getEmail().equalsIgnoreCase(request.getEmail()) && userRepository.findByEmail(request.getEmail()) != null) {
            throw new UserAlreadyExistException("Email is already in use.");
        }
        img = imageService.update(user.getImage().getId(), imageService.imageUser(image));
        //convetimos archivo multiportFle en image

        User userSaved = userRepository.save(userMapper.updateToMaper(user, request, img));
        return userMapper.dtoToEntityUser(userSaved);
    }

    private User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if (user.isSoftDeleted()) {
            throw new EntityNotFoundException("User not found or has been deleted");
        }
        return user;
    }
}
