package estudo.ViaCep.service.impl;

import estudo.ViaCep.Dto.request.UserRequestDto;
import estudo.ViaCep.Dto.response.UserResponseDto;
import estudo.ViaCep.entity.UserEntity;
import estudo.ViaCep.repository.UserRepository;
import estudo.ViaCep.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public UUID createUser(UserRequestDto userRequestDto) {

        var user = new UserEntity(null, userRequestDto.name(), userRequestDto.age(), new ArrayList<>());

        userRepository.save(user);

        return user.getUserId();
    }

    @Override
    public UserResponseDto getUserById(String userId) {

        var user = userRepository.findById(UUID.fromString((userId)));

        return user.map(x-> new UserResponseDto(x.getName(), x.getAddressEntities())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario nao encontrado"));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUserById(String userId, UserRequestDto userRequestDto) {
        userRepository.findById(UUID.fromString(userId)).ifPresentOrElse(x -> {
            if (userRequestDto.name() != null){
                x.setName(userRequestDto.name());
            }
            if (userRequestDto.age() != null){
                x.setAge(userRequestDto.age());
            }
            userRepository.save(x);
        }, () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Nao encontrado");});
    }

    @Override
    public void deleteUserById(String userid) {
        userRepository.deleteById(UUID.fromString(userid));
    }


}
