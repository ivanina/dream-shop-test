package controller;

import base.RespMsg;
import dao.AddressManager;
import dao.CustomerManager;
import dao.ProductManager;
import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerManager customerManager;
    @Autowired
    private ProductManager productManager;
    @Autowired
    private AddressManager addressManager;


    @RequestMapping("/customer/create")
    public RespMsg createCustomer(
            @RequestParam(value = "name",required = true) String name,
            @RequestParam(value = "email",required = true) String email,
            @RequestParam(value = "credit",required = false) Integer credit
    ) {
        RespMsg msg = new RespMsg("success");
        Customer customer = new Customer(name,email,new BigDecimal(credit!=null?credit:10));
        try {
            customerManager.add(customer);
        }catch (Exception e){
            msg.setMsg("ERROR: "+ e.getMessage());
        }
        return msg;
    }


    @RequestMapping("/customer/set-credit")
    public RespMsg setCredit(
            @RequestParam(value = "credit",required = true) Integer credit,
            @RequestParam(value = "email",required = false) String email,
            @RequestParam(value = "id",required = false) Long id
    ){
        RespMsg msg = new RespMsg("success");
        Customer customer = getCustomer(id,email);
        if(customer == null){
            return new RespMsg("customer not found");
        }
        customer.setCredit( new BigDecimal(credit) );
        customerManager.update(customer);
        return msg;
    }

    @RequestMapping("/customer/list")
    public List<Customer> getList(){
        return customerManager.getAll();
    }

    @RequestMapping("/customer/view")
    public Customer getCustomer(
            @RequestParam(value = "email",required = false) String email,
            @RequestParam(value = "id",required = false) Long id
    ){
        return getCustomer(id, email);
    }
    private Customer getCustomer(Long id, String email){
        Customer customer = null;
        if(id != null){
            customer = customerManager.getById(id);
        }else if(email != null){
            customer = customerManager.getByEmail(email);
        }
        return customer;
    }
}
