package estudos.agregadoInvestimentos.controller;

import estudos.agregadoInvestimentos.dto.request.CreateStockDto;
import estudos.agregadoInvestimentos.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    StockService stockService;

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createUserDto){
        stockService.createStock(createUserDto);
        return ResponseEntity.ok().build();
    }
}
