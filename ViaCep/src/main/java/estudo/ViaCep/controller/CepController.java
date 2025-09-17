package estudo.ViaCep.controller;

import estudo.ViaCep.Dto.request.CepRequestDto;
import estudo.ViaCep.service.impl.CepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ceps")
public class CepController {

    @Autowired
    CepServiceImpl cepService;

    @PostMapping
    ResponseEntity<Void> createCep(CepRequestDto cepRequestDto){
        cepService.createCep(cepRequestDto);
        return ResponseEntity.ok().build();
    }
}
