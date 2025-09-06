package estudos.productRegistration.service;

import estudos.productRegistration.entity.Products;

import java.math.BigDecimal;
import java.util.List;

public interface ProductsService {

    void createProducts(String name,BigDecimal price, Long amount);
    List<Products> displayAll();
    Products findById(Long id);
    void updateProduct(Long id, String name, BigDecimal price, Long amount);
    void deleteById(Long id);

}
