package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the command to find a keyword in the tasks
 * <code> keyword</code> corresponds to the word that needs to be there in the task descriptions
 */
public class FindCommand extends Command {
    String keyword;
    public FindCommand (String keyword){
        this.keyword = keyword;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage)throws DukeException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++){
            if(tasks.checkTask(i, keyword.trim())){
                matchingTasks.add(tasks.get(i));
            }
        }
        //Check if there are any matching to inform the user
        if (matchingTasks.isEmpty()){
            ui.printNoMatchingTask();
        }else {
            ui.printMatchingTask();
            for (int i = 0; i < matchingTasks.size(); i++){
                ui.printTaskInfo(i+1, matchingTasks.get(i).toString());
            }
        }
    }
}
