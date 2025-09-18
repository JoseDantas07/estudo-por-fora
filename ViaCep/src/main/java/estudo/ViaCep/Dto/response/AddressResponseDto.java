package estudo.ViaCep.Dto.response;

public record AddressResponseDto(String userId,String cepId,String street,String neighborhood,String locality,Long number, String type, String description) {
}
