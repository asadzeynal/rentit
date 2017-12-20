package az.pkg.rentit.webservice;

import az.pkg.rentit.entity.Plant;
import az.pkg.rentit.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
@Service
public class PlantWS {
    @Autowired
    PlantService service;

    @WebMethod(operationName = "listAllPlants")
    public List<Plant> listAllPlants(){
        List<Plant> plants = service.findAll();
        for (Plant p: plants)
            p.setPos(null);
        return plants;
    }

    @WebMethod
    public Plant getPlantByID(long id){
        Plant p =service.find(id);
        p.setPos(null);
        return p;
    }

}
