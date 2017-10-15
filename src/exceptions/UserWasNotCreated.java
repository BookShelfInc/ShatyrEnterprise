package exceptions;

import javax.servlet.ServletException;

public class UserWasNotCreated extends ServletException {
	
	public UserWasNotCreated() {
		super("User was not created");
	}
}
