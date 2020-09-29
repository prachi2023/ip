package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents work that has to be done by a certain deadline. A <code>Task</code> object corresponds to
 * a task represented using a string for the description of it eg <code> CS2113 tutorial </code>
 * a Boolean to keep track of whether it has been completed
 * a LocalDate format of yyyy-mm-dd to store the date the event is taking place
 * a localTime 24hr format of HH:MM to store the time the event is starting
 */
public class Event extends Task{
    private LocalDate date;
    private LocalTime time;

    public Event(String description, LocalDate date, LocalTime time){
        super(description);
        this.date = date;
        this.time = time;
    }

    public String toString (){
        return String.format("[E][%s] %s(at:%s %s)", getStatusIcon(), description, dateToString(), timeToString());
    }
    public String saveFormat(){
        return String.format("E:%s:%s:%s:%s", isDone, description, date.toString(), time.toString());
    }

    /**
     * Returns a String of the date in the format of dd MONTH (in words) yyyy which defers from the input format
     * @return date of the event
     */
    public String dateToString (){
        String day = date.format(DateTimeFormatter.ofPattern("d"));
        int year = date.getYear();
        String month = date.getMonth().toString();

        return String.format("%s %s %s", day, month, year);
    }
    /**
     * Returns a string in the 12hour clock format with the relevant am/pm period
     * @return time of the event
     */
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
