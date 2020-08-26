import java.util.Scanner;

public class Duke {
    public static void printList (String[] list, int listSize){
        for (int i = 0; i < listSize; i++){
            System.out.println(i+1 + ". " + list[i]);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        String line;
        String[] list = new String[100];
        int listSize = 0;
        System.out.println ("Hello! I'm Duke");
        System.out.println("What can I do for you?");


        while (true){
            line = in.nextLine();
            if (line.equals("bye")){
                break;
            }
            if (line.equals("list")) {
                printList(list, listSize);
                continue;
            }
            System.out.println (line);
            list[listSize] = line;
            listSize ++;

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
