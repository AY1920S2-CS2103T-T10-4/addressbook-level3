package nasa.model.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ScheduleTest {

    final Date date = new Date("10-03-2020 03:00");
    final Date temp = date;
    final Schedule schedule = new Schedule(date);

    @Test
    void initialisation() {
        assertEquals(date, schedule.getDate());
        assertEquals(0, schedule.getType());
    }

    @Test
    void checkChangeType() {
        Date dateExpected = new Date(date.toString()).addDaysToCurrDate(7);
        schedule.setType(1);
        assertEquals(dateExpected, schedule.getDate());

        dateExpected = new Date(date.toString()).addDaysToCurrDate(14);
        schedule.setType(2);
        assertEquals(dateExpected, schedule.getDate());

        dateExpected = new Date(date.toString()).addMonthsToCurrDate(1);
        schedule.setType(3);
        assertEquals(dateExpected, schedule.getDate());

        schedule.cancel();
        assertEquals(temp, schedule.getDate());
    }

    @Test
    void checkWrongSchedule() {
        assertThrows(IllegalArgumentException.class, () -> schedule.setType(-1));
        assertThrows(IllegalArgumentException.class, () -> schedule.setType(4));
    }

}
