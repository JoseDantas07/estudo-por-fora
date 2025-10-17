package estudo.picpay.Url;

import estudo.picpay.dto.request.AuthorizeRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Authorize", url = "https://util.devi.tools/api/v2/authorize")
public interface AuthorizePayment {

    @GetMapping
    AuthorizeRequestDto authorizeRequestDtos();
}
