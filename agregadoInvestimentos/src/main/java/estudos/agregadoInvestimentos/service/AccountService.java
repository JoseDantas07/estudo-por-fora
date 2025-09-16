package estudos.agregadoInvestimentos.service;

import estudos.agregadoInvestimentos.dto.request.AssociateAccountStocksDto;
import estudos.agregadoInvestimentos.dto.response.AccountStockResponseDto;

import java.util.List;

public interface AccountService {
    void associateStock(String id, AssociateAccountStocksDto associateAccountStocksDto);

    List<AccountStockResponseDto> listStocks(String id);
}
