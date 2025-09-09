package estudo.sistemaCadastro.service;

import estudo.sistemaCadastro.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(String name,String cpf, int age);
    UserEntity displayUserByCpf(String cpf);
    UserEntity displayUserById(Long id);
    void deleteUserById(Long id);
    void deleteUserByCpf(String cpf);
    void updateAgeById(Long id,int age);
    void updateAgeByCpf(String cpf,int age);
    void updateNameById(Long id,String name);
    void updateNameByCpf(String cpf,String name);
    List<UserEntity> displayAll();
}
