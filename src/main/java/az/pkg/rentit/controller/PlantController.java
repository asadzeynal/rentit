package az.pkg.rentit.controller;

import az.pkg.rentit.entity.Plant;
import az.pkg.rentit.entity.PlantStatus;
import az.pkg.rentit.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;


@Controller
public class PlantController {

    @Autowired
    PlantService service;

    @RequestMapping(method = RequestMethod.POST, value = "/plant/create")
    public String createPlant(@RequestBody MultiValueMap<String,String> map, Model model) {
        Plant plant = new Plant();
        plant.setName(map.getFirst("name"));
        plant.setDescription(map.getFirst("desc"));
        plant.setPrice(Double.parseDouble(map.getFirst("pric")));
        plant.setCurrentStatus(PlantStatus.available);
        service.create(plant);
        model.addAttribute("message", "Plant " + plant.getName() + " successfully created");
        return "success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/plants")
    public String getPlants(Model model){
        ArrayList<Plant> plants = (ArrayList<Plant>) service.findAll();
        model.addAttribute("plants",plants);
        return "plants";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/plant")
    public String getPlant(@RequestParam String id, Model model){
        ArrayList<Plant> plant = new ArrayList<Plant>();
        plant.add(service.find(Long.parseLong(id)));
        model.addAttribute("plants", plant);
        return "plants";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/plant/availableAtDate")
    public String getPlant(@RequestParam String id, @RequestParam String start, @RequestParam String end, Model model){
        Plant plant = service.find(Long.parseLong(id));
        boolean available = service.checkPlantAvailability(plant, start, end);
        ArrayList<Plant> pl = new ArrayList<Plant>();
        pl.add(plant);
        model.addAttribute("plants", plant);
        model.addAttribute("name", plant.getName());
        model.addAttribute("id", plant.getId());
        model.addAttribute("availability", available? "available":"not available");
        return "plants";
    }
}
