package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.time.LocalDate;

public class ListCommand extends Command {
    private static char tag;
    private static int value;
    private static int numTasksPrinted = 0;
    private static int numTasksDone = 0;

    public ListCommand (char tag){
        this.tag = tag;
    }
    public ListCommand (char tag, int value){
        this.tag = tag;
        this.value = value;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage){
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
    private static Boolean checkPrintingStatus (Task task){
        switch (tag) {
            case 'N': // normal case of listing
                return true;
            case 'D':
                return (task.getDate().equals(LocalDate.now()));
            case 'M':
                if (value == 0) {
                    return (task.getMonth() == LocalDate.now().getMonthValue());
                }else{
                    return (task.getMonth() == value);
                }
            case 'Y':
                if (value == 0){
                    return (task.getYear() == LocalDate.now().getYear());
                }else{
                    return (task.getYear() == value);
                }
            default:
               return false;
        }
    }
}
