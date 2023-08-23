import java.util.Scanner;

public class Duke {
    public static Task[] taskList = new Task[100];
    public static int taskListIndex = 0;

    public static void unmark(int index) {
        Task target = taskList[index - 1];
        target.setDone(false);
        System.out.println("I've marked this task as not done yet...");
        System.out.println("    " + target.getStatus());
        System.out.println("--------------------------------");
    }
    public static void markAsDone(int index) {
        Task target = taskList[index - 1];
        target.setDone(true);
        System.out.println("I've marked this as done...");
        System.out.println("    " + target.getStatus());
        System.out.println("--------------------------------");
    }
    public static void listTask() {
        for (int i = 1; i <= taskListIndex; i++) {
            Task target = taskList[i - 1];
            System.out.println("    " + i + ". " + target.getStatus());
        }
        System.out.println("--------------------------------");
    }
    public static void addTask(Task newTask) {
        taskList[taskListIndex] = newTask;
        System.out.println("    Got it... I've added this task...");
        System.out.println("      " + newTask.getStatus());
        taskListIndex++;
        System.out.println("    Now you have " + (taskListIndex) + " tasks in the list...");
        System.out.println("--------------------------------");
    }
    public static boolean continueOrNot(String[] input) {
        if (input[0].equals("bye")) {
            return false;
        }
        return true;
    }
    public static String input(Scanner sc) {
        String reply = sc.nextLine();
        System.out.println("--------------------------------");
        return reply;
    }

    public static String[] splitBy(String input, String regex) {
        String [] parts = input.split(regex, 2);
        if (parts.length <= 1) {
            parts = new String[]{parts[0], ""};
        }
        return parts;
    }

    public static void greeting() {
        System.out.println("Hello.. I'm ekuD..");
        System.out.println("I probably won't be much of a help.. But ask me something..");
        System.out.println("--------------------------------");
        Scanner sc = new Scanner(System.in);
        String [] input = splitBy(input(sc), " ");

        while (continueOrNot(input)) {
            try {
                if (input[0].equals("list")) {
                    listTask();
                } else if (input[0].equals("mark")) {
                    markAsDone(Integer.parseInt(input[1]));
                } else if (input[0].equals("unmark")) {
                    unmark(Integer.parseInt(input[1]));
                } else if (input[0].equals("todo")) {
                    addTask(new Todo(input[1]));
                } else if (input[0].equals("deadline")) {
                    if (input[1].isBlank()) {
                        addTask(new Deadline("", ""));
                    }
                    String newString = input[1];
                    String[] parts = splitBy(newString, "/by");
                    addTask(new Deadline(parts[0].strip(), parts[1].strip()));
                } else if (input[0].equals("event")) {
                    if (input[1].isBlank()) {
                        addTask(new Event("", "", ""));
                    }
                    String newString = input[1].strip();
                    String[] splitFrom = splitBy(newString, "/from");
                    String[] splitTo = splitBy(splitFrom[1], "/to");;
                    String startDate = splitTo[0].strip();
                    String endDate = splitTo[1].strip();
                    addTask(new Event(splitFrom[0].strip(), startDate, endDate));
                } else {
                    throw new DukeUnknownCommandException();
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = splitBy(input(sc), " ");
        }
        System.out.println("   bye...");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
    }
}

