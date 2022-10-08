package ecommerce.eco.controller;

import ecommerce.eco.model.response.SizeResponse;

import ecommerce.eco.service.abstraction.SizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/size")
@Api(value = "Size Controller", description = "zize functionalities")
@CrossOrigin(origins = "*")
public class SizeController {
    private final SizeService sizeService;

    @GetMapping("/all")
    @ApiOperation(value = "Find all sizes ", notes = "Returns list zizes" )
    public ResponseEntity<List<SizeResponse>> getAll(){
        List<SizeResponse> response = sizeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
