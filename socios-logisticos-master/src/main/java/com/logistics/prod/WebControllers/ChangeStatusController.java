package com.logistics.prod.WebControllers;

import com.logistics.prod.Controllers.PartnerController;
import com.logistics.prod.Interfaces.IPartnerController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
public class ChangeStatusController {

    @Inject
    IPartnerController partnerController = new PartnerController();

    @PostMapping("/changeStatusInTransit")
    public String changeStatusInTransit(@RequestParam(name = "idTransit") Long id) {
        //IPartnerController partnerController = new PartnerController();
        partnerController.changePackageStatusTransit(id);
        return "redirect:/listPartners";
    }

    @PostMapping("/changeStatusDelivered")
    public String changeStatusDelivered(@RequestParam(name = "idDelivered") Long id) {

        partnerController.changePackageStatusDelivered(id);
        return "redirect:/listPartners";
    }

    @PostMapping("/sendUpdates")
    public String sendUpdates() {

        partnerController.sendChangedStatus();
        return "redirect:/listPartners";
    }

}
