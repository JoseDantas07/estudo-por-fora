package estudos.agregadoInvestimentos.service;

import estudos.agregadoInvestimentos.dto.request.CreateStockDto;

public interface StockService {
    void createStock(CreateStockDto createStockDto);
}
