package estudo.picpay.controller;

import estudo.picpay.dto.request.SenderRequestDto;
import estudo.picpay.dto.response.TransactionResponseDto;
import estudo.picpay.entity.TransactionEntity;
import estudo.picpay.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping
    public ResponseEntity<Void> sender(@RequestBody SenderRequestDto senderDto){
        transactionService.sender(senderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponseDto>> getALlTransaction(){
        return ResponseEntity.ok(transactionService.getAllTransaction());
    }
}
