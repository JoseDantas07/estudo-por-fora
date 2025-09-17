package estudo.ViaCep.service.impl;

import estudo.ViaCep.Dto.request.UserRequestDto;
import estudo.ViaCep.Dto.response.UserAddressResponseDto;
import estudo.ViaCep.entity.UserEntity;
import estudo.ViaCep.repository.UserRepository;
import estudo.ViaCep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(UserRequestDto userRequestDto) {

        var user = new UserEntity(UUID.randomUUID(), userRequestDto.name(), userRequestDto.age(), null);

        userRepository.save(user);
    }

    @Override
    public UserAddressResponseDto getUserWithAddress(UUID userId) {
        return null;
    }


}
