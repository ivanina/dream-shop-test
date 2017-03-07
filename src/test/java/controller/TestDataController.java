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
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TestDataController {
    @Autowired
    private CustomerManager customerManager;
    @Autowired
    private ProductManager productManager;
    @Autowired
    private AddressManager addressManager;
    @Autowired
    private OrderManger orderManger;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/test")
    public RespMsg test() {
        return new RespMsg("success");
    }

    @RequestMapping("/prepare-date")
    public RespMsg prepareData(){
        RespMsg msg = new RespMsg("success");
        if(counter.get() > 0){
            msg.setMsg("'prepare test date' can be run once");
            return msg;
        }
        Customer customer = new Customer("Smith","john@smith.com", new BigDecimal(75));
        try {
            customerManager.add(customer);
            msg.addMsg("Customer "+customer+" added successfully.");
        }catch (Exception e){
            msg.addMsg("ERROR: "+ e.getMessage());
        }
        List<Product> products = new ArrayList<>();
        products.add(new Product("sku-10-001","ProPlan Purina",new BigDecimal(25),3));
        products.add(new Product("sku-10-022","Arden Grange Sensitive",new BigDecimal(30),1));
        try {
            for(Product product : products){
                productManager.add(product);
                msg.addMsg("Product "+product.getSku()+" added successfully.");
            }
        }catch (Exception e){
            msg.addMsg("ERROR: "+ e.getMessage());
        }
        Address address = new Address("90039","Rowena Ave","2764-A","");
        try {
            addressManager.add(address);
            msg.addMsg("Address "+address+" added successfully.");
        }catch (Exception e){
            msg.addMsg("ERROR: "+ e.getMessage());
        }

        Order order = new Order(customer,address,new HashSet<Product>(products));
        try {
            orderManger.add(order);
            msg.addMsg("Order "+order.getId()+" added successfully.");
            msg.addData(order);
        }catch (Exception e){
            msg.addMsg("ERROR: "+ e.getMessage());
        }

        counter.incrementAndGet();
        return msg;
    }

    @RequestMapping("/create-test-order")
    public RespMsg createTestOrder(){
        Order order = null;
        List<Customer> customerList = customerManager.getAll();
        if(customerList == null || customerList.size() == 0){
            return new RespMsg(1,"List of customers is empty");
        }
        order = orderManger.getActiveByCustomer(customerList.get(0));
        if(order != null){
            return new RespMsg(1,"For customer with ID: '"+customerList.get(0).getId()+"' already exist open order (ID:'"+order.getId()+"')");
        }
        List<Address> addressList = addressManager.getAll();
        if(addressList == null || addressList.size() == 0){
            return new RespMsg(1,"List of addresses is empty");
        }
        List<Product> productList = productManager.getAll();
        if(productList == null || productList.size() == 0){
            return new RespMsg(1,"List of products is empty");
        }
        order = new Order(customerList.get(0),productList.get(0));
        order.setAddress(addressList.get(0));
        orderManger.add(order);
        return new RespMsg(0,"Order created. ID: "+order.getId());
    }
}
