package az.pkg.rentit.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plant {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private String name;
    private double price;
    private PlantStatus currentStatus;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PurchaseOrder> pos;

    public PlantStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(PlantStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public List<PurchaseOrder> getPos() {
        return pos;
    }

    public void setPos(List<PurchaseOrder> pos) {
        this.pos = pos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
