package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import duke.exception.DukeException;

import duke.Ui.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;



public class Duke {
    public static boolean shouldExit = false;
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static String path = "tasklist.txt";
    public static ui ui;

    public static void main(String[] args) {
        // set up scanner
        ui = new ui();
        Scanner in = new Scanner (System.in);
        String command;

        ui.printWelcomeMessage();

        try{
            setUpFile();
        }catch (IOException e){
            ui.showFileCreatingError(path);
        }
        while (!shouldExit){
            command = in.nextLine();
            executeCommand(command);
        }
    }

    /* Saving file and Editing file */
    //Check and create file
    public static void setUpFile() throws IOException{
        File f = new File(path);
        if (!f.exists()){
            f.createNewFile();
        }else {
            try{
                loadData();
            }catch (FileNotFoundException e){
                //should not occur since, it has been checked that there is a file
                ui.showFileLoadingError();
            }
        }
    }
    //Load data from file
    public static void loadData() throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        // get the tasks line by line
        while (s.hasNext()){
            // | is the barrier between the information
            String taskInput = s.nextLine();
            String[] task = taskInput.split(":");
            switch (task[0]) {
            case "T":
                tasks.add(new ToDo(task[2]));
                break;
            case "D":
                tasks.add(new Deadline(task[2], task[3]));
                break;
            case "E":
                tasks.add(new Event(task[2], task[3]));
                break;
            default:
                tasks.add(new Task(task[2]));
            }
            if (task[1].equals("true")){
                tasks.get(tasks.size()-1).markTaskDone();
            }
        }
    }

    //Add a task to the file
    public static void addTaskToFile (int index) throws IOException{
        FileWriter fw = new FileWriter(path, true);
        String taskToWrite = tasks.get(index).saveFormat();
        fw.write(taskToWrite + System.lineSeparator());
        fw.close();
    }
    //edit the details of the task in the file
    public static void editOrDeleteTaskFile () throws IOException{
        // Delete all the data in the original file
        FileWriter fw = new FileWriter(path);
        fw.write("");
        fw.close();
        //Add back all the data excluding deleted data, including the edited data
        for (int i = 0; i < tasks.size(); i++){
            addTaskToFile(i);
        }
    }

    /* Methods to deal with the different commands */
    public static void exitDuke(){
        shouldExit = true;
        ui.printExitMessage();
    }
    // Print the list of tasks entered
    public static void printList(){
        ui.printTaskListInfo(tasks.size());
        for (int i = 0; i < tasks.size(); i++){
            ui.printTask(i+1, tasks.get(i).toString());
        }
    }

    // Check the status of the task and accordingly mark it as done
    public static void markTaskInListAsDone(int taskIndexCompleted){
            if (taskIndexCompleted > tasks.size()) {
                ui.showInvalidTaskNumError();
                return;
            }
            String task = tasks.get(taskIndexCompleted).toString();
            if (tasks.get(taskIndexCompleted).getIsDone()){
                ui.printTaskPreviouslyMarkedDone(task);
            }else{
                tasks.get(taskIndexCompleted).markTaskDone();
                ui.printTaskMarkedDone(task);
            }
    }

    // Delete a task
    public static void deleteTask(String index){
        int taskToDelete = Integer.parseInt(index.trim())-1;
        if (tasks.isEmpty()){
            ui.showEmptyTaskList();
        }
        else if (taskToDelete > tasks.size()) {
            ui.showInvalidTaskNumError();
        }
        else{
            ui.printDeleteTaskInfo(tasks.get(taskToDelete).toString());
            tasks.remove(taskToDelete);
        }
    }

    // Add the specific type of tasks to the big list of tasks
    public static void addTodo(String task) throws DukeException{
        if (task.equals("")){
            throw new DukeException();
        }
        tasks.add(new ToDo(task));
        ui.printAddedTask(tasks.get(tasks.size()-1).toString());
    }

    public static void addDeadline(String task) throws DukeException{
        //index 0 refers to the description of the task and index 1 refers to the deadline
        String[] taskDetails = task.split("/by", 2);
        if (taskDetails[0].isEmpty()){
            throw new DukeException();
        }
        if (!task.contains("/by")){
            throw new DukeException();
        }
        tasks.add(new Deadline(taskDetails[0].trim(), taskDetails[1].trim()));
        ui.printAddedTask(tasks.get(tasks.size()-1).toString());
    }

    public static void addEvent(String task) throws DukeException{
        //index 0 refers to the description of the task and index 1 refers to the deadline
        String[] taskDetails = task.split("/at", 2);
        if (taskDetails[0].isEmpty()){
            throw new DukeException();
        }
        if (!task.contains("/at")){
            throw new DukeException();
        }
        tasks.add(new Event(taskDetails[0].trim(), taskDetails[1].trim()));
        ui.printAddedTask(tasks.get(tasks.size()-1).toString());
    }

    /* main method do determine command entered by user */
    // Determine the command sent and execute accordingly
    public static void executeCommand (String input){
        // Find the command word entered
        String[] userInput = input.split(" ",2);
        String command = userInput[0];
        // Determine the command
        switch (command) {
        case "bye":
            exitDuke();
            break;
        case "list":
            printList();
            break;
        case "done":
            try {
                int taskNumCompleted = Integer.parseInt(userInput[1].trim()) - 1;
                markTaskInListAsDone(taskNumCompleted);
                editOrDeleteTaskFile();
            } catch (NumberFormatException e) {
                ui.showInvalidTaskNumError();
            } catch (IOException e){
                ui.showFileLoadingError();
            }
            break;
        case "delete":
            try {
                deleteTask(userInput[1]);
                editOrDeleteTaskFile ();
            } catch (NumberFormatException e) {
                ui.showInvalidTaskNumError();
            } catch (IOException e){
                ui.showFileEditingError();
            }
            break;
        case "todo":
            try{
                addTodo(userInput[1]);
                addTaskToFile (tasks.size() -1);
            } catch (ArrayIndexOutOfBoundsException e){
                ui.showInvalidTaskNumError();
            } catch (DukeException e){
                ui.showNoDescriptionError();
            } catch (IOException e){
                ui.showFileEditingError();
            }
            break;
        case "deadline":
            try{
                addDeadline(userInput[1]);
                addTaskToFile (tasks.size() -1);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showInvalidTaskNumError();
            } catch (DukeException e){
                ui.showNoDescriptionError();
                ui.showNoEventError();
            } catch (IOException e){
                ui.showFileEditingError();
            }
            break;
        case "event":
            try{
                addEvent(userInput[1]);
                addTaskToFile (tasks.size() -1);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showInvalidTaskNumError();
            } catch (DukeException e){
                ui.showNoDescriptionError();
                ui.showNoEventError();
            }catch (IOException e){
                ui.showFileEditingError();
            }
            break;
        // If it is none of the above commands, Tell the user to enter a valid command
        default:
            ui.showInvalidCommand();
        }
    }
}
