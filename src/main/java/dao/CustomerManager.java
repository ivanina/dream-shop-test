package dao;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CustomerManager {
    @PersistenceContext
    private EntityManager entityManager;


    public void add(Customer customer){
        entityManager.persist(customer);
    }

    public void delete(Customer customer){
        if(entityManager.contains(customer)){
            entityManager.remove(customer);
        }else {
            entityManager.remove(entityManager.merge(customer));
        }
    }

    @SuppressWarnings("unchecked")
    public List<Customer> getAll(){
        return entityManager.createQuery("from Customer").getResultList();
    }

    public Customer getByEmail(String email) {
        return (Customer) entityManager.createQuery(
                "from Customer where email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }

    public Customer getById(long id) {
        return entityManager.find(Customer.class, id);
    }

    public void update(Customer user) {
        entityManager.merge(user);
        return;
    }

    public boolean debiting(Customer customer, BigDecimal cost){
        if(customer == null) return false;
        if(customer.getCredit().subtract(cost).compareTo(BigDecimal.ZERO) >= 0 ){
            customer.setCredit(customer.getCredit().subtract(cost));
            update(customer);
            return true;
        }else {
            return false;
        }
    }
}
