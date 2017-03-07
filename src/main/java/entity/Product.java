package entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dream_shop_products")
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="sku", nullable = false, unique = true)
    private String sku;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="price", nullable = false)
    private BigDecimal price;

    @Column(name="count", nullable = false)
    private Integer count = 0;

    public Product() {
    }

    public Product(String sku, String name, BigDecimal price, Integer count) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        if(price==null) price = BigDecimal.valueOf(0.0);
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(Double price) {
        this.price = BigDecimal.valueOf(price) ;
    }

    public void setPrice(Integer price) {
        this.price = BigDecimal.valueOf(price) ;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void countDecries(){
        countDecries(1);
    }
    public void countDecries(Integer count){
        if(count != null){
            this.count = this.count-count;
            if(this.count < 0) throw new IndexOutOfBoundsException();
        }
    }
}

