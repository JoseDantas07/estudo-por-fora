package estudos.agregadoInvestimentos.service.impl;

import estudos.agregadoInvestimentos.dto.request.CreateStockDto;
import estudos.agregadoInvestimentos.entity.StockEntity;
import estudos.agregadoInvestimentos.repository.StockRepository;
import estudos.agregadoInvestimentos.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockRepository stockRepository;

    @Override
    public void createStock(CreateStockDto createStockDto) {
        var stock = new StockEntity(createStockDto.stockId(), createStockDto.description());

        stockRepository.save(stock);
    }
}
