package brandon.chatbot.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

/**
 * Handles the Ui that users view on screen.
 */
public class TextUi {
    private static final String DIVIDER = "--------------------------------";
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Assigns input and output streams to the constructor below.
     */
    public TextUi() {
        this(System.in, System.out);
    }

    /**
     * Assigns new Scanner and System.out print stream to the class variables.
     *
     * @param in assigns System.in input stream to Scanner upon TextUi() call.
     * @param out assigns System.out print stream to the class variable.
     */
    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Receives input from the user and prints the command entered to the user.
     *
     * @return input string the user entered.
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();

        showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    /**
     * Prints out the string messages
     *
     * @param messages the user wants to show to user.
     */
    public void showToUser(String... messages) {
        for (String m : messages) {
            out.println(m);
        }
    }

    /**
     * Prints the feedback the CommandResult entails, tasks by calling showTasks if the result is not empty
     * and prints the divider.
     *
     * @param result is of type CommandResult which when called getTasks() returns an optional.
     */
    public void showResultToUser(CommandResult result) {
        final Optional<TaskList> resultTasks = result.getTasks();
        showToUser(result.feedbackToUser);
        if (resultTasks.isPresent()) {
            showTasks(resultTasks.get());
        }
        showToUser(DIVIDER);
    }

    private void showTasks(TaskList tasks) {
        final ArrayList<String> formattedTasks = new ArrayList<>();
        for (Task t : tasks.getList()) {
            formattedTasks.add(t.getStatus());
        }
        showToUserAsIndexedList(formattedTasks);
    }

    private void showToUserAsIndexedList(List<String> list) {
        showToUser(getIndexedListForViewing(list));
    }

    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 1;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
