package estudo.picpay.service;

import estudo.picpay.dto.request.SenderRequestDto;
import estudo.picpay.dto.response.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    void sender(SenderRequestDto senderDto);

    List<TransactionResponseDto> getAllTransaction();
}
