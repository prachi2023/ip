package duke;

import duke.command.*;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Deals with understanding the user input
 * Ensures that the user has entered the commands in the right format
 */
public class Parser {
    /**
     * Returns a Command that can then be executed
     * The String input is minimally broken down into the first word entered which should be the command name
     * An error is thrown when the user has not entered valid formats for the inputs for the various commands
     *
     * @param input the input that the user has entered
     * @param ui required to print error messages when input is entered in the wrong format
     * @return Command class that is required to execute the user's input
     * @throws DukeException
     */
    public static Command parseInput(String input, Ui ui) throws DukeException{
        // Get the command word entered
        String[] userInput = input.split(" ",2);
        String command = userInput[0];
        String[] detailsSplit;
        int taskNum;
        LocalTime time;
        LocalDate date;
        // Find the right command class to return
        switch (command) {
            case "help":
                return new HelpCommand();
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand('N');
            case "find":
                return new FindCommand(userInput[1]);
            case "done":
                taskNum = getInteger(userInput[1], ui)- 1;
                return new DoneCommand(taskNum);
            case "delete":
                taskNum = getInteger(userInput[1], ui) -1;
                return new DeleteCommand(taskNum);
            case "today":
                return new ListCommand('D');
            case "month":
                int month;
                try {
                    month = getInteger(userInput[1], ui);
                } catch (ArrayIndexOutOfBoundsException e){
                    month = 0;
                }
                return new ListCommand('M', month);
            case "year":
                int year;
                try {
                    year = getInteger(userInput[1], ui);
                } catch (ArrayIndexOutOfBoundsException e){
                    year = 0;
                }
                return new ListCommand('Y', year);
            case "todo":
                String description;
                try{
                    description = userInput[1];
                } catch(ArrayIndexOutOfBoundsException e){
                    throw new DukeException("invalid number of arguments entered");
                }
                if (userInput[1].equals("")){
                    throw new DukeException("No description of added");
                }
                return new AddCommand('T', description);

            case "deadline":
                try{
                    detailsSplit = parseDeadline(userInput[1]); //splits the input into the description and the dateTime
                    String[] dateTimeDetails = detailsSplit[1].trim().split(" ", 2); //splits the dateTime string into date and Time
                    date = parseDate(dateTimeDetails[0].trim());
                    time = parseTime(dateTimeDetails[1]);
                } catch (ArrayIndexOutOfBoundsException e){
                    throw new DukeException("invalid number of arguments entered");
                }
                return new AddCommand('D', detailsSplit[0], date, time);
            case "event":
                try {
                    detailsSplit = parseEvent(userInput[1]);//splits the input into the description and the dateTime
                    String[] dateTimeDetails = detailsSplit[1].trim().split(" ", 2); //splits the dateTime string into date and Time
                    date = parseDate(dateTimeDetails[0].trim());
                    time = parseTime(dateTimeDetails[1]);
                } catch (ArrayIndexOutOfBoundsException e){
                    throw new DukeException("invalid number of arguments entered");
                }
                return new AddCommand('E', detailsSplit[0],date, time);
            // If it is none of the above commands, Tell the user to enter a valid command
            default:
                throw new DukeException ("Invalid command Entered. Enter help to see all the commands available");
        }
    }

   protected static LocalDate parseDate(String date) throws DukeException {
        LocalDate d;
        try {
            d = LocalDate.parse(date.trim());
        } catch (DateTimeParseException e){
            throw new DukeException("Please enter the date in the format yyyy-mm-dd");
        }
        return d;
    }

    protected static LocalTime parseTime(String time) throws DukeException  {
        LocalTime t;
        try {
            t = LocalTime.parse(time.trim());
        } catch (DateTimeParseException e){
            throw new DukeException("Time format entered is invalid. please enter HH:MM in 24 hr format");
        }
        return t;
    }

    private static String[] parseDeadline (String userInput) throws DukeException{
        String[] taskDetails = userInput.split("by", 2);
        if (!userInput.contains("by")){
            throw new DukeException("No 'by' for deadline");
        }
        if (taskDetails[0].equals("")){
            throw new DukeException("No description of task added");
        }
        return taskDetails;
    }
    private static String[] parseEvent (String userInput) throws DukeException{
        String[] taskDetails = userInput.split("at", 2);
        if (!userInput.contains("at")){
            throw new DukeException("No 'at' for event");
        }
        if (taskDetails[0].equals("")){
            throw new DukeException("No description of task added");
        }
        return taskDetails;
    }
    // Used for the determining which task to delete or mark as done
    private static Integer getInteger (String indexInput, Ui ui) {
        Integer taskNum = 0;
        try {
            taskNum = Integer.parseInt(indexInput.trim());
        } catch (NumberFormatException e) {
            ui.showErrorMessage("Number not entered as an integer");
        }
        return taskNum;
    }
}
