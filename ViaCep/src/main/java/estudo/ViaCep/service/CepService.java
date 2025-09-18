package estudo.ViaCep.service;

import estudo.ViaCep.Dto.response.CepFullResponseDto;

import java.util.List;

public interface CepService {
    void createCep(String cep);

    CepFullResponseDto getCepById(String cep);

    List<CepFullResponseDto> getAllCep();

    void deleteCepById(String cep);
}
