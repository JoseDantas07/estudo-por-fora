package estudo.picpay.service;

import estudo.picpay.dto.request.SenderDto;

public interface TransactionService {

    void sender(SenderDto senderDto);
}
