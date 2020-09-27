package duke;

import java.util.Scanner;

public class Ui {
    //input
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
    public void printTaskInfo(int index, String task){
        System.out.println (index + ". " + task);
    }

    public void printListInfo(int numTasks, int numTasksDone){
        System.out.println("You have " + numTasks + " Tasks in the list");
        System.out.println("You have completed " + numTasksDone +" Tasks");
    }

    //Print commands relating to actions done by the user: adding, deleting and marking as done
    public void printTaskMarkedDone (String description){
        System.out.println("Good Job completing your task! I have marked it as done.");
        System.out.println(description);
    }
    public void printTaskPreviouslyMarkedDone (String description){
        System.out.println("This task has already been marked as done!");
        System.out.println(description);
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

    public void printCommandInfo() {
        String helpInfo = "Available Commands:\n" +
                "help: Prints out available commands\n" +
                "list: lists out all tasks stored\n" +
                "exit: Ends the duke program\n"+
                "done x: marks the task with index x as done\n" +
                "delete x: deletes the task with index x\n" +
                "\n***Adding Tasks***\n"+
                "todo description of task: adds a todo task with a description of the task\n" +
                "deadline description by yyyy-mm-dd HH:MM : this adds a task with a deadline. If no time is entered the default time is 23:59\n" +
                "event description at yyyy-mm-dd HH:MM : this adds an event at a certain date. If no time is added default is 23:59\n"+
                "\n***Searching for a Task***\n" +
                "find x: finds a task with x as a keyword\n" +
                "today: lists all the tasks occuring on that given day\n"+
                "month x: lists all the tasks in the month of x (integer). If no x is added it shows the task in the current month\n"+
                "year x: lists all the tasks in the year x, if no year is entered it shows the tasks in the current year\n";
        System.out.print(helpInfo);
    }
}
