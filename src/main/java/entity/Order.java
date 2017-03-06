package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dream_shop_orders")
public class Order {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="address_id")
    private Long addressId;

    @Column(name="total")
    private BigDecimal total;

    @Column(name="date", columnDefinition = "DATETIME")
    private Date date = new Date();


    /**
     * new | shining | complete
     */
    @Column(name="status")
    private String status = "new";


    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "shop_order_products",
            joinColumns = {
                    @JoinColumn(name = "order_id",nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id",nullable = false, updatable = false)
            }
    )
    private Set<Product> products;

    public Order() {
    }

    public Order(Customer customer) {
        this.customer = customer;
        this.customerId = customer.getId();
    }

    public Order(Customer customer, Address address, Set<Product> products) {
        this.customer = customer;
        this.customerId = customer.getId();
        this.address = address;
        this.addressId = address.getId();
        this.products = products;
        if(products != null){
            for (Product product : products){
                this.total = this.total.add(product.getPrice()) ;
            }
        }else{
            this.total = new BigDecimal(0.0);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProducts(Set<Product> products) {
        if(this.products == null) this.products = new HashSet<>();
        this.products.addAll(products);
        if(this.products != null){
            for (Product product : this.products){
                this.total = this.total.add(product.getPrice()) ;
            }
        }else{
            this.total = new BigDecimal(0.0);
        }
    }

    public void addProduct(Product product){
        if(this.products == null) {
            this.products = new HashSet<>();
            this.products.add(product);
        }else {
            Set<Product> products = new HashSet<>();
            products.add(product);
            addProducts( products );
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
