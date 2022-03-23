package Entities;

import DTO.DTPerson;
import DTO.DTRole;
import entities.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class RoleTest {

  Role role;

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
  public void roleTest() {
    role = new Role(new DTRole(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeString, new DTPerson()) {}) {};
    role.getPerson();
    role.getDTRole();
  }
}
