package uy.vacunas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckLogin {
    @PostMapping("/CheckLogin")
    public String checkLogin(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {

        if (email.equals(password)) {
            return "redirect:/listReservations";
        }

        return "redirect:/CheckLogin";
    }
}