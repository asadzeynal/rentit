package az.pkg.rentit.service;

import az.pkg.rentit.entity.Plant;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class PlantService {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public void create(Plant plant){
        em.persist(plant);
    }

    public Plant find(long id){
        return em.find(Plant.class, id);
    }

    public List<Plant> findAll(){
        Query query = em.createQuery("SELECT p FROM Plant p");
        return  (List<Plant>)query.getResultList();
    }

    public boolean checkPlantAvailability(Plant plant, @RequestParam String start, @RequestParam String end){
        boolean available = true;
        plant = find(plant.getId());
        for (int i = 0; i< plant.getPos().size(); i++ ){
            Date str = plant.getPos().get(i).getStart();
            Date en = plant.getPos().get(i).getEndr();
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            Date datest = null;
            Date datend = null;
            try {
                datest = formatter.parse(start);
                datend = formatter.parse(end);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(str + "   " + en);
            if (datest.after(str) && datest.before(en)){
                available = false;
            } else if (datend.after(str) && datend.before(en)){
                available = false;
            } else if (datest.before(str) && datend.after(en)){
                available = false;
            }
        }
        return available;
    }
}
