package estudo.ViaCep.controller;

import estudo.ViaCep.Dto.request.AddressRequestDto;
import estudo.ViaCep.Dto.request.UpdateAddressRequestDto;
import estudo.ViaCep.Dto.response.AddressResponseDto;
import estudo.ViaCep.entity.AddressEntity;
import estudo.ViaCep.service.impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressServiceImpl addressService;

    @PostMapping("/{userId}")
    ResponseEntity<AddressRequestDto> createAddress(@PathVariable String userId, @RequestBody AddressRequestDto addressRequestDto){
        var address = addressService.createAddress(userId,addressRequestDto);
        return ResponseEntity.created(URI.create("address/" + address.toString())).build();
    }

    @GetMapping("/{addressId}")
    ResponseEntity<AddressResponseDto> getAddressById(@PathVariable String addressId){
        var address = addressService.getAddressById(addressId);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{addressId}")
    ResponseEntity<Void> updateAddressById(@PathVariable String addressId,@RequestBody UpdateAddressRequestDto updateAddressRequestDto){
        addressService.updateAddressById(addressId, updateAddressRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    ResponseEntity<Void> deleteAddressById(String addressId){
        addressService.deleteAddressById(addressId);
        return ResponseEntity.ok().build();
    }
}
