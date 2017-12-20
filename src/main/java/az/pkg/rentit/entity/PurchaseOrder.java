package az.pkg.rentit.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String contact;
    private double cost;
    private Date endr;
    private Date start;
    private PurchaseOrderStatus purchaseOrderStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    private Plant plant;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PurchaseOrderStatus getPurchaseOrderStatus() {
        return purchaseOrderStatus;
    }

    public void setPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus) {
        this.purchaseOrderStatus = purchaseOrderStatus;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getEndr() {
        return endr;
    }

    public void setEndr(Date endr) {
        this.endr = endr;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
