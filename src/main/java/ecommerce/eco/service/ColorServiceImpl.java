package ecommerce.eco.service;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.mapper.ColorMapper;
import ecommerce.eco.model.response.ColorResponse;
import ecommerce.eco.repository.ColorRepository;
import ecommerce.eco.service.abstraction.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;
    @Override
    public Color findBy(String name) {
        return colorRepository.findByName(name);
    }

    @Override
    public boolean checkList(List<String> colors) {
        for (String c: colors) {
            if (colorRepository.findByName(c.toUpperCase()) == null) {
               return false;
            }
        }
        return true;
    }


    @Override
    public List<ColorResponse> findAll() {
        return colorRepository.findAll().stream()
                .map(colorMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
