package estudo.ViaCep.service.impl;


import estudo.ViaCep.Dto.response.CepFullResponseDto;
import estudo.ViaCep.entity.CepEntity;
import estudo.ViaCep.repository.CepRepository;
import estudo.ViaCep.service.CepService;
import estudo.ViaCep.url.ViaCepUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CepServiceImpl implements CepService {

    @Autowired
    CepRepository cepRepository;

    @Autowired
    ViaCepUrl viaCepUrl;

    @Autowired
    ChecksCepServiceImpl checkscepServiceImpl;

    @Override
    public void createCep(String cep) {

        var formattedCep = checkscepServiceImpl.checkCep(cep);

        var cepFound = viaCepUrl.getCepInformation(formattedCep);

        var cepFull = new CepEntity(formattedCep, cepFound.logradouro(), cepFound.bairro(), cepFound.localidade(), null);

        cepRepository.save(cepFull);
    }

    @Override
    public CepFullResponseDto getCepById(String cep) {
        var formattedCep = checkscepServiceImpl.checkCep(cep);

        var cepFound = cepRepository.findById(formattedCep);

        return cepFound.map(x -> new CepFullResponseDto(x.getCepId(), x.getLogradouro(), x.getBairro(),x.getLocalidade())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cep nao encontrado"));
    }

    @Override
    public List<CepFullResponseDto> getAllCep() {

        var cep = cepRepository.findAll();

        if (cep.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cep nao encontrado");
        }

        return cep.stream().map(x -> new CepFullResponseDto(x.getCepId(), x.getLogradouro(), x.getBairro(),x.getLocalidade())).toList();
    }

    @Override
    public void deleteCepById(String cep) {
        if (!cepRepository.existsById(cep)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cep nao encontrado");
        }

        cepRepository.deleteById(cep);
    }
}
