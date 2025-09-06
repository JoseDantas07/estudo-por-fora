package estudos.productRegistration.service.impl;

import estudos.productRegistration.entity.Products;
import estudos.productRegistration.repository.ProductsRepository;
import estudos.productRegistration.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    ProductsRepository productsRepository;

    @Override
    public void createProducts(String name, BigDecimal price, Long amount) {
        Products products = new Products(name,price,amount);
        productsRepository.save(products);
    }

    @Override
    public List<Products> displayAll() {
       return productsRepository.findAll();
    }

    @Override
    public Products findById(Long id) {
       return productsRepository.findById(id).orElseThrow(() -> {throw new RuntimeException("Id nao encontrado");} );
    }

    @Override
    public void updateProduct(Long id, String name, BigDecimal price, Long amount) {
        productsRepository.findById(id).ifPresent(x -> {
            x.setName(name);
            x.setPrice(price);
            x.setAmount(amount);
            productsRepository.save(x);
        });
    }

    @Override
    public void deleteById(Long id) {productsRepository.deleteById(id);
    }


}
