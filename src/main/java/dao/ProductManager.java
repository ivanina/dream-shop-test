package dao;

import entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductManager {
    @PersistenceContext
    private EntityManager entityManager;

    public void add(Product product){
        entityManager.persist(product);
    }

    public void delete(Product product){
        if(entityManager.contains(product)){
            entityManager.remove(product);
        }else {
            entityManager.remove(entityManager.merge(product));
        }
    }

    @SuppressWarnings("unchecked")
    public List<Product> getAll(){
        return entityManager.createQuery("from Product").getResultList();
    }

    public Product getBySku(String sku) {
        return (Product) entityManager.createQuery(
                "from Product where sku = :email")
                .setParameter("sku", sku)
                .getSingleResult();
    }

    public Product getById(long id) {
        return entityManager.find(Product.class, id);
    }

    public void update(Product product) {
        entityManager.merge(product);
        return;
    }
}
