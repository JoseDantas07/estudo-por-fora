package estudo.ViaCep.service.impl;

import estudo.ViaCep.Dto.request.AddressRequestDto;
import estudo.ViaCep.Dto.request.UpdateAddressRequestDto;
import estudo.ViaCep.Dto.response.AddressResponseDto;
import estudo.ViaCep.entity.AddressEntity;
import estudo.ViaCep.repository.AddressRepository;
import estudo.ViaCep.repository.CepRepository;
import estudo.ViaCep.repository.UserRepository;
import estudo.ViaCep.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CepRepository cepRepository;

    @Autowired
    ChecksCepServiceImpl checkCepService;

    @Override
    public UUID createAddress(String userId,AddressRequestDto addressRequestDto) {
        var user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> {return new ResponseStatusException(HttpStatus.NOT_FOUND,"Id nao encontrado");
        });
        var formattedCep = checkCepService.checkCep(addressRequestDto.cepId());
        var cep = cepRepository.findById(formattedCep).orElseThrow(() -> {return new ResponseStatusException(HttpStatus.NOT_FOUND,"Cep Nao encontrado");});

        var address = addressRepository.save(new AddressEntity(null,user,cep, addressRequestDto.number(), addressRequestDto.type(), addressRequestDto.description()));

        return address.getAddressId();
    }

    @Override
    public AddressResponseDto getAddressById(String addressId) {
       var address = addressRepository.findById(UUID.fromString(addressId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id nao encontrado"));

        return new AddressResponseDto(address.getUserFk().getName(),address.getCepFk().getCepId(),address.getCepFk().getLogradouro(),address.getCepFk().getBairro(),address.getCepFk().getLocalidade(),address.getNumber(),address.getType(),address.getDescription());
    }

    @Override
    public void updateAddressById(String addressId, UpdateAddressRequestDto updateAddressRequestDto) {
        addressRepository.findById(UUID.fromString(addressId)).ifPresentOrElse(x -> {
            if (x.getNumber() != null){
                x.setNumber(updateAddressRequestDto.number());
            }
            if (x.getType() != null){
                x.setNumber(updateAddressRequestDto.number());
            }
            if (x.getDescription() != null){
                x.setDescription(x.getDescription());
            }

            addressRepository.save(x);
        },() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id nao encontrado");});
    }

    @Override
    public void deleteAddressById(String addressId) {
        addressRepository.deleteById(UUID.fromString(addressId));
    }


}
