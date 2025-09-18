package estudo.ViaCep.controller;

import estudo.ViaCep.Dto.request.UserRequestDto;
import estudo.ViaCep.Dto.response.UserResponseDto;
import estudo.ViaCep.entity.UserEntity;
import estudo.ViaCep.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping
    ResponseEntity<UserEntity> createUser(@RequestBody UserRequestDto userRequestDto){
        var user = userService.createUser(userRequestDto);
        return ResponseEntity.created(URI.create("user/" + user.toString())).build();
    }

    @GetMapping("/{userId}")
    ResponseEntity <UserResponseDto> getUserById(@PathVariable String userId){
        var user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}
