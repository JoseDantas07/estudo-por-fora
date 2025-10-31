package estudo.picpay.dto.request;

import estudo.picpay.entity.UserEntity;

import java.util.UUID;

public record ReturnRequestDto (UUID transactionId){
}
