package brandon.chatbot.commands.generalcommands;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;

/**
 * Represents a command that exits from the duke program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    @Override
    public CommandResult execute() {
        return new CommandResult("bye bye...");
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
