package ecommerce.eco.service;

import ecommerce.eco.model.entity.Size;
import ecommerce.eco.model.mapper.SizeMapper;
import ecommerce.eco.model.response.SizeResponse;
import ecommerce.eco.repository.SizeRepository;
import ecommerce.eco.service.abstraction.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void checkList(List<String> sizes) {
        for (String s : sizes) {
            if (sizeRepository.findByName(s.toUpperCase()) == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Talle no valido: " + s);
            }
        }
    }

    @Override
    public List<SizeResponse> findAll() {
        return sizeRepository.findAll().stream()
                .map(sizeMapper::entityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<Size> stringToEnty(List<String> requests) {
        List<Size> sizes = sizeRepository.findAll();
        checkList(requests);
        List<Size> sizesResponse = new ArrayList<>();
        for (Size size : sizes) {
            for (String s : requests) {
                if (size.getName().equalsIgnoreCase(s)) {
                    sizesResponse.add(size);
                }
            }
        }
        return sizesResponse;
    }
}
