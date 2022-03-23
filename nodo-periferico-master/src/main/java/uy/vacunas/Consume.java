package uy.vacunas;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;


import Controllers.ReservationController;
import Entities.Reservation;
import Entities.Schedule;
import Interfaces.IReservationController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


//@SpringBootApplication
public class Consume {

    public static void main(String[] args) throws IOException {



        Consume receive = new Consume();
        long vacunatorio = 1L;
        //receive.receiveReservation(vacunatorio);
       // receive.vaccinationAct(2L);
       //receive.receiveSchedule(vacunatorio);
       // receive.sendConfirmed();


        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while(!salir){
            System.out.println("***************************");
            System.out.println("*********vacunas.uy********");
            System.out.println("*******Nodo Logístico******");
            System.out.println("***************************");
            System.out.println("1. Recibir Agenda y reservas");
            System.out.println("2. Vacunar usuario");
            System.out.println("3. Enviar vacunados");
            System.out.println("4. Salir");

            try {
                System.out.println("Elija una opción: ");
                opcion = sn.nextInt();

                switch(opcion){
                    case 1:

                        receive.receiveReservation(1L);


                        break;
                    case 2:

                        System.out.println("Ingrese ID de reserva: ");
                        Long number1 = sn.nextLong();
                        receive.vaccinationAct(number1);

                        break;
                    case 3:

                        receive.sendConfirmed();

                        break;

                    case 4:
                        salir=true;
                        break;

                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {

            }

        }



    }

    @Inject
    ReservationController reservationController = new ReservationController();

    private void receiveReservation(long vacunatorio) {
        reservationController.receiveReservation(vacunatorio);
    }

    private void vaccinationAct (Long reservationId){
        reservationController.vaccinationAct(reservationId);
    }

    private void sendConfirmed (){
        reservationController.sendConfirmed();
    }




    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory(){
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("nodo-periferico");
        return factoryBean;
    }


    public void receiveSchedule(Long id) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "nodo-periferico" );
        EntityManager entitymanager2 = emfactory.createEntityManager( );

        String wsURL = "http://localhost:8080/VacunasUYG16-api/SoapSendSchedule";
        URL url = null;
        URLConnection connection = null;
        HttpURLConnection httpConn = null;
        String responseString = null;
        String outputString="";
        OutputStream out = null;
        InputStreamReader isr = null;
        BufferedReader in = null;


        String xmlInput2 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.webservice/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <soap:sendScheduleById>\n" +
                "         <!--Optional:-->\n" +
                "         <arg0>" + id + "</arg0>\n" +
                "      </soap:sendScheduleById>\n" +
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

            // Obtengo la respuesta del web service
            Document document = reservationController.parseXmlFile(outputString);
            //Document document = parseXmlFile(outputString);
            NodeList nodeLst = document.getElementsByTagName("ns2:sendScheduleByIdResponse");

            JAXBContext jaxbContext = JAXBContext.newInstance(Schedule.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            String webServiceResponse = nodeLst.item(0).getTextContent();
            System.out.println("The response from the web service call is : " + webServiceResponse);

            //Elimino cabeceras para quedarme con el elemento root, para este caso "schedules"
            String nuevo = outputString.replace("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:sendScheduleByIdResponse xmlns:ns2=\"http://soap.webservice/\">","");
            String nuevo1 = nuevo.replace("</ns2:sendScheduleByIdResponse></soap:Body></soap:Envelope>","");

            // Unmarshall de la salida.
            StringReader reader = new StringReader(nuevo1);
            Schedule schedule = (Schedule) unmarshaller.unmarshal(reader);


            // Persisto en BD


            entitymanager2.getTransaction( ).begin();
            entitymanager2.persist(schedule);
            entitymanager2.getTransaction( ).commit( );
            entitymanager2.close();
            emfactory.close( );
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }




}