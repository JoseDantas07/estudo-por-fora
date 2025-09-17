package estudo.ViaCep.service;

import estudo.ViaCep.Dto.request.UserRequestDto;
import estudo.ViaCep.Dto.response.UserAddressResponseDto;

import java.util.UUID;

public interface UserService {

void createUser(UserRequestDto userRequestDto);

UserAddressResponseDto getUserWithAddress(UUID userId);
}
