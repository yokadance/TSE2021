package com.logistics.prod.Servlets;

import com.logistics.prod.Controllers.PartnerController;
import com.logistics.prod.Entities.Partner;
import com.logistics.prod.Interfaces.IPartnerController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/ListPackages")
public class ServletListPackages extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletListPackages() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
        //request.getAttribute("listaLote");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IPartnerController partnerController = new PartnerController();
        List<Partner> partnerList = partnerController.getPartners();
        request.setAttribute("list", partnerList);

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);

        doGet(request, response);
    }

}



