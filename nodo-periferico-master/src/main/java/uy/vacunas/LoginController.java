package uy.vacunas;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LoginController {
    @GetMapping("/login")
    // public String viewLoginPage(){
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        //IPartnerController partnerController = new PartnerController();
        //String email = (String) model.getAttribute("email");
        //String password = (String) model.getAttribute("passowrd");

        //if (email.equals(password)) {
        //   return "listPartners";
        //}
        // else return "login";
        return "login";
    }
}
