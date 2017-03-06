package dao;

import entity.Customer;
import entity.Order;
import entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderManger {
    @PersistenceContext
    private EntityManager entityManager;

    public void add(Order order){
        entityManager.persist(order);
    }

    public void delete(Order order){
        if(entityManager.contains(order)){
            entityManager.remove(order);
        }else {
            entityManager.remove(entityManager.merge(order));
        }
    }

    @SuppressWarnings("unchecked")
    public List<Order> getAll(){
        return entityManager.createQuery("from Order").getResultList();
    }


    public Order getById(long id) {
        return entityManager.find(Order.class, id);
    }

    public void update(Order order) {
        entityManager.merge(order);
        return;
    }

    public Order getActiveByCustomer(Customer customer){
        if(customer == null) return null;
        return getActiveByCustomer(customer.getId());
    }
    public Order getActiveByCustomer(Long customerId){
        List<Order> orderList = entityManager.createQuery(
                "from Order where customerId = :customerId and status = :status order by date DESC")
                .setParameter("customerId",customerId)
                .setParameter("status","new")
                .getResultList();
        if(orderList == null || orderList.size() == 0) return null;
        else return orderList.get(0);
    }

    public List<Order> getByCustomer(Customer customer){
        if(customer == null) return null;
        return getByCustomer(customer.getId());
    }
    public List<Order> getByCustomer(Long customerId){
        if(customerId == null) return null;
        return entityManager.createQuery(
                "from Order where customerId = :customerId order by date DESC")
                .setParameter("customerId",customerId)
                .getResultList();
    }


}
