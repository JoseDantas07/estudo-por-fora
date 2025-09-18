package estudo.ViaCep.service.impl;

import estudo.ViaCep.url.ViaCepUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ChecksCepServiceImpl {

    @Autowired
    ViaCepUrl viaCepUrl;

    public String checkCep(String cep){
        cep = cep.replace("-", "" ).replace(" ","");

        try {
            Long.parseLong(cep);
        }catch (NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"O cep tem que ser numero");
        }

        if (cep.length() != 8){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cep deve ter 8 digitos");
        }

        var cepFound = viaCepUrl.getCepInformation(cep);

        if (cepFound.localidade() == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Cep invalido");
        }

        return cep;
    }
}
