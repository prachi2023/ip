package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents the command to be done
 * Keeps check of whether the application has to end <code> isExit </code>
 */
public class Command {
    public Boolean isExit = false;
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {

    }
}
