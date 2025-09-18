package estudo.ViaCep.controller;

import estudo.ViaCep.Dto.request.AddressRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @PostMapping
    ResponseEntity<Void> createAddress(AddressRequestDto addressRequestDto){

        return ResponseEntity.ok().build();
    }

}
