package soap;

import Controllers.ReservationController;
import Interfaces.IReservationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import javax.inject.Inject;

@Endpoint
public class sendConfirmedSOAP {
    private static final String NAMESPACE_URI = "http://in28minutes.com/students";

    @Inject
    ReservationController reservationController;



}
