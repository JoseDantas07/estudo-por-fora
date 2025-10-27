package estudo.picpay.service.impl;

import estudo.picpay.Url.AuthorizePayment;
import estudo.picpay.dto.request.ReturnRequestDto;
import estudo.picpay.dto.request.SenderRequestDto;
import estudo.picpay.dto.response.TransactionResponseDto;
import estudo.picpay.entity.TransactionEntity;
import estudo.picpay.repository.TransactionRepository;
import estudo.picpay.repository.UserRepository;
import estudo.picpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Checks checks;

    @Autowired
    private TransactionRepository  transactionRepository;

    @Autowired
    private AuthorizePayment authorizePayment;

    @Override
    public void sender(SenderRequestDto senderDto) {

        var sender =  userRepository.findById(senderDto.senderId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id não encontrado /" +senderDto.senderId()));

        checks.verifyUserType(sender);
        checks.verifyMoney(sender,senderDto.amount());

        var receiver = userRepository.findById(senderDto.receiverId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id não encontrado /" +senderDto.receiverId()));

        var authorize = authorizePayment.authorizeRequestDtos();

        if ( authorize.status().isEmpty() || !authorize.status().equalsIgnoreCase("success")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"requisição não autorizada");
        }

        sender.setBalance(sender.getBalance().subtract(senderDto.amount()));
        receiver.setBalance(receiver.getBalance().add(senderDto.amount()));

        userRepository.save(sender);
        userRepository.save(receiver);

        transactionRepository.save(new TransactionEntity(null, Instant.now(),senderDto.amount(),false,sender,receiver));
    }

    @Override
    public List<TransactionResponseDto> getAllTransaction() {
        var transaction = transactionRepository.findAll();
        if (transaction.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"A lista esta vazia");
        }
        return transaction.stream().map(x -> new TransactionResponseDto(x.getTransactionId(),x.getSender(),x.getReceiver(),x.getTime(),x.getAmount(),x.isRefundOfPayment())).toList();
    }

    @Override
    public void RefundOfPayment(ReturnRequestDto returnRequestDto) {

        var transaction = transactionRepository.findById(returnRequestDto.transactionId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Transação não encontrada" + returnRequestDto.transactionId()));

        if (transaction.isRefundOfPayment()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Essa transação ja foi devolvida");
        }

        var senderId = userRepository.findById(returnRequestDto.senderId()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,"Id não encotnrado" + returnRequestDto.senderId()));

        var receiverId = userRepository.findById(returnRequestDto.receiverId()).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND,"Id não encotnrado" + returnRequestDto.receiverId()));


        BigDecimal money = transaction.getAmount();


        senderId.setBalance(senderId.getBalance().add(money));
        receiverId.setBalance(receiverId.getBalance().subtract(money));
        transaction.setRefundOfPayment(true);

        transactionRepository.save(transaction);
        userRepository.save(senderId);
        userRepository.save(receiverId);

    }
}
