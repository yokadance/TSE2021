package Interfaces;

import Entities.Reservation;
import Entities.Schedule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface IScheduleController {

    public void saveSchedule(Schedule schedule);

}
