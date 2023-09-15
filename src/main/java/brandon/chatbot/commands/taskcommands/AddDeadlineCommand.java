package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.tag.Tag;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Deadline;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents the command that adds deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String ADD_SUCCESS = "ok... I'm adding..";
    private Deadline toAdd;

    public AddDeadlineCommand(String taskName, String deadline, Optional<ArrayList<Tag>> tags) throws DukeException {
        this.toAdd = new Deadline(taskName, deadline, tags);
    }

    @Override
    public CommandResult execute() {
        tasks.addTask(toAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
