package estudo.picpay.controller;

import estudo.picpay.dto.request.CreateUserDto;
import estudo.picpay.dto.request.DepositDto;
import estudo.picpay.entity.UserEntity;
import estudo.picpay.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceimpl userServiceimpl;

    @PostMapping("/user")
    public ResponseEntity<UUID> createCommonUser(@RequestBody CreateUserDto createUserDto){
        var commonUser = userServiceimpl.CreateUser(createUserDto);
        return ResponseEntity.created(URI.create("user/" + commonUser.toString())).build();
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser(){
        return ResponseEntity.ok(userServiceimpl.getAllUsers());
    }

    @PutMapping("/user/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DepositDto depositDto){
        userServiceimpl.deposit(depositDto);
        return ResponseEntity.ok().build();
    }

}
