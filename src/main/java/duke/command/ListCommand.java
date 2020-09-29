package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

import java.time.LocalDate;

/**
 * Represents the command entered by the user.
 * <code> tag</code> corresponds to the type of list the user wants to be printed (everything or according to date)
 */
public class ListCommand extends Command {
    private static char tag;
    private static int value;
    private static int numTasksPrinted ;
    private static int numTasksDone ;

    public ListCommand (char tag){
        this.tag = tag;
        numTasksDone = 0;
        numTasksPrinted = 0;
    }
    public ListCommand (char tag, int value){
        this.tag = tag;
        this.value = value;
        numTasksDone = 0;
        numTasksPrinted = 0;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException{
       for (int i = 0; i < tasks.size(); i++){
            if (checkPrintingStatus(tasks.get(i))){
                ui.printTaskInfo(i+1, tasks.get(i).toString());
                processPrintedTaskDetails(tasks.get(i));
                }
            }
        ui.printListInfo(numTasksPrinted, numTasksDone);
    }
    private static void processPrintedTaskDetails (Task task){
        numTasksPrinted ++;
        if (task.getIsDone()){
            numTasksDone ++;
        }
    }
    // This functions checks whether the task fulfills the conditions necessary to be printed (eg is it today)
    private static Boolean checkPrintingStatus (Task task) throws DukeException {
        switch (tag) {
            case 'N': // normal case of listing
                return true;
            case 'D':
                return (task.getDate().equals(LocalDate.now()));
            case 'M':
                if (value > 12 || value < 0){
                    throw new DukeException("Please enter a valid month number from 0 to 12. 0 shows the tasks in the current month");
                }else if (value == 0) {
                    return (task.getMonth() == LocalDate.now().getMonthValue());
                }else{
                    return (task.getMonth() == value);
                }
            case 'Y':
                if (value < 0){
                    throw new DukeException("Please enter a valid year from 0. 0 shows the tasks in the current year");
                }else if (value == 0){
                    return (task.getYear() == LocalDate.now().getYear());
                }else{
                    return (task.getYear() == value);
                }
            default:
               return false;
        }
    }
}
