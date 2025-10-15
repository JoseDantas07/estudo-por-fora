package estudo.picpay.dto.request;

import estudo.picpay.entity.UserType;

public record CreateUserRequestDto(String name, String password, String cpfOrCnpj, String email, UserType userType){}
