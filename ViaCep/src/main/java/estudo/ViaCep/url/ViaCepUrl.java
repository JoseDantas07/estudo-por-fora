package estudo.ViaCep.url;

import estudo.ViaCep.Dto.request.CepRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ViaCep", url = "viacep.com.br/ws")

public interface ViaCepUrl {
    @GetMapping(value = "/{cepId}/json/")
    CepRequestDto getZipCodeInformation(@PathVariable String cepId);
}
