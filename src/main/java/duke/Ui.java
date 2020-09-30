package duke;

import java.util.Scanner;

/**
 * The class deals with all interactions with the user.
 * Input from the user is gotten using this class
 * All the outputs to the command line are sent from this class
 */
public class Ui {
    //input

    /**
     * Returns a String that the user enters into the command line
     * Method returns once the user has clicked "enter"
     * @return one line of the users input
     */
    public String readCommand(){
        Scanner in = new Scanner (System.in);
        return in.nextLine();
    }

    //welcome and exit message

    public void printWelcomeMessage (){
        System.out.println ("Hello! I'm DUKE");
        System.out.println ("What can I do for you?");
    }
    public void printExitMessage (){
        System.out.println ("Bye");
        System.out.println ("See you again!");
    }
    public void printLine(){
        System.out.println("----------------------------------------------------------");
    }

    //Error messages
    public void showErrorMessage (String errorMessage){
        System.out.println (errorMessage);
    }

    public void showFileCreatingError (String fileName){
        System.out.println ("Unable to create a file name "+ fileName);
    }

    public void showFileEditingError (){
        System.out.println ("Unable to edit file");
    }

    //Print task and list info

    /**
     * Prints out the Task information along with the index of the task in the task list
     * @param index Position of the task in the TaskList
     * @param task Description of the task along with other information in a string format
     */
    public void printTaskInfo(int index, String task){
        System.out.println (index + ". " + task);
    }

    /**
     * Prints out the summary of the tasks printed, including the total num of tasks and the num completed
     * @param numTasks Number of tasks that the ListCommand has printed according to the constraints given
     * @param numTasksDone Number of tasks that have been completed out of the tasks printed
     */

    public void printListInfo(int numTasks, int numTasksDone){
        System.out.println("You have " + numTasks + " Tasks in the list");
        System.out.println("You have completed " + numTasksDone +" Tasks");
    }

    //Print commands relating to actions done by the user: adding, deleting and marking as done
    public void printTaskMarkedDone (String description){
        System.out.println("Good Job completing your task! I have marked it as done.");
        System.out.println(description);
    }

    /**
     * Tells the user that the task entered by the user has been marked as done previously
     * @param task Description of the task along with other information in a string format
     */
    public void printTaskPreviouslyMarkedDone (String task){
        System.out.println("This task has already been marked as done!");
        System.out.println(task);
    }

    public void printDeletedTask(String task){
        System.out.println("Alright deleting task: " + task);
        System.out.println("Task has been deleted");
    }

    public void printAddedTask (String task){
        System.out.println("There you go I've added " + task + " to the list");
    }
    public void printNoMatchingTask (){
        System.out.println("There are no tasks that match this keyword");
    }
    public void printMatchingTask(){
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Prints out all the commands that the user can enter
     * Shows the format in which the user needs to enter the commands
     */
    public void printCommandInfo() {
        String helpInfo = "Available Commands:\n" +
                "help: Prints out available commands\n" +
                "list: lists out all tasks stored\n" +
                "exit: Ends the duke program\n"+
                "done x: marks the task with index x as done\n" +
                "delete x: deletes the task with index x\n" +
                "\n***Adding Tasks***\n"+
                "todo description of task: adds a todo task with a description of the task\n" +
                "deadline description by yyyy-mm-dd HH:MM : this adds a task with a deadline\n" +
                "event description at yyyy-mm-dd HH:MM : this adds an event at a certain date.\n"+
                "\n***Searching for a Task***\n" +
                "find x: finds a task with x as a keyword\n" +
                "today: lists all the tasks occuring on that given day\n"+
                "month x: lists all the tasks in the month of x (integer). If no x is added it shows the task in the current month\n"+
                "year x: lists all the tasks in the year x, if no year is entered it shows the tasks in the current year\n";
        System.out.print(helpInfo);
    }
}
