package AllTasks;
import Enum.Frequency;
import Enum.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class WeeklyTask extends Task {
    public WeeklyTask(String title, String description, LocalDateTime dateTime, Type type, Frequency frequency) {
        super(title, description, dateTime, type, frequency);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        LocalDate date = this.getDateTime().toLocalDate();
        return localDate.equals(date) || (localDate.isAfter(date) &&
                localDate.getDayOfWeek().equals(date.getDayOfWeek()));
    }

    @Override
    public String toString() {
        return super.toString() + " - еженедельная задача";
    }
}
