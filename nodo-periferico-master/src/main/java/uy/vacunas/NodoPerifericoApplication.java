package uy.vacunas;//import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class NodoPerifericoApplication {

	public static void main(String[] args)  {
		SpringApplication.run(NodoPerifericoApplication.class, args);
		Consume receiveScheduleAndReservations = new Consume();
		//long idSchedule = 1L;
		//receiveScheduleAndReservations.receiveSchedule(idSchedule);
		//receiveScheduleAndReservations.receiveReservation(idSchedule);

	}
}
