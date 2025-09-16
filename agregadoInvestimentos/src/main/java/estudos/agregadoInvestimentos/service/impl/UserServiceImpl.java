package estudos.agregadoInvestimentos.service.impl;

import estudos.agregadoInvestimentos.dto.request.CreateAccountDto;
import estudos.agregadoInvestimentos.dto.request.CreateUserDto;
import estudos.agregadoInvestimentos.dto.request.UpdateUserDto;
import estudos.agregadoInvestimentos.dto.response.AccountResponseDto;
import estudos.agregadoInvestimentos.entity.AccountEntity;
import estudos.agregadoInvestimentos.entity.BillingAddressEntity;
import estudos.agregadoInvestimentos.entity.UserEntity;
import estudos.agregadoInvestimentos.repository.AccountRepository;
import estudos.agregadoInvestimentos.repository.BillingAddressRepository;
import estudos.agregadoInvestimentos.repository.UserRepository;
import estudos.agregadoInvestimentos.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BillingAddressRepository billingAddressRepository;

    @Override
    public UUID createUser(CreateUserDto createUserDto) {

       var user = new UserEntity(null,createUserDto.name(), createUserDto.email(), createUserDto.password(), Instant.now(),null);

       var entity = userRepository.save(user);

       return entity.getUserId();
    }

    @Override
    public Optional<UserEntity> getUserById(String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    @Override
    public List<UserEntity> listAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void updateUserById(String id, UpdateUserDto updateUserDto) {
       userRepository.findById(UUID.fromString(id)).ifPresentOrElse(x -> {
          if (updateUserDto.name() != null){
              x.setUsername(updateUserDto.name());
          }
          if (updateUserDto.password() != null){
              x.setPassword(updateUserDto.password());
          }
          userRepository.save(x);
       }, () -> {throw new IllegalArgumentException("id nao encontrado ou algum campo não preenchido");});
    }

    @Override
    public void deleteById(String id) {
       if (userRepository.existsById(UUID.fromString(id))){
           userRepository.deleteById(UUID.fromString(id));
       }
    }
    @Transactional
    @Override
    public void createAccount(String id, CreateAccountDto createAccountDto) {
        var user = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var billingAddress = new BillingAddressEntity(null, null, createAccountDto.street(), createAccountDto.number());

        var account = new AccountEntity(null, createAccountDto.description(), user, billingAddress, new ArrayList<>());

        billingAddress.setAccountEntity(account);

        var accountCreate = accountRepository.save(account);


    }

    @Override
    public List<AccountResponseDto> listAccounts(String id) {
        var user = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return  user.getAccount().stream().map(a -> new AccountResponseDto(a.getAccountId().toString(), a.getDescription())).toList();
    }
}
