package service;


import base.RespMsg;
import dao.CustomerManager;
import dao.OrderManger;
import dao.ProductManager;
import entity.Address;
import entity.Customer;
import entity.Order;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServices {
    private final CustomerManager customerManager;
    private final ProductManager productManager;
    private final OrderManger orderManger;

    @Autowired
    public OrderServices(OrderManger orderManger, CustomerManager customerManager, ProductManager productManager) {
        this.orderManger = orderManger;
        this.customerManager = customerManager;
        this.productManager = productManager;
    }

    public void newOrder(Customer customer, RespMsg msg){
        if(customer == null){
            msg.setMsg(1, "Customer not found");
            return ;
        }
        Order order = orderManger.getActiveByCustomer(customer);
        if(order != null){
            msg.setMsg(2, String.format("Open order exists (ID:?)",order.getId()));
            return ;
        }
        order = new Order(customer);
        orderManger.add(order);
        msg.setMsg(0,"Success");
    }

    public void checkout(Order order, RespMsg msg){
        if(!baseCheck(order,msg)){
            return;
        }
        Customer customer = order.getCustomer();
        if(!customerManager.debiting(customer,order.getTotal())){
            msg.setMsg(
                    1,
                    String.format("Insufficient funds (Order Total: ?, the customer has only ?)",order.getTotal(),customer.getCredit()));
        }
        order.setStatus("shipping");
        orderManger.update(order);
        customer.getCredit().subtract(order.getTotal());
        customerManager.update(customer);
        order.getProducts().forEach(product -> {
            product.countDecries();
            productManager.update(product);
        });
        msg.setMsg(0,"Success");
    }

    public void addAddress(Order order, Address address, RespMsg msg){
        if(!baseCheck(order,msg)){
            return;
        }
        if (address == null) {
            msg.setMsg(1, "Address not found");
            return ;
        }
        order.setAddress(address);
        orderManger.update(order);
        msg.setMsg(0,"Success");
    }

    public void addProduct(Order order, Product product, RespMsg msg){
        if(!baseCheck(order,msg)){
            return;
        }
        if (product == null) {
            msg.setMsg(1, "Product not found");
            return;
        }
        if (product.getCount() <= 0) {
            msg.setMsg(2, "Product not evadable");
            return;
        }
        order.addProduct(product);
        orderManger.add(order);
        msg.setMsg(0,"Updated Order");
    }

    public boolean baseCheck(Order order, RespMsg msg){
        if (order == null) {
            msg.setMsg(1, "Order not found");
            return false;
        }
        if(!order.getStatus().equals("new")){
            msg.setMsg(2, "Order already closed.");
            return false;
        }
        return true;
    }
}
