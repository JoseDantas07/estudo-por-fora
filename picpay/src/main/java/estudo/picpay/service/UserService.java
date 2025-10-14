package estudo.picpay.service;

import estudo.picpay.dto.request.CreateUserDto;
import estudo.picpay.dto.request.DepositDto;
import estudo.picpay.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UUID CreateUser(CreateUserDto CreateUserDto);

    void deposit (DepositDto depositDto);

    List<UserEntity> getAllUsers();


}
