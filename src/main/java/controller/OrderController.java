package controller;

import base.RespMsg;
import dao.AddressManager;
import dao.CustomerManager;
import dao.OrderManger;
import dao.ProductManager;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.OrderServices;

import java.util.List;

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
    @Autowired
    private OrderServices orderServices = new OrderServices(orderManger,customerManager,productManager);

    @RequestMapping("/order/new")
    public RespMsg newOrder(
            @RequestParam(value = "customer_id", required = true) Long customer_id
    ) {
        RespMsg msg = new RespMsg();
        orderServices.newOrder(
                customerManager.getById(customer_id),
                msg);
        return msg;
    }


    @RequestMapping("/order/add/product")
    public RespMsg addProduct(
            @RequestParam(value = "order_id", required = true) Long order_id,
            @RequestParam(value = "product_id", required = true) Long product_id
    ) {
        RespMsg msg = new RespMsg();
        orderServices.addProduct(
                orderManger.getById(order_id),
                productManager.getById(product_id),
                msg);
        return msg;
    }

    @RequestMapping("/order/add/address")
    public RespMsg addAddress(
            @RequestParam(value = "order_id", required = true) Long order_id,
            @RequestParam(value = "address_id", required = true) Long address_id
    ) {
        RespMsg msg = new RespMsg();
        orderServices.addAddress(
                orderManger.getById(order_id),
                addressManager.getById(address_id),
                msg);
        return msg;
    }

    @RequestMapping("/order/list")
    public List<Order> getList(
            @RequestParam(value = "customer_id", required = false) Long customer_id
    ) {
        if (customer_id != null) {
            return orderManger.getByCustomer(customer_id);
        } else {
            return orderManger.getAll();
        }
    }

    @RequestMapping("/order/checkout")
    public RespMsg orderCompete(
            @RequestParam(value = "order_id", required = true) Long order_id
    ) {
        RespMsg msg = new RespMsg();
        orderServices.checkout(orderManger.getById(order_id),msg);
        return msg;
    }

}
