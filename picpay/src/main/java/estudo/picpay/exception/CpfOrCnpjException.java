package estudo.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfOrCnpjException extends RuntimeException {
    public CpfOrCnpjException(String message) {
        super(message);
    }
}
