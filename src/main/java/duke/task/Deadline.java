package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task{
    private LocalDate date;
    private LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time){
        super(description);
        this.date = date;
        this.time = time;
    }

    public String toString (){
        return String.format("[D][%s] %s(by:%s %s)", getStatusIcon(), description, dateToString(), timeToString());
    }
    public String saveFormat(){
        return String.format("deadline:%s:%s:%s:%s", isDone, description, date.toString(), time.toString());
    }
    public String dateToString (){
        int day = date.getDayOfMonth();
        int year = date.getYear();
        String month = date.getMonth().toString();

        return String.format("%s %s %s", day, month, year);
    }

    public String timeToString(){
        int hour;
        int min;
        String period; //am or pm

        if (time.getHour() > 12){
            hour = (time.getHour() -12);
            period = "pm";

        } else if (time.getHour() == 0){
            hour = 12;
            period = "am";
        } else {
            hour = time.getHour();
            period = "am";
        }
        min = time.getMinute();
        return String.format("%s:%02d %s", hour, min, period);
    }

    public int getDay(){
        return date.getDayOfMonth();
    }
    public int getYear(){
        return date.getYear();
    }
    public int getMonth(){
        return date.getMonthValue();
    }
    public LocalDate getDate(){
        return date;
    }
}
