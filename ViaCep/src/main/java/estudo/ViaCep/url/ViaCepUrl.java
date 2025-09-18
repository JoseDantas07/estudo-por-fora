package estudo.ViaCep.url;

import estudo.ViaCep.Dto.request.CepFullRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ViaCep", url = "viacep.com.br/ws")

public interface ViaCepUrl {
    @GetMapping(value = "/{cepId}/json/")
    CepFullRequestDto getCepInformation(@PathVariable String cepId);
}
