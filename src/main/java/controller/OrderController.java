package controller;

import base.RespMsg;
import dao.AddressManager;
import dao.CustomerManager;
import dao.OrderManger;
import dao.ProductManager;
import entity.Address;
import entity.Customer;
import entity.Order;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private CustomerManager customerManager;
    @Autowired
    private ProductManager productManager;
    @Autowired
    private AddressManager addressManager;
    @Autowired
    private OrderManger orderManger;

    // order/add/product?user_id&product_id
    // order/set/address?user_id&address_id
    // order/view
    // order/list?user_id

    @RequestMapping("/order/add/product")
    public RespMsg addProduct(
            @RequestParam(value = "customer_id",required = true) Long customer_id,
            @RequestParam(value = "product_id",required = true) Long product_id
    ){
        RespMsg msg = new RespMsg(0,"success");
        Customer customer = customerManager.getById(customer_id);
        if(customer == null){
            msg.setMsg(1,"Customer not found");
            return msg;
        }
        Product product = productManager.getById(product_id);
        if(product == null){
            msg.setMsg(1,"Product not found");
            return msg;
        }
        Order order = orderManger.getActiveByCustomer(customer);
        if(order == null){
            order = new Order(customer,product);
            orderManger.update(order);
            msg.setMsg("Created Order");
        }else {
            order.addProduct(product);
            orderManger.add(order);
            msg.setMsg("Updated Order");
        }
        return msg;
    }

    @RequestMapping("/order/add/address")
    public RespMsg addAddress(
            @RequestParam(value = "customer_id",required = true) Long customer_id,
            @RequestParam(value = "address_id",required = true) Long address_id
    ){
        RespMsg msg = new RespMsg(0,"success");
        Customer customer = customerManager.getById(customer_id);
        if(customer == null){
            msg.setMsg(1,"Customer not found");
            return msg;
        }
        Address address = addressManager.getById(address_id);
        if(address == null){
            msg.setMsg(1,"Product not found");
            return msg;
        }
        Order order = orderManger.getActiveByCustomer(customer);
        if(order == null){
            order = new Order(customer);
            order.setAddress(address);
            orderManger.update(order);
            msg.setMsg("Created Order");
        }else {
            order.setAddress(address);
            orderManger.add(order);
            msg.setMsg("Updated Order");
        }
        return msg;
    }
}
