package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static boolean shouldExit = false;
    public static ArrayList<Task> tasks = new ArrayList<>();

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
    public static void markTaskInListAsDone(String index){
            int taskIndexCompleted = Integer.parseInt(index.trim());

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
    public static void deleteTask(String index){
        int taskToDelete = Integer.parseInt(index.trim());

        if (taskToDelete > tasks.size()) {
            System.out.println("This is not a valid task\nPlease enter a valid task number");
        }
        else{
            System.out.println("Alright deleting task: " + tasks.get(taskToDelete - 1));
            tasks.remove(taskToDelete - 1);
            System.out.println("Task has been deleted");
        }
    }

    // Add the specific type of tasks to the big list of tasks
    public static void addTodo  (String task) throws DukeException{
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
                markTaskInListAsDone(userInput[1]);
            } catch (NumberFormatException e) {
                System.out.println("Task index is not a number: please enter a valid integer");
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
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println ("Oopsies, seems like you did not enter a description for the task. A todo needs a description!");
            } catch (DukeException e){
                System.out.println ("Oopsies, seems like you left the description blank. A todo needs a description!");
            }
            break;
        case "deadline":
            try{
            addDeadline(userInput[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oopsies, seems like you did not enter a description for the task. A deadline needs a description!");
            } catch (DukeException e){
                System.out.println("A deadline requires the time it is due");
            }
            break;
        case "event":
            try{
                addEvent(userInput[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oopsies, seems like you did not enter a description for the task. An event needs a description!");
            } catch (DukeException e){
                System.out.println("An event requires the time of the event");
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

        while (!shouldExit){
            command = in.nextLine();
            executeCommand(command);
        }
    }
}
