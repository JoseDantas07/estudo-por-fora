package estudo.picpay.dto.response;

import estudo.picpay.entity.UserEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionResponseDto(UUID transactionId, UserEntity senderId, UserEntity receiverId, Instant time, BigDecimal amount, boolean refundOfPayment) {
}
