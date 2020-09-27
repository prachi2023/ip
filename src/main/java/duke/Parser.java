package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parseInput(String input, Ui ui) throws DukeException{
        // Find the command word entered
        String[] userInput = input.split(" ",2);
        String command = userInput[0];

        int taskNum;
        LocalTime time;
        LocalDate date;

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "done":
                taskNum = getIntegerIndex(userInput[1], ui);
                return new DoneCommand(taskNum);
            case "delete":
                taskNum = getIntegerIndex(userInput[1], ui);
                return new DeleteCommand(taskNum);
            case "todo":
                return new AddCommand('T', userInput[1]);
            case "deadline":
                String[] deadlineDetails = parseDeadline(userInput[1]); //splits the input into the description and the dateTime
                String[] dateTimeDetails = deadlineDetails[1].trim().split(" ", 2); //splits the dateTime string into date and Time
                date = parseDate(dateTimeDetails[0].trim());
                try {
                    time = parseTime(dateTimeDetails[1], ui);
                } catch (ArrayIndexOutOfBoundsException e){
                    time = parseTime(" ", ui);
                }
                return new AddCommand('D', deadlineDetails[0], date, time);
            case "event":
                String[] eventDetails = parseEvent(userInput[1]);//splits the input into the description and the dateTime
                dateTimeDetails = eventDetails[1].trim().split(" ", 2); //splits the dateTime string into date and Time
                date = parseDate(dateTimeDetails[0].trim());
                try {
                    time = parseTime(dateTimeDetails[1], ui);
                } catch (ArrayIndexOutOfBoundsException e){
                    time = parseTime(" ", ui);
                }
                return new AddCommand('E', eventDetails[0],date, time);
            // If it is none of the above commands, Tell the user to enter a valid command
            default:
                throw new DukeException ("Invalid command Entered");
        }
    }



    static LocalDate parseDate(String date) throws DukeException {
        LocalDate d;
        try {
            d = LocalDate.parse(date.trim());
        } catch (DateTimeParseException e){
            throw new DukeException("Please enter the date in the format yyyy-mm-dd");
        }
        return d;
    }

    static LocalTime parseTime(String time, Ui ui) throws DukeException {
        LocalTime t;
        try {
            t = LocalTime.parse(time.trim());
        } catch (DateTimeParseException e){
            t = LocalTime.parse("23:59");
            ui.showErrorMessage("Time format entered is invalid. Please enter HH:MM. 23:59 has been added as a default time.");
        }
        return t;
    }


    private static String[] parseDeadline (String userInput) throws DukeException{
        String[] taskDetails = userInput.split("/by", 2);
        if (!userInput.contains("/by")){
            throw new DukeException("No /by for deadline");
        }
        if (taskDetails[0].equals("")){
            throw new DukeException("No description of task added");
        }
        return taskDetails;
    }
    private static String[] parseEvent (String userInput) throws DukeException{
        String[] taskDetails = userInput.split("/at", 2);
        if (!userInput.contains("/at")){
            throw new DukeException("No /at for event");
        }
        if (taskDetails[0].equals("")){
            throw new DukeException("No description of task added");
        }
        return taskDetails;
    }

    private static Integer getIntegerIndex (String indexInput, Ui ui) {
        Integer taskNum = -1;
        try {
            taskNum = Integer.parseInt(indexInput.trim()) - 1;
        } catch (NumberFormatException e) {
            ui.showErrorMessage("Number Format Exception");
        }
        return taskNum;
    }
}
