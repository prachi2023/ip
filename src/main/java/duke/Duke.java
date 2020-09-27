package duke;
import duke.command.Command;
import duke.exception.DukeException;

import java.io.IOException;



public class Duke {
    public static boolean isExit = false;
    public static String path = "tasklist.txt";
    public static Storage storage;

    public static void main(String[] args) {
        //Instantiate Ui, Tasklist and Parser
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        // Set up and load data from file
        try{
            storage = new Storage(path);
            storage.load(tasks, parser);
        }catch (IOException e){
            ui.showFileCreatingError(path);
        }catch (DukeException e){
            ui.showErrorMessage(e.getErrorMessage());
        }

        ui.printWelcomeMessage();

        while (!isExit) {
            try {
                ui.printLine();
                String fullCommand = ui.readCommand();
                Command c = Parser.parseInput(fullCommand, ui);
                c.execute(ui, tasks, storage);
                isExit = c.isExit;
            } catch (DukeException e) {
                ui.showErrorMessage(e.getErrorMessage());
            }
        }
        ui.printExitMessage();
    }
}


