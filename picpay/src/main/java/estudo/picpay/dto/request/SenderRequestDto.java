package estudo.picpay.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record SenderRequestDto(UUID senderId, UUID receiverId, BigDecimal amount){
}
