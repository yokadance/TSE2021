package Controllers;

import Entities.Reservation;
import Entities.Vaccinators;
import Interfaces.IReservationController;
import com.sun.xml.messaging.saaj.packaging.mime.internet.InternetHeaders;
//import org.apache.cxf.headers.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.*;
import java.net.*;
import java.util.*;

@Configuration
@Controller
public class ReservationController implements IReservationController {

    @Value("${urlSoap}")
    String urlSoap;


    //String wsURL = "http://localhost:8080/VacunasUYG16-api/SoapSendSchedule";
    String wsURL = "https://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/SoapSendSchedule";

    URL url = null;
    URLConnection connection = null;
    HttpURLConnection httpConn = null;
    String responseString = null;
    String outputString="";
    OutputStream out = null;
    InputStreamReader isr = null;
    BufferedReader in = null;

    @Inject
    IReservationController iReservationController;


    @Override
    public void saveReservation(Reservation reservation) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "nodo-periferico" );
        EntityManager entitymanager2 = emfactory.createEntityManager( );
        entitymanager2.getTransaction( ).begin();
        entitymanager2.persist(reservation);
        entitymanager2.getTransaction( ).commit( );
        entitymanager2.close();
        emfactory.close( );
    }


    public void saveVaccinator(Vaccinators vaccinators) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "nodo-periferico" );
        EntityManager entitymanager2 = emfactory.createEntityManager( );
        entitymanager2.getTransaction( ).begin();
        entitymanager2.persist(vaccinators);
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



    public void sendConfirmed () {


        System.out.println("ENTRO ACA");
        List<Long> id = getConfirmed();

        String xmlInput2 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.webservice/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <soap:receiveConfirmed>\n" +
                "         <!--Optional:-->\n" +
                "         <arg0>" + id + "</arg0>\n" +
                "      </soap:receiveConfirmed>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try {
            System.out.println("URL PRUEBA" + urlSoap);
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
            System.out.println(httpConn.getResponseCode());
            System.out.println("ENTRO ACA");

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void receiveReservation(Long id) {


        String xmlInput2 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.webservice/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <soap:sendReservations2>\n" +
                "         <!--Optional:-->\n" +
                "         <arg0>" + id + "</arg0>\n" +
                "      </soap:sendReservations2>\n" +
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
            NodeList nodeLst = document.getElementsByTagName("return");

            //Recorro para buscar elementos root
            for (int temp = 0; temp < nodeLst.getLength(); temp++)
            {
                Node node = nodeLst.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {

                    Element eElement = (Element) node;


                    //Creo y seteo elemento reservation

                    Reservation reservation = new Reservation();
                    int scheduleId = Integer.parseInt(eElement.getElementsByTagName("scheduleId").item(0).getTextContent());
                    reservation.setScheduleId(scheduleId);
                    reservation.setReservationState(eElement.getElementsByTagName("reservationState").item(0).getTextContent());
                    reservation.setReservationDate(eElement.getElementsByTagName("reservationDate").item(0).getTextContent());
                    reservation.setReservationTime(eElement.getElementsByTagName("reservationTime").item(0).getTextContent());
                    Long reservationId = Long.parseLong(eElement.getElementsByTagName("id").item(0).getTextContent());
                    reservation.setId(reservationId);
                    Long vaccinationPostId = Long.parseLong(eElement.getElementsByTagName("vaccinationPostId").item(0).getTextContent());
                    reservation.setVaccinationPostId(vaccinationPostId);
                    reservation.setCitizenId(eElement.getElementsByTagName("citizenName").item(0).getTextContent());
                    reservation.setPersonId(eElement.getElementsByTagName("personId").item(0).getTextContent());
                    reservation.setCreateDate(eElement.getElementsByTagName("createDate").item(0).getTextContent());
                    reservation.setSended(false);

                    if (getReservationById(reservation.getId()) == null) {
                        // Persisto elemento
                        saveReservation(reservation);
                    }


                }
            }


        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void receiveAssignations(Long id) {

        //String wsURL2 = "http://localhost:8080/VacunasUYG16-api/SoapSendSchedule";
        String wsURL2 = "https://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/SoapSendSchedule";
        URL url2 = null;
        URLConnection connection2 = null;
        HttpURLConnection httpConn2 = null;
        String responseString2 = null;
        String outputString2="";
        OutputStream out2 = null;
        InputStreamReader isr2 = null;
        BufferedReader in2 = null;

        String xmlInput2 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.webservice/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <soap:vaccinatorByPost>\n" +
                "         <!--Optional:-->\n" +
                "         <arg0>"+id+"</arg0>\n" +
                "      </soap:vaccinatorByPost>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try
        {
            url2 = new URL(wsURL2);
            connection2 = url2.openConnection();
            httpConn2 = (HttpURLConnection) connection2;

            byte[] buffer2 = new byte[xmlInput2.length()];
            buffer2 = xmlInput2.getBytes();

            String SOAPAction = "";
            httpConn2.setRequestProperty("Content-Length", "310");
            httpConn2.setRequestProperty("Content-Type",
                    "text/xml; charset=utf-8");
            httpConn2.setRequestProperty("User-Agent", "Apache-HttpClient/4.5.5 (Java/12.0.1)");

            httpConn2.setRequestProperty("SOAPAction", SOAPAction);
            httpConn2.setRequestMethod("POST");
            httpConn2.setRequestProperty("Host","localhost:8080");
            httpConn2.setRequestProperty("username", "admin");
            httpConn2.setRequestProperty("password", "");


            httpConn2.setDoOutput(true);
            httpConn2.setDoInput(true);
            out2 = httpConn2.getOutputStream();
            out2.write(buffer2);
            out2.close();

            // Leo la respuesta y la escribo en una salida standard
            isr2 = new InputStreamReader(httpConn2.getInputStream());
            in2 = new BufferedReader(isr2);

            while ((responseString2 = in2.readLine()) != null)
            {
                outputString2 = outputString2 + responseString2;
            }
            System.out.println(outputString2);
            System.out.println("");



            //Obtengo la respuesta del web service y la parseo
            Document document = parseXmlFile(outputString2);
            //Document document = parseXmlFile(outputString);

            //Creo un NodeList y obtengo elementos por el tag "return"
            NodeList nodeLst = document.getElementsByTagName("return");

            //Recorro para buscar elementos root
            for (int temp = 0; temp < nodeLst.getLength(); temp++)
            {
                Node node = nodeLst.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {

                    Element aElement = (Element) node;

                    //Creo y seteo elemento reservation

                    Vaccinators vaccinators = new Vaccinators();
                    //int scheduleId = Integer.parseInt(eElement.getElementsByTagName("date").item(0).getTextContent());
                    vaccinators.setDate(aElement.getElementsByTagName("date").item(0).getTextContent());
                    Integer documentNumber = Integer.parseInt(aElement.getElementsByTagName("documentNumber").item(0).getTextContent());
                    vaccinators.setDocumentNumber(documentNumber);
                    Integer idAssignament = Integer.parseInt(aElement.getElementsByTagName("id").item(0).getTextContent());
                    vaccinators.setId(idAssignament);
                    vaccinators.setvPostName(aElement.getElementsByTagName("vPostName").item(0).getTextContent());
                    vaccinators.setVaccinationPostId(aElement.getElementsByTagName("vaccinationPostId").item(0).getTextContent());
                    vaccinators.setName(aElement.getElementsByTagName("name").item(0).getTextContent());

                    // Persisto elemento
                    if (getVaccinatorById(vaccinators.getId()) == null) {
                        saveVaccinator(vaccinators);
                    }
                }
            }


        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation getReservationById(Long idReservation){

        try{
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "nodo-periferico" );
        EntityManager entitymanager2 = emfactory.createEntityManager( );
        entitymanager2.getTransaction( ).begin();

        Reservation reservation = entitymanager2.find(Reservation.class, idReservation);
        entitymanager2.getTransaction( ).commit( );
        entitymanager2.close();
        emfactory.close( );
            return reservation;
        }   catch (NullPointerException a){
            System.out.println(a.getMessage());
        }
        return null;
    }

    @Override
    public Vaccinators getVaccinatorById(int id){

        try{
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "nodo-periferico" );
            EntityManager entitymanager2 = emfactory.createEntityManager( );
            entitymanager2.getTransaction( ).begin();
            Vaccinators vaccinators = entitymanager2.find(Vaccinators.class, id);
            entitymanager2.getTransaction( ).commit( );
            entitymanager2.close();
            emfactory.close( );
            return vaccinators;
        }   catch (NullPointerException a){
            System.out.println(a.getMessage());
        }
        return null;
    }

    @Override
    public void vaccinationAct (Long idReservation){

        Reservation reservation = getReservationById(idReservation);
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "nodo-periferico" );
        EntityManager entitymanager2 = emfactory.createEntityManager( );


        entitymanager2.getTransaction( ).begin();
        reservation.setReservationState("confirmed");
        //entitymanager2.persist(reservation);
        entitymanager2.merge(reservation);
        entitymanager2.getTransaction( ).commit( );
        entitymanager2.close();
        emfactory.close( );

    }


    public List<Long> getConfirmed (){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "nodo-periferico" );
        EntityManager entitymanager2 = emfactory.createEntityManager( );

        List <Reservation> confirmed = entitymanager2.createQuery("select r from Reservation as r where r.reservationState = 'confirmed' and r.sended = false ").getResultList();
        List  listConfirmed = new ArrayList();

        confirmed.forEach(

                ob -> {
                    Long id = ob.getId();
                    listConfirmed.add(id);
                    entitymanager2.getTransaction( ).begin();
                    ob.setSended(true);
                    entitymanager2.merge(ob);
                    entitymanager2.getTransaction( ).commit( );
                });
        System.out.println(listConfirmed.size());
        entitymanager2.close();
        emfactory.close( );
                return listConfirmed;
    }

    @Override
    public List<Reservation> getReservations() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("nodo-periferico");
        EntityManager entitymanager2 = emfactory.createEntityManager();
        entitymanager2.getTransaction().begin();
        List<Reservation> list = entitymanager2.createQuery("select p from Reservation p where p.sended = false").getResultList();
        entitymanager2.close();
        emfactory.close( );
        return list;
    }

    @Override
    public List<Vaccinators> getVaccinators() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("nodo-periferico");
        EntityManager entitymanager2 = emfactory.createEntityManager();
        entitymanager2.getTransaction().begin();
        List<Vaccinators> list = entitymanager2.createQuery("select v from Vaccinators v").getResultList();
        entitymanager2.close();
        emfactory.close( );
        return list;
    }


}
