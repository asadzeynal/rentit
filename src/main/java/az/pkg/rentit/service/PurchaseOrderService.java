package az.pkg.rentit.service;

import az.pkg.rentit.entity.Plant;
import az.pkg.rentit.entity.PlantStatus;
import az.pkg.rentit.entity.PurchaseOrder;
import az.pkg.rentit.entity.PurchaseOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class PurchaseOrderService {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Autowired
    PlantService service;

    public void create(PurchaseOrder po){
        if (service.checkPlantAvailability(po.getPlant(), po.getStart().toString(), po.getEndr().toString())){
            po.setPurchaseOrderStatus(PurchaseOrderStatus.accepted);
            po.setPlant(service.find(po.getPlant().getId()));
            em.persist(po);
        } else {
            po.setPlant(service.find(po.getPlant().getId()));
            po.setPurchaseOrderStatus(PurchaseOrderStatus.rejected);
            em.persist(po);
        }
    }

    public PurchaseOrder find(long id){
        return em.find(PurchaseOrder.class, id);
    }

    public Collection<PurchaseOrder> findAll(){
        Query query = em.createQuery("SELECT p FROM PurchaseOrder p");
        return (Collection<PurchaseOrder>) query.getResultList();
    }

    public String checkPurchaseOrderStatus(long id){
        return em.find(PurchaseOrder.class, id).getPurchaseOrderStatus().toString();
    }

    public void cancelPurchaseOrder(long id){
        PurchaseOrder po = em.find(PurchaseOrder.class, id);
        po.setPurchaseOrderStatus(PurchaseOrderStatus.cancelledByCustomer);
        em.merge(po);
    }
}
