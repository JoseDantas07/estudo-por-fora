package estudo.ViaCep.service;

import estudo.ViaCep.Dto.request.AddressRequestDto;
import estudo.ViaCep.Dto.request.UpdateAddressRequestDto;
import estudo.ViaCep.Dto.response.AddressResponseDto;

import java.util.UUID;

public interface AddressService {

    UUID createAddress(String userId, AddressRequestDto addressRequestDto);

    AddressResponseDto getAddressById(String addressId);

    void updateAddressById(String addressId, UpdateAddressRequestDto addressRequestDto);

    void deleteAddressById(String addressId);
}
