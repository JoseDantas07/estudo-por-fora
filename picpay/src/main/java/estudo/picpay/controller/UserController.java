package estudo.picpay.controller;

import estudo.picpay.dto.request.CreateUserRequestDto;
import estudo.picpay.dto.request.DepositRequestDto;
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
    public ResponseEntity<UUID> createCommonUser(@RequestBody CreateUserRequestDto createUserDto){
        var commonUser = userServiceimpl.CreateUser(createUserDto);
        return ResponseEntity.created(URI.create("user/" + commonUser.toString())).build();
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser(){
        return ResponseEntity.ok(userServiceimpl.getAllUsers());
    }

    @PutMapping("/user/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DepositRequestDto depositDto){
        userServiceimpl.deposit(depositDto);
        return ResponseEntity.ok().build();
    }

}
