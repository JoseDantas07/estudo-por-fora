package estudo.ViaCep.service;

import estudo.ViaCep.Dto.request.UserRequestDto;
import estudo.ViaCep.Dto.response.UserResponseDto;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

UUID createUser(UserRequestDto userRequestDto);

UserResponseDto getUserById(String userId);
}
