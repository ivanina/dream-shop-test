package controller;

import dao.CustomerManager;
import dao.ProductManager;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppController {
    @Autowired
    private ProductManager productManager ;

    //TODO: general controllers
    @RequestMapping("/product/list")
    public List<Product> getList(){
        return productManager.getAll();
    }
}
