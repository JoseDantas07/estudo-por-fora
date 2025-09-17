package estudo.ViaCep.service.impl;

import estudo.ViaCep.Dto.request.CepRequestDto;
import estudo.ViaCep.entity.CepEntity;
import estudo.ViaCep.repository.CepRepository;
import estudo.ViaCep.service.CepService;
import estudo.ViaCep.url.ViaCepUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepServiceImpl implements CepService {

    @Autowired
    CepRepository cepRepository;

    @Autowired
    ViaCepUrl viaCepUrl;

    @Override
    public void createCep(CepRequestDto cepRequestDto) {

        if (cepRepository.existsById(cepRequestDto.cep())) {
            throw new IllegalArgumentException("esse cep ja exite");
        }

        var cepFound = viaCepUrl.getZipCodeInformation(cepRequestDto.cep());

        var cep = new CepEntity(cepFound.cep(), cepFound.logradouro(), cepFound.bairro(), cepFound.localidade(), null);

        cepRepository.save(cep);
    }
}
