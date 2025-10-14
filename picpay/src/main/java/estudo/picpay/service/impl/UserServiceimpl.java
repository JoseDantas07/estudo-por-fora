package estudo.picpay.service.impl;

import estudo.picpay.dto.request.CreateUserDto;
import estudo.picpay.dto.request.DepositDto;
import estudo.picpay.entity.UserEntity;
import estudo.picpay.repository.UserRepository;
import estudo.picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Checks checks;

    @Override
    public UUID CreateUser(CreateUserDto createClientDto) {

        var chekccpfOrCnpj= checks.chekccpfOrCnpj(createClientDto.cpfOrCnpj());

        var checkEmail = checks.checkEmail(createClientDto.email());

        var user = new UserEntity(null,createClientDto.name(), createClientDto.password(), createClientDto.cpfOrCnpj(), createClientDto.email(),createClientDto.userType(),new BigDecimal(0),new ArrayList<>(), new ArrayList<>());

        userRepository.save(user);

        return user.getUserId();
    }

    @Override
    public void deposit(DepositDto depositDto) {
        userRepository.findById(depositDto.userId()).ifPresentOrElse(x -> {
            x.setBalance(depositDto.amount());
            userRepository.save(x);
        }, () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id não encontrado");});
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
