package brandon.chatbot.commands;

/**
 * Represents a command that lists the tasks in TaskList.
 */
public class ListCommand extends Command {
    public static final String LIST_SUCCESS = "ok... I'm listing..";

    @Override
    public CommandResult execute() {

        return new CommandResult(LIST_SUCCESS, tasks);
    }
}
