package az.pkg.rentit.webservice;

import az.pkg.rentit.entity.PurchaseOrder;
import az.pkg.rentit.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
@Service
public class PurchaseOrderWS {
    @Autowired
    PurchaseOrderService service;

    @WebMethod
    public boolean createPurchaseOrder(PurchaseOrder order){
            service.create(order);
        return true;
    }

    @WebMethod
    public PurchaseOrder getPurchaseOrderByID(long id){
        PurchaseOrder po = service.find(id);
//        System.out.println(po.getContact() + "cfhj");
        po.getPlant().setPos(null);
        return po;
    }

    @WebMethod
    public boolean cancelPurchaseOrderById(long id){
        service.cancelPurchaseOrder(id);
        return true;
    }

    @WebMethod
    public List<PurchaseOrder> getAllPurchaseOrders(){
        return (List<PurchaseOrder>) service.findAll();
    }

}
