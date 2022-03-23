package com.logistics.prod.WebControllers;

import com.logistics.prod.Controllers.PartnerController;
import com.logistics.prod.Entities.Partner;
import com.logistics.prod.Interfaces.IPartnerController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import javax.inject.Inject;
import java.util.List;



@Controller
public class ListPartnersController {

    @Value("${partnerName}")
    String partnerName;

    @GetMapping("/listPartners")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        IPartnerController partnerController = new PartnerController();
        List<Partner> partnerList = partnerController.getPartners();
        model.addAttribute("partnerList", partnerList);
        model.addAttribute("partnerName", partnerName);
        return "listPartners";
    }

}
