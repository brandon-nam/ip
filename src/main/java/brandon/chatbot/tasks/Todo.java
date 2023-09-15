package brandon.chatbot.tasks;

import brandon.chatbot.Tag;
import brandon.chatbot.common.DukeException;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Sets the title of a todo task and creates a Todo object.
     *
     * @param title of the todo task.
     * @throws DukeException if the title of the task is blank.
     */
    public Todo(String title, Optional<ArrayList<Tag>> tags) throws DukeException {
        super(title, tags);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

}

