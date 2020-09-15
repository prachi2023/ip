package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static boolean shouldExit = false;
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static String path = "data/tasklist.txt";

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
                System.out.println("File not found");
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
                tasks.get(tasks.size()).markTaskDone();
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

    public static void exitDuke (){
        shouldExit = true;
        System.out.println("Bye. Hope to see you again soon!");
    }
    // Print the list of tasks entered
    public static void printList (){
        System.out.println("Here is your List of Tasks: ");
        for (int i = 0; i < tasks.size(); i++){
            System.out.println(i+1 + ". " + tasks.get(i));
        }
        System.out.println("You have " + tasks.size() + " Tasks in the list");
    }

    // Mark a specific task in the list as done
    public static void markTaskInListAsDone(int taskIndexCompleted){
            if (taskIndexCompleted > tasks.size()) {
                System.out.println("This is not a valid task\nPlease enter a valid task number");
            }
            if (tasks.get(taskIndexCompleted - 1).getIsDone()){
                System.out.println("This task has already been marked as done!");
            }else{
                System.out.println("Good Job completing your task! I have marked it as done.");
                tasks.get(taskIndexCompleted - 1).markTaskDone();
            }
            System.out.println(tasks.get(taskIndexCompleted - 1));
    }

    // Delete a task
    public static void deleteTask(String index){
        int taskToDelete = Integer.parseInt(index.trim());
        if (tasks.isEmpty()){
            System.out.println("There is no task to delete");
        }
        else if (taskToDelete > tasks.size()) {
            System.out.println("This is not a valid task\nPlease enter a valid task number");
        }
        else{
            System.out.println("Alright deleting task: " + tasks.get(taskToDelete - 1));
            tasks.remove(taskToDelete - 1);
            System.out.println("Task has been deleted");
        }
    }

    // Add the specific type of tasks to the big list of tasks
    public static void addTodo(String task) throws DukeException{
        if (task.equals("")){
            throw new DukeException("no description");
        }
        tasks.add(new ToDo(task));
        System.out.println("There you go I've added it to the list\n" + tasks.get(tasks.size()-1));
    }

    public static void addDeadline (String task) throws DukeException{
        //index 0 refers to the description of the task and index 1 refers to the deadline
        String[] taskDetails = task.split("/by", 2);
        if (taskDetails[0].isEmpty()){
            throw new DukeException("no description");
        }
        if (!task.contains("/by")){
            throw new DukeException("No /by");
        }
        tasks.add(new Deadline(taskDetails[0].trim(), taskDetails[1].trim()));
        System.out.println("There you go I've added it to the list\n" + tasks.get(tasks.size()-1));
    }

    public static void addEvent (String task) throws DukeException{
        //index 0 refers to the description of the task and index 1 refers to the deadline
        String[] taskDetails = task.split("/at", 2);
        if (taskDetails[0].isEmpty()){
            throw new DukeException("no description");
        }
        if (!task.contains("/at")){
            throw new DukeException("No /at");
        }
        tasks.add(new Event(taskDetails[0].trim(), taskDetails[1].trim()));
        System.out.println("There you go I've added it to the list\n" +  tasks.get(tasks.size()-1));
    }


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
                System.out.println("Task index is not a number: please enter a valid integer");
            } catch (IOException e){
                System.out.println ("File is unable to be edited");
            }
            break;
        case "delete":
            try {
                deleteTask(userInput[1]);
            } catch (NumberFormatException e) {
                System.out.println("Task index is not a number: please enter a valid integer");
            }
            break;
        case "todo":
            try{
                addTodo(userInput[1]);
                addTaskToFile (tasks.size() -1);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println ("Oopsies, seems like you did not enter a description for the task. A todo needs a description!");
            } catch (DukeException e){
                System.out.println ("Oopsies, seems like you left the description blank. A todo needs a description!");
            } catch (IOException e){
                System.out.println ("File is unable to be edited");
            }
            break;
        case "deadline":
            try{
                addDeadline(userInput[1]);
                addTaskToFile (tasks.size() -1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oopsies, seems like you did not enter a description for the task. A deadline needs a description!");
            } catch (DukeException e){
                System.out.println("A deadline requires the time it is due");
            } catch (IOException e){
                System.out.println ("File is unable to be edited");
            }
            break;
        case "event":
            try{
                addEvent(userInput[1]);
                addTaskToFile (tasks.size() -1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oopsies, seems like you did not enter a description for the task. An event needs a description!");
            } catch (DukeException e){
                System.out.println("An event requires the time of the event");
            }catch (IOException e){
                System.out.println ("File is unable to be edited");
            }
            break;
        // If it is none of the above commands, Tell the user to enter a valid command
        default:
            System.out.println ("Please enter a valid command! I do not understand '" + userInput[0] + "'");
        }
    }

    public static void main(String[] args) {
        // set up scanner
        Scanner in = new Scanner (System.in);
        String command;

        // Introduction
        System.out.println ("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        try{
            setUpFile();
        }catch (IOException e){
            System.out.println ("Unable to create a file name 'tasklist'");
        }
        while (!shouldExit){
            command = in.nextLine();
            executeCommand(command);
        }
    }
}
