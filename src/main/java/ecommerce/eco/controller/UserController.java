package ecommerce.eco.controller;

import ecommerce.eco.model.request.UserUpdateRequest;
import ecommerce.eco.model.response.UserResponse;
import ecommerce.eco.service.abstraction.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Api(value = "User Controller", description = "User functionalities")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Find by id ", notes = "Returns one user" )
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        UserResponse response = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Find by id and delete ", notes = "Returns http 204" )
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Find all users ", notes = "Returns list users" )
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> response = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Find by id update user ", notes = "Returns user updated" )
    public ResponseEntity<UserResponse> update(
              @RequestPart ( value="image",required=false) MultipartFile image,
              @RequestPart(value="user", required=true) UserUpdateRequest request
    ){
        UserResponse response =userService.update(request,image);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
