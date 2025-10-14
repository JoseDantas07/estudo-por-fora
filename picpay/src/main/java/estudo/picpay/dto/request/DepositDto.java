package estudo.picpay.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record DepositDto(UUID userId, BigDecimal amount) {
}
