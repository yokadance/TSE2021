package com.logistics.prod.WebControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class checkLogin {
    @PostMapping("/checkLogin")
    public String checkLogin(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {

        if (email.equals(password)) {
               return "redirect:/listPartners";
            }

        return "redirect:/login";
    }
}
