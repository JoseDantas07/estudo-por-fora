package estudos.agregadoInvestimentos.controller;

import estudos.agregadoInvestimentos.dto.request.AssociateAccountStocksDto;
import estudos.agregadoInvestimentos.dto.response.AccountStockResponseDto;
import estudos.agregadoInvestimentos.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountServiceImpl  accountService;

    @PostMapping("/{id}/stock")
    public ResponseEntity<Void> associatesStocks(@PathVariable String id, @RequestBody AssociateAccountStocksDto associateAccountStocksDto){
        accountService.associateStock(id,associateAccountStocksDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<List<AccountStockResponseDto>> associatesStocks(@PathVariable String id){
        var stocks = accountService.listStocks(id);
        return ResponseEntity.ok(stocks);
    }
}
