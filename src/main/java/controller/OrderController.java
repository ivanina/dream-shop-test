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


    @RequestMapping("/order/add/product")
    public RespMsg addProduct(
            @RequestParam(value = "customer_id", required = true) Long customer_id,
            @RequestParam(value = "product_id", required = true) Long product_id
    ) {
        RespMsg msg = new RespMsg(0, "success");
        Customer customer = customerManager.getById(customer_id);
        if (customer == null) {
            msg.setMsg(1, "Customer not found");
            return msg;
        }
        Product product = productManager.getById(product_id);
        if (product == null) {
            msg.setMsg(1, "Product not found");
            return msg;
        }
        Order order = orderManger.getActiveByCustomer(customer);
        if (order == null) {
            order = new Order(customer, product);
            orderManger.update(order);
            msg.setMsg("Created Order");
        } else {
            order.addProduct(product);
            orderManger.add(order);
            msg.setMsg("Updated Order");
        }
        return msg;
    }

    @RequestMapping("/order/add/address")
    public RespMsg addAddress(
            @RequestParam(value = "customer_id", required = true) Long customer_id,
            @RequestParam(value = "address_id", required = true) Long address_id
    ) {
        RespMsg msg = new RespMsg(0, "success");
        Customer customer = customerManager.getById(customer_id);
        if (customer == null) {
            msg.setMsg(1, "Customer not found");
            return msg;
        }
        Address address = addressManager.getById(address_id);
        if (address == null) {
            msg.setMsg(1, "Product not found");
            return msg;
        }
        Order order = orderManger.getActiveByCustomer(customer);
        if (order == null) {
            order = new Order(customer);
            order.setAddress(address);
            orderManger.update(order);
            msg.setMsg("Created Order");
        } else {
            order.setAddress(address);
            orderManger.add(order);
            msg.setMsg("Updated Order");
        }
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

    @RequestMapping("/order/compete")
    public RespMsg orderCompete(
            @RequestParam(value = "order_id", required = true) Long order_id
    ) {
        Order order = orderManger.getById(order_id);
        if (order == null) {
            return new RespMsg(1, "The order with ID: '" + order_id + "' does not exist");
        }
        if(!order.getStatus().equals("new")){
            return new RespMsg(1, "The order with ID: '" + order_id + "' can not be processed. It's already closed. Status: "+order.getStatus());
        }
        Customer customer = order.getCustomer();
        if(!customerManager.debiting(customer,order.getTotal())){
            return new RespMsg(1, "Insufficient funds (Order Total: "+order.getTotal()+", the customer has only "+customer.getCredit()+")");
        }
        order.setStatus("shipping");
        orderManger.update(order);
        customer.getCredit().subtract(order.getTotal());
        customerManager.update(customer);
        return new RespMsg(0,"Order ID: '"+order.getId()+"' has to be shipped to "+order.getAddress());
    }

}
