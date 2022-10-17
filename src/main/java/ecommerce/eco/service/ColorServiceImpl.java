package ecommerce.eco.service;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.mapper.ColorMapper;
import ecommerce.eco.model.response.ColorResponse;
import ecommerce.eco.repository.ColorRepository;
import ecommerce.eco.service.abstraction.ColorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ColorServiceImpl.class);
    @Override
    public Color findBy(String name) {
        Color color=colorRepository.findByName(name.toLowerCase()).get();
        if (color==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Color not found");
        return color;
    }

    @Override
    public Boolean checkListColor(List<String> colors) {
        for (String color : colors) {
            Color c= findBy(color.toLowerCase());
            if ( c== null) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public List<ColorResponse> findAll() {
        return colorRepository.findAll().stream()
                .map(colorMapper::entityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<Color> stringToEnty(List<String> request) {
        List<Color> colors = new ArrayList<>();
        request.stream()
                .map(p -> colors.add(findBy(p.toUpperCase())))
                .collect(Collectors.toList());
        return colors;
    }
}
