package estudo.ViaCep.controller;

import estudo.ViaCep.Dto.request.UserRequestDto;
import estudo.ViaCep.Dto.response.UserAddressResponseDto;
import estudo.ViaCep.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping
    ResponseEntity<Void> createUser(UserRequestDto userRequestDto){
        userService.createUser(userRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/address")
    ResponseEntity<UserAddressResponseDto> getUserWithAddress(UUID userId){
        return null;
    }
}
