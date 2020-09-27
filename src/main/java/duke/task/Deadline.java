package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String description, LocalDate date){
        super(description);
        this.date = date;
    }

    public String toString (){
        return String.format("[D][%s] %s(by:%s)", getStatusIcon(), description, DateToString());
    }
    public String saveFormat(){
        return String.format("D:%s:%s:%s", isDone, description, date.toString());
    }
    public String DateToString (){
        String day = date.format(DateTimeFormatter.ofPattern("d"));
        int year = date.getYear();
        String month = date.getMonth().toString();

        return String.format("%s %s %s", day, month, year);
    }
}
