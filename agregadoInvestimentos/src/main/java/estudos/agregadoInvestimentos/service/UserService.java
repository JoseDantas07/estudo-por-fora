package estudos.agregadoInvestimentos.service;

import estudos.agregadoInvestimentos.dto.request.CreateAccountDto;
import estudos.agregadoInvestimentos.dto.request.CreateUserDto;
import estudos.agregadoInvestimentos.dto.request.UpdateUserDto;
import estudos.agregadoInvestimentos.dto.response.AccountResponseDto;
import estudos.agregadoInvestimentos.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UUID createUser(CreateUserDto createUserDto);

    Optional<UserEntity> getUserById(String id);

    List<UserEntity> listAllUser();

    void updateUserById(String id, UpdateUserDto updateUserDto);

    void deleteById(String id);

    void createAccount(String id, CreateAccountDto createAccountDto);

    List<AccountResponseDto> listAccounts(String id);
}
