package com.logistics.prod.Servlets;


import com.logistics.prod.Entities.Partner;
import com.logistics.prod.Interfaces.IPartnerController;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.lang.reflect.Parameter;
import java.util.List;

@Named("index")
@ManagedBean
public class IndexBean {

    @EJB
    IPartnerController partnerController;

    private List<Partner> lista;

    public IndexBean(){
        //super();
    }

    @PostConstruct
    public void init(){
        lista = partnerController.getPartners();
    }

    public List<Partner> getList(){
        return lista;
    }

}
