package Entities;

import entities.*;
import enumerations.ReservationState;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReservationTest {

  Reservation reservation;

  private String fakeString = "Test";

  private Date fakeDate = new Date();

  private Long fakeLong = 1L;

  private int fakeInt = 1;

  private float fakeFloat;

  @Before
  public void setup() {}

  @After
  public void teardown() {}

  @Test
  public void reservationTest() {
    reservation =
      new Reservation(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        new VaccinationCenter(),
        new Schedule(),
        new VaccinationPost(),
        new Citizen(),
        fakeString,
        fakeString,
        ReservationState.canceled
      );
    reservation.getVaccinationCenter();
    reservation.setVaccinationCenter(new VaccinationCenter());
    reservation.setVaccinationPost(new VaccinationPost());
    reservation.setCitizen(new Citizen());
    reservation.setDate(fakeString);
    reservation.setTime(fakeString);
    reservation.setReservationState(ReservationState.rejected);
    reservation.getDTReservation();
  }
}
