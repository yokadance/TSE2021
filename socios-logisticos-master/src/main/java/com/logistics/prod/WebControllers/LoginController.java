package com.logistics.prod.WebControllers;

import com.logistics.prod.Controllers.PartnerController;
import com.logistics.prod.Entities.Partner;
import com.logistics.prod.Interfaces.IPartnerController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {
    @GetMapping("/login")
           // public String viewLoginPage(){
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
       // IPartnerController partnerController = new PartnerController();
        //String email = (String) model.getAttribute("email");
        //String password = (String) model.getAttribute("passowrd");

        //if (email.equals(password)) {
         //   return "listPartners";
       //}
       // else return "login";
        return "login";
    }
}
