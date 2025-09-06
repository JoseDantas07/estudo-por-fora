package estudos.productRegistration.controller;

import estudos.productRegistration.entity.Products;
import estudos.productRegistration.service.impl.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductsController {
    @Autowired
    ProductsServiceImpl productsService;

    @PostMapping()
    public void createProducts(String name, BigDecimal price, Long amount){
        productsService.createProducts(name,price,amount);
    }

    @GetMapping()
    public List<Products> displayAll(){
       return productsService.displayAll();
    }

    @GetMapping("/{id}")
    public Products findById(@PathVariable Long id){
       return productsService.findById(id);
    }

    @PutMapping()
    public void updateProducts(Long id, String name,BigDecimal price,Long amount){
        productsService.updateProduct(id,name,price,amount);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Long id){
        productsService.deleteById(id);
    }
}
