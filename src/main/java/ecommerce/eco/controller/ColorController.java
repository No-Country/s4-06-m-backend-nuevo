package ecommerce.eco.controller;

import ecommerce.eco.model.response.ColorResponse;
import ecommerce.eco.service.abstraction.ColorService;
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
@RequestMapping("/color")
@Api(value = "Color Controller", description = "color functionalities")
@CrossOrigin(origins = "*")
public class ColorController {
    private final ColorService colorService;

    @GetMapping("/all")
    @ApiOperation(value = "Find all colors ", notes = "Returns list colors" )
    public ResponseEntity<List<ColorResponse>> getAll(){
        List<ColorResponse> response = colorService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
