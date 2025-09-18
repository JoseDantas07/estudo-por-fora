package estudo.ViaCep.controller;

import estudo.ViaCep.Dto.request.UserRequestDto;
import estudo.ViaCep.Dto.response.UserResponseDto;
import estudo.ViaCep.entity.UserEntity;
import estudo.ViaCep.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping
    ResponseEntity<UserRequestDto> createUser(@RequestBody UserRequestDto userRequestDto){
        var user = userService.createUser(userRequestDto);
        return ResponseEntity.created(URI.create("user/" + user.toString())).build();
    }

    @GetMapping("/{userId}")
    ResponseEntity <UserResponseDto> getUserById(@PathVariable String userId){
        var user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    ResponseEntity<List<UserEntity>> getAllUsers(){
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("{userId}")
    ResponseEntity<Void> updateUserById(@PathVariable String userId,@RequestBody UserRequestDto userRequestDto){
        userService.updateUserById(userId,userRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUserById(@PathVariable String userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }
}
