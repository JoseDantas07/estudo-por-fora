package estudo.picpay.service.impl;

import estudo.picpay.dto.request.SenderDto;
import estudo.picpay.entity.TransactionEntity;
import estudo.picpay.repository.TransactionRepository;
import estudo.picpay.repository.UserRepository;
import estudo.picpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Checks checks;

    @Autowired
    TransactionRepository  transactionRepository;

    @Override
    public void sender(SenderDto senderDto) {

        var sender =  userRepository.findById(senderDto.senderId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id não encontrado /" +senderDto.senderId()));

        checks.verifyUserType(sender);
        checks.verifyMoney(sender,senderDto.amount());

        var receiver = userRepository.findById(senderDto.receiverId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id não encontrado /" +senderDto.receiverId()));

        sender.setBalance(sender.getBalance().subtract(senderDto.amount()));
        receiver.setBalance(receiver.getBalance().add(senderDto.amount()));

        userRepository.save(sender);
        userRepository.save(receiver);

        transactionRepository.save(new TransactionEntity(null, Instant.now(),senderDto.amount(),sender,receiver));
    }
}
