package estudo.ViaCep.Dto.response;

import estudo.ViaCep.entity.AddressEntity;

import java.util.List;

public record UserAddressResponseDto(String name , List<AddressEntity> address) { }
