package estudo.ViaCep.Dto.response;

import estudo.ViaCep.entity.AddressEntity;

import java.util.List;

public record UserResponseDto(String name , List<AddressEntity> address) { }
