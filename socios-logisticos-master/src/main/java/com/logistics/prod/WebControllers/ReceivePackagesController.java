package com.logistics.prod.WebControllers;

import com.logistics.prod.Controllers.PartnerController;
import com.logistics.prod.Entities.Partner;
import com.logistics.prod.Interfaces.IPartnerController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReceivePackagesController {
    @Value("${partner}")
    private Integer vID;


    @PostMapping("/receivePackages")
    public String checkLogin() {
        Long v = Long.valueOf(vID);
        IPartnerController partnerController = new PartnerController();
        partnerController.receivePending(v);
        return "redirect:/listPartners";
    }
}
