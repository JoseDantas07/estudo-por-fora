package estudo.picpay.controller;

import estudo.picpay.dto.request.SenderDto;
import estudo.picpay.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping
    public ResponseEntity<Void> sender(@RequestBody SenderDto senderDto){
        transactionService.sender(senderDto);
        return ResponseEntity.ok().build();
    }
}
