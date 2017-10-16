package exceptions;

import javax.servlet.ServletException;

public class PostWasNotCreated extends ServletException {
	public PostWasNotCreated() {
		super("Post was not created");
	}
}
