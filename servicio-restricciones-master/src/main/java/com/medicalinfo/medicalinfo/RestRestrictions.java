package com.medicalinfo.medicalinfo;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestRestrictions {
    @PostMapping("/restrictions")

    public boolean restrictions (@RequestBody Restrictions restriction){
        //Restrictions restriction = jsonObject;


        if (restriction.getElementName().equals("DangerousAllergy")){
        return Math.random() < 0.1;
    }
        if ((restriction.getElementName().equals("edad")) && (restriction.getLogicOperator().getCode() == 0)){
            return Math.random() < 0.9;
        }
        return false;
    }


    @GetMapping("/prueba")
    public Restrictions restrictions (){
        Restrictions restrictions = new Restrictions();
        restrictions.setElementName("prueba");
        restrictions.setLogicOperator(EnumLogicOp.greaterThan);
        restrictions.setValue(1);
        return restrictions;
    }
}

