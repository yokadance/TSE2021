package com.logistics.prod.Controllers;

import com.logistics.prod.Entities.Partner;
import com.logistics.prod.Interfaces.IPartnerController;
//import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.*;

@Controller
public class PartnerController implements IPartnerController {

    //String wsURL = "http://localhost:8080/VacunasUYG16-api/SoapLogisticsPartners";
    String wsURL = "https://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/SoapLogisticsPartners";
    URL url = null;
    URLConnection connection = null;
    HttpURLConnection httpConn = null;
    String responseString = null;
    String outputString="";
    OutputStream out = null;
    InputStreamReader isr = null;
    BufferedReader in = null;

    @Override
    public void savePartner(Partner partner) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "default" );
        EntityManager entitymanager2 = emfactory.createEntityManager( );
        entitymanager2.getTransaction( ).begin();
        entitymanager2.persist(partner);
        entitymanager2.getTransaction( ).commit( );
        entitymanager2.close();
        emfactory.close( );


    }

    @Override
    public Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void receivePending(Long idV) {
        String xmlInput2 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.webservice/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <soap:sendPendings>\n" +
                "         <!--Optional:-->\n" +
                "         <arg0>"+ idV + "</arg0>\n" +
                "      </soap:sendPendings>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        try
        {
            url = new URL(wsURL);
            connection = url.openConnection();
            httpConn = (HttpURLConnection) connection;

            byte[] buffer = new byte[xmlInput2.length()];
            buffer = xmlInput2.getBytes();

            String SOAPAction = "";
            httpConn.setRequestProperty("Content-Length", "310");
            httpConn.setRequestProperty("Content-Type",
                    "text/xml; charset=utf-8");
            httpConn.setRequestProperty("User-Agent", "Apache-HttpClient/4.5.5 (Java/12.0.1)");

            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Host","localhost:8080");
            httpConn.setRequestProperty("username", "admin");
            httpConn.setRequestProperty("password", "");


            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            out = httpConn.getOutputStream();

            out.write(buffer);
            out.close();

            // Leo la respuesta y la escribo en una salida standard
            isr = new InputStreamReader(httpConn.getInputStream());
            in = new BufferedReader(isr);

            while ((responseString = in.readLine()) != null)
            {
                outputString = outputString + responseString;
            }
            System.out.println(outputString);
            System.out.println("");



            //Obtengo la respuesta del web service y la parseo
            Document document = parseXmlFile(outputString);
            //Document document = parseXmlFile(outputString);

            //Creo un NodeList y obtengo elementos por el tag "return"
            NodeList nodeLst = document.getElementsByTagName("partners");

            //Recorro para buscar elementos root
            for (int temp = 0; temp < nodeLst.getLength(); temp++)
            {
                Node node = nodeLst.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    //Imprimo de prueba
                    Element eElement = (Element) node;
                    System.out.println("Create Date : "    + eElement.getAttribute("return"));

                    //Creo y seteo elemento partner

                    Partner partner = new Partner();

                    Long id = Long.parseLong(eElement.getElementsByTagName("packageId").item(0).getTextContent());
                    String status = eElement.getElementsByTagName("packageStatus").item(0).getTextContent();

                    partner.setId(id);
                    partner.setPackageStatus(status);
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    partner.setUpdateDate(timestamp.toString());

                    // Persisto elemento
                    savePartner(partner);

                }
            }


        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void changePackageStatusTransit (Long idPackage){

        Partner partner = getPackageById(idPackage);

        if (partner!= null) {
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("default");
            EntityManager entitymanager2 = emfactory.createEntityManager();
            entitymanager2.getTransaction().begin();
            partner.setPackageStatus("InTransit");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            partner.setUpdateDate(timestamp.toString());
            entitymanager2.merge(partner);
            entitymanager2.getTransaction().commit();
            entitymanager2.close();
            emfactory.close();
        }
    }
    @Override
    public void changePackageStatusDelivered (Long idPackage){

        Partner partner = (Partner) getPackageById(idPackage);

        if (partner!= null) {
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("default");
            EntityManager entitymanager2 = emfactory.createEntityManager();


            entitymanager2.getTransaction().begin();
            partner.setPackageStatus("Delivered");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            partner.setUpdateDate(timestamp.toString());
            entitymanager2.merge(partner);
            entitymanager2.getTransaction().commit();
            entitymanager2.close();
            emfactory.close();
        }
    }

    @Override
    public String getChangeStatus() {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("default");
        EntityManager entitymanager2 = emfactory.createEntityManager();
        entitymanager2.getTransaction().begin();
        List<Partner> list = entitymanager2.createQuery("select p from Partner p where p.packageStatus not like 'Pending'").getResultList();

        //String statusChanged = StringUtils.join(list.toString(), ",");
        String statusChanged2 = StringUtils.joinWith(",",list);
        //System.out.println(statusChanged);
        System.out.println(statusChanged2);
        return statusChanged2;
    }

    public Partner getPackageById(Long idPackage){

        try{
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "default" );
            EntityManager entitymanager2 = emfactory.createEntityManager( );
            entitymanager2.getTransaction( ).begin();

            Partner partner = entitymanager2.find(Partner.class, idPackage);

            entitymanager2.getTransaction( ).commit( );
            entitymanager2.close();
            emfactory.close( );
            return partner;
        }   catch (NullPointerException a){
            System.out.println(a.getMessage());
        }
        return null;
    }

    @Override
    public void sendChangedStatus () {

        System.out.println("ENTRO ACA");
        String sendChangedStatus = getChangeStatus();

        String xmlInput2 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.webservice/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <soap:receiveChangeStatus>\n" +
                "         <!--Optional:-->\n" +
                "         <arg0>"+sendChangedStatus+"</arg0>\n" +
                "      </soap:receiveChangeStatus>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";


        try {
            url = new URL(wsURL);
            System.out.println("INTENTA CONECTAR");
            connection = url.openConnection();
            httpConn = (HttpURLConnection) connection;

            byte[] buffer = new byte[xmlInput2.length()];
            buffer = xmlInput2.getBytes();

            String SOAPAction = "";
            httpConn.setRequestProperty("Content-Length", "310");
            httpConn.setRequestProperty("Content-Type",
                    "text/xml; charset=utf-8");
            httpConn.setRequestProperty("User-Agent", "Apache-HttpClient/4.5.5 (Java/12.0.1)");

            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Host", "localhost:8080");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);

            out = httpConn.getOutputStream();
            out.write(buffer);
            out.close();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Partner> getPartners() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("default");
        EntityManager entitymanager2 = emfactory.createEntityManager();
        entitymanager2.getTransaction().begin();
        List<Partner> list = entitymanager2.createQuery("select p from Partner p").getResultList();
        entitymanager2.close();
        emfactory.close( );
        return list;
    }
}
