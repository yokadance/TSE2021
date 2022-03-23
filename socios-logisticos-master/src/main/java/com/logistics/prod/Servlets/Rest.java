package com.logistics.prod.Servlets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@RestController
public class Rest{

@PostMapping("/restrictions")

public boolean restrictions (){
    return Math.random() < 0.5;
}
}

