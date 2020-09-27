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
}
