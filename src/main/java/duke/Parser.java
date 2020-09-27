package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parseInput(String input, Ui ui) throws DukeException{
        // Find the command word entered
        String[] userInput = input.split(" ",2);
        String command = userInput[0];

        int taskNum;

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
                String[] deadlineDetails = parseDeadline(userInput[1]);
                LocalDate date = parseDate(deadlineDetails[1]);
                return new AddCommand('D', deadlineDetails[0], date);
            case "event":
                String[] eventDetails = parseEvent(userInput[1]);
                return new AddCommand('E', eventDetails[0], eventDetails[1]);
            // If it is none of the above commands, Tell the user to enter a valid command
            default:
                throw new DukeException ("Invalid command Entered");
        }
    }

    static LocalDate parseDate(String deadlineDetail) throws DukeException {
        LocalDate d;
        try {
            d = LocalDate.parse(deadlineDetail.trim());
        } catch (DateTimeParseException e){
            throw new DukeException("Please enter the date in the format yyyy-mm-dd");
        }
        return d;
    }


    private static String[] parseDeadline (String userInput) throws DukeException{
        String[] taskDetails = userInput.split("/by", 2);
        if (!userInput.contains("/by")){
            throw new DukeException("No /by for deadline");
        }
        return taskDetails;
    }
    private static String[] parseEvent (String userInput) throws DukeException{
        String[] taskDetails = userInput.split("/at", 2);
        if (!userInput.contains("/at")){
            throw new DukeException("No /at for event");
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
