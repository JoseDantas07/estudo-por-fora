package estudo.picpay.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record DepositRequestDto(UUID userId, BigDecimal amount) {
}
