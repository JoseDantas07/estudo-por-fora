package estudos.agregadoInvestimentos.dto.response;

import estudos.agregadoInvestimentos.dto.request.StockDto;

import java.util.List;

public record BrapiResponseDto(List<StockDto> results) {
}
