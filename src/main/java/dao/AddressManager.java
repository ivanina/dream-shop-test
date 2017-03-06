package dao;

import entity.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AddressManager {
    @PersistenceContext
    private EntityManager entityManager;

    public void add(Address address){
        entityManager.persist(address);
    }

    public void delete(Address address){
        if(entityManager.contains(address)){
            entityManager.remove(address);
        }else {
            entityManager.remove(entityManager.merge(address));
        }
    }

    @SuppressWarnings("unchecked")
    public List<Address> getAll(){
        return entityManager.createQuery("from Address").getResultList();
    }


    public Address getById(long id) {
        return entityManager.find(Address.class, id);
    }

    public void update(Address address) {
        entityManager.merge(address);
        return;
    }
}
