package ecommerce.eco.service;

import ecommerce.eco.model.entity.Size;
import ecommerce.eco.model.mapper.SizeMapper;
import ecommerce.eco.model.response.SizeResponse;
import ecommerce.eco.repository.SizeRepository;
import ecommerce.eco.service.abstraction.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    private final SizeMapper sizeMapper;

    @Override
    public Size findBy(String name) {
        return sizeRepository.findByName(name);
    }

    @Override
    public boolean checkList(List<String> sizes) {
        for (String c: sizes) {
            if (sizeRepository.findByName(c.toUpperCase()) == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<SizeResponse> findAll() {
        return sizeRepository.findAll().stream()
                .map(sizeMapper::entityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<Size> stringToEnty(List<String> request) {
        List<Size> sizes = new ArrayList<>();
        request.stream()
                .map(p -> sizes.add(findBy(p.toUpperCase())))
                .collect(Collectors.toList());
        return sizes;
    }
}
