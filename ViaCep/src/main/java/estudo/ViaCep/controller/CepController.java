package estudo.ViaCep.controller;

import estudo.ViaCep.Dto.request.CepRequestDto;
import estudo.ViaCep.Dto.response.CepFullResponseDto;
import estudo.ViaCep.entity.CepEntity;
import estudo.ViaCep.service.impl.CepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ceps")
public class CepController {

    @Autowired
    CepServiceImpl cepService;

    @PostMapping
    ResponseEntity<CepEntity> createCep(@RequestBody CepRequestDto cepRequestDto){
        cepService.createCep(cepRequestDto.cep());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cepId}/cep")
    ResponseEntity<CepFullResponseDto> getCepById(@PathVariable String cepId){
        var cep = cepService.getCepById(cepId);
        return ResponseEntity.ok(cep);
    }

    @GetMapping("/all")
    ResponseEntity<List<CepFullResponseDto>> getAllCep(){
        var cep = cepService.getAllCep();
        return ResponseEntity.ok(cep);
    }

    @DeleteMapping("/{cepId}/cep")
    ResponseEntity<Void> deleteCepById(@PathVariable String cepId){
        cepService.deleteCepById(cepId);
        return ResponseEntity.ok().build();
    }
}
