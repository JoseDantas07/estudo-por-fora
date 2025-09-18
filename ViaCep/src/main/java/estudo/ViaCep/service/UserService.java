package estudo.ViaCep.service;

import estudo.ViaCep.Dto.request.UserRequestDto;
import estudo.ViaCep.Dto.response.UserResponseDto;
import estudo.ViaCep.entity.UserEntity;

import java.util.List;

import java.util.UUID;

public interface UserService {

UUID createUser(UserRequestDto userRequestDto);

UserResponseDto getUserById(String userId);

List<UserEntity> getAllUsers();

void updateUserById(String userId , UserRequestDto userRequestDto);

void deleteUserById(String userid);
}
