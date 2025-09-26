package estudo.sistemaCadastro.controller;

import estudo.sistemaCadastro.entity.UserEntity;
import estudo.sistemaCadastro.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public void createUser(String name,String cpf,int age){
        userService.createUser(name, cpf, age);
    }

    @GetMapping("/users")
    public List<UserEntity> displayAll(){
        return userService.displayAll();
    }

    @GetMapping("/getId/{id}")
    public UserEntity displayUserById(@PathVariable Long id){
      return userService.displayUserById(id);
    }

    @GetMapping("/getCpf/{cpf}")
    public UserEntity displayUserByCpf(@PathVariable String cpf){
        return userService.displayUserByCpf(cpf);
    }

    @PutMapping("/ageId/{id}")
    public void updateAgeById(@PathVariable Long id, int age){
        userService.updateAgeById(id,age);
    }

    @PutMapping("/ageCpf/{cpf}")
    public void updateAgeByCpf(@PathVariable String cpf,int age){
        userService.updateAgeByCpf(cpf,age);
    }

    @PutMapping("/nameId/{id}")
    public void updateNameById(@PathVariable Long id,String name){
        userService.updateNameById(id,name);
    }

    @PutMapping("/nameCpf/{cpf}")
    public void updateNameByCpf(@PathVariable String cpf,String name){
        userService.updateNameByCpf(cpf,name);
    }

    @DeleteMapping("/delId/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @DeleteMapping("/delCpf/{cpf}")
    public void deleteUserByCpf(@PathVariable String cpf){
        userService.deleteUserByCpf(cpf);
    }



}
