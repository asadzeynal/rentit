package az.pkg.rentit.controller;

import az.pkg.rentit.entity.PurchaseOrder;
import az.pkg.rentit.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PurchaseOrderController {
    @Autowired
    PurchaseOrderService service;

    @RequestMapping(method = RequestMethod.GET, value = "/pos")
    public String getAllPOS(Model model){
        model.addAttribute("pos", service.findAll());
        return "pos";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/po")
    public String getPo(@RequestParam long id, Model model){
        PurchaseOrder po = service.find(id);
        model.addAttribute("pos", po);
        model.addAttribute("id", po.getId());
        model.addAttribute("availability", po.getPurchaseOrderStatus());
        return "pos";
    }
}
