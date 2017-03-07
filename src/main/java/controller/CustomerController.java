package controller;

import base.RespMsg;
import dao.CustomerManager;
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



    @RequestMapping("/customer/create")
    public RespMsg createCustomer(
            @RequestParam(value = "name",required = true) String name,
            @RequestParam(value = "email",required = true) String email,
            @RequestParam(value = "credit",required = false) Integer credit
    ) {
        customerManager.add(new Customer(name,email,new BigDecimal(credit!=null?credit:10)));
        return new RespMsg("success");
    }


    @RequestMapping("/customer/set-credit")
    public RespMsg setCredit(
            @RequestParam(value = "credit",required = true) Integer credit,
            @RequestParam(value = "email",required = false) String email,
            @RequestParam(value = "id",required = false) Long id
    ){
        Customer customer = getCustomer(id,email);
        if(customer == null){
            return new RespMsg("customer not found");
        }
        customer.setCredit( new BigDecimal(credit) );
        customerManager.update(customer);
        return new RespMsg("success");
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
