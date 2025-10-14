package estudo.picpay.dto.request;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record SenderDto (UUID senderId, UUID receiverId, BigDecimal amount){
}
