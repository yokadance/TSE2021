package enumerations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class enumerationsTests {

  @Test
  public void BatchStateTest() {
    int i = BatchState.Arrived.getCode();
  }

  @Test
  public void LogicOpTest() {
    int i = LogicOp.equalTo.getCode();
  }

  @Test
  public void PackageStateTest() {
    int i = PackageState.Delivered.getCode();
  }

  @Test
  public void ReservationStateTest() {
    int i = ReservationState.pending.getCode();
  }

  @Test
  public void SexTest() {
    int i = Sex.feminine.getCode();
  }

  @Test
  public void VaccinationStateTest() {
    int i = VaccinationState.Used.getCode();
    i = VaccinationState.Available.getCode();
    i = VaccinationState.Destroyed.getCode();
  }
}
