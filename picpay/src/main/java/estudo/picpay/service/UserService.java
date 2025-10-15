package estudo.picpay.service;

import estudo.picpay.dto.request.CreateUserRequestDto;
import estudo.picpay.dto.request.DepositRequestDto;
import estudo.picpay.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UUID CreateUser(CreateUserRequestDto CreateUserDto);

    void deposit (DepositRequestDto depositDto);

    List<UserEntity> getAllUsers();


}
