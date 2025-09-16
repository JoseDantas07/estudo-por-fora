package estudos.agregadoInvestimentos.controller;

import estudos.agregadoInvestimentos.dto.request.CreateAccountDto;
import estudos.agregadoInvestimentos.dto.request.CreateUserDto;
import estudos.agregadoInvestimentos.dto.request.UpdateUserDto;
import estudos.agregadoInvestimentos.dto.response.AccountResponseDto;
import estudos.agregadoInvestimentos.entity.UserEntity;
import estudos.agregadoInvestimentos.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDto createUserDto){
        var user = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/users/" + user.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String id){
        var user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<UserEntity>> listUsers(){
       var users = userService.listAllUser();
       return ResponseEntity.ok(users);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(@PathVariable String id,@RequestBody UpdateUserDto updateUserDto){
        userService.updateUserById(id,updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/accounts")
    public ResponseEntity<Void> createAccount(@PathVariable String id,@RequestBody CreateAccountDto createAccountDto){
        userService.createAccount(id,createAccountDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<AccountResponseDto>> listAccounts(@PathVariable String id){
      var account = userService.listAccounts(id);
        return ResponseEntity.ok(account);
    }
}
