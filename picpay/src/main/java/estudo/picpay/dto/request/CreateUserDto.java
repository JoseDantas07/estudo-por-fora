package estudo.picpay.dto.request;

import estudo.picpay.entity.UserType;

public record CreateUserDto(String name, String password, String cpfOrCnpj, String email, UserType userType){}
