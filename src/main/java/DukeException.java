public class DukeException extends Exception {
    DukeException (String error){
        switch (error) {
        case "no description":
            System.out.println("Oops it seems you have left the description empty");
            break;
        case "no /by":
            System.out.println("Seems like you have not entered /by to specify the deadline");
            break;
        case "no /at":
            System.out.println("Seems like you have not entered /at to specify the event time");
            break;
        default:
            break;
        }
    }
}
