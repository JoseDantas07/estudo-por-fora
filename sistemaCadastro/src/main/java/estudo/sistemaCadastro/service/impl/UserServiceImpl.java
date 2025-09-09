package estudo.sistemaCadastro.service.impl;

import estudo.sistemaCadastro.entity.UserEntity;
import estudo.sistemaCadastro.repository.UserRepository;
import estudo.sistemaCadastro.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ValidateCpf validateCpf;

    @Override
    public void createUser(String name, String cpf, int age) {
        UserEntity userEntity = new UserEntity(name, cpf, age);
        if (validateCpf.isCpf(cpf)){
            if (userRepository.existsByCpf(cpf)){
                throw  new IllegalArgumentException("Esse usuario ja existi");
            }else {
                userRepository.save(userEntity);
                System.out.println("Usuario salvo");
            }
        }
    }

    @Override
    public UserEntity displayUserByCpf(String cpf) {
        return userRepository.findByCpf(cpf).orElseThrow(() -> new IllegalArgumentException("cpf nao encontrado"));
    }

    @Override
    public UserEntity displayUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->  new IllegalArgumentException("Id nao encontrado"));
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)){
            throw new IllegalArgumentException("Esse id nao existi");
        }else {
            userRepository.deleteById(id);
            System.out.println("usuario deletado");
        }
    }
    @Transactional
    @Override
    public void deleteUserByCpf(String cpf) {
        if (!userRepository.existsByCpf(cpf)){
            throw new IllegalArgumentException("esse cpf nao existi");
        }else {
            userRepository.deleteByCpf(cpf);
            System.out.println("usuario deletado");
        }
    }

    @Override
    public void updateAgeById(Long id,int age) {
        userRepository.findById(id).ifPresentOrElse(x -> {x.setAge(age); userRepository.save(x); System.out.println("Usuario atualizado");},() -> {throw new IllegalArgumentException("Id nao encontrado");});
    }

    @Override
    public void updateAgeByCpf(String cpf,int age) {
        userRepository.findByCpf(cpf).ifPresentOrElse(x -> {x.setAge(age); userRepository.save(x);
            System.out.println("Usuario atualizado"); }, () -> {throw new IllegalArgumentException("Cpf nao encontrado");});
    }

    @Override
    public void updateNameById(Long id, String name) {
        userRepository.findById(id).ifPresentOrElse(x -> {x.setName(name); userRepository.save(x);  System.out.println("Usuario atualizado");},() -> {throw new IllegalArgumentException("Id nao encontrado");});
    }

    @Override
    public void updateNameByCpf(String cpf, String name) {
        userRepository.findByCpf(cpf).ifPresentOrElse(x -> {x.setName(name); userRepository.save(x); System.out.println("Usuario atualizado");}, () -> {throw new IllegalArgumentException("Cpf nao encontrado");});
    }

    @Override
    public List<UserEntity> displayAll() {
        return userRepository.findAll();
    }


}
