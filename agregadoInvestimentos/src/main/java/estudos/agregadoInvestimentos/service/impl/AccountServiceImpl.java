package estudos.agregadoInvestimentos.service.impl;

import estudos.agregadoInvestimentos.client.BrapiClient;
import estudos.agregadoInvestimentos.dto.request.AssociateAccountStocksDto;
import estudos.agregadoInvestimentos.dto.response.AccountStockResponseDto;
import estudos.agregadoInvestimentos.entity.AccountStockEntity;
import estudos.agregadoInvestimentos.entity.AccountStockIdEntity;
import estudos.agregadoInvestimentos.repository.AccountRepository;
import estudos.agregadoInvestimentos.repository.AccountStockRepository;
import estudos.agregadoInvestimentos.repository.StockRepository;
import estudos.agregadoInvestimentos.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    AccountStockRepository accountStockRepository;
    @Autowired
    BrapiClient brapiClient;

    @Override
    public void associateStock(String id, AssociateAccountStocksDto associateAccountStocksDto) {
        var account = accountRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock  = stockRepository.findById((associateAccountStocksDto.stockId())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var Id = new AccountStockIdEntity(account.getAccountId(),stock.getStockId());
        var entity = new AccountStockEntity(Id,account,stock, associateAccountStocksDto.quantity());

        accountStockRepository.save(entity);
    }

    @Override
    public List<AccountStockResponseDto> listStocks(String id) {
        var account = accountRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

       return account.getAccountStocks().stream().map(a -> new AccountStockResponseDto(a.getStockEntity().getStockId(),a.getQuantity(),getTotal(a.getStockEntity().getStockId(),a.getQuantity()))).toList();
    }

    private double getTotal(String stockId, Integer quantity) {
        var response = brapiClient.getQuote(TOKEN,stockId);
        var price = response.results().getFirst().regularMarketPrice();
        return price * quantity;
    }
}
