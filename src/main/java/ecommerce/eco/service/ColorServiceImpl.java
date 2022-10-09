package ecommerce.eco.service;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.mapper.ColorMapper;
import ecommerce.eco.model.response.ColorResponse;
import ecommerce.eco.repository.ColorRepository;
import ecommerce.eco.service.abstraction.ColorService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public Color findBy(String name) {
        return colorRepository.findByName(name);
    }

    public void checkList(List<String> colors) {
        for (String c : colors) {
            if (colorRepository.findByName(c.toLowerCase()) == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Color no valido: " + c);
            }
        }
    }

    @Override
    public List<ColorResponse> findAll() {
        return colorRepository.findAll().stream()
                .map(colorMapper::entityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<Color> stringToEnty(List<String> requests) {
        List<Color> colors = colorRepository.findAll();
        checkList(requests);
        List<Color> colorResponse = new ArrayList<>();
        for (Color c: colors){
            for(String s: requests){
                if(c.getName().equalsIgnoreCase(s)){
                    colorResponse.add(c);
                }
            }
        }
        return colorResponse;
    }
}
