package entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "dream_shop_customers")
public class Customer {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name="password")
    private String passwordHsh;

    @Column(name="credit", nullable = false)
    private BigDecimal credit = new BigDecimal(0);

    @JsonIgnore
    @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL)
    private Set<Order> orders;

    public Customer(){}
    public Customer(String name, String email){
        this.name = name;
        this.email = email;
    }
    public Customer(String name, String email, BigDecimal credit){
        this(name,email);
        this.credit = credit;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHsh() {
        return passwordHsh;
    }

    public void setPasswordHsh(String passwordHsh) {
        this.passwordHsh = passwordHsh;
    }

    public BigDecimal getCredit() {
        if(credit==null) credit = BigDecimal.valueOf(0.0);
        return credit;
    }
    public void setCredit(BigDecimal credit) {
        if(credit==null) credit = BigDecimal.valueOf(0.0);
        this.credit = credit;
    }
    public void setCredit(Double credit) {
        this.credit = BigDecimal.valueOf( credit);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString(){
        return name+" ("+email+") [ID:"+id+"]";
    }
}
