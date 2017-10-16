import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import dao.UserDAO;
import db.DBUtil;
import dto.UserDTO;
import exceptions.DBException;
import exceptions.UserWasNotCreated;

//@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SignUpServlet() {
        super();
    }
    
    private UserDAO userDao;
    private PostDAO postDao;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
	    String jdbcUrl = config.getInitParameter("jdbcUrl");
	    String dbUsername = config.getInitParameter("dbUsername");
	    String dbPassword = config.getInitParameter("dbPassword");
	    
	    System.out.println(jdbcUrl);
	    System.out.println(dbUsername);
	    System.out.println(dbPassword);

	    Connection conn = null;

	    try {
            conn = DBUtil.createConnection(jdbcUrl, dbUsername, dbPassword);
        } catch (DBException e) {
            e.printStackTrace();
        }
	    
	    userDao = new UserDAO(conn);
	    postDao = new PostDAO(conn);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		
		System.out.println(email + " " + password);
		
		if(email.length() > 0 && password.length() > 0 && repassword.length() > 0 && (password.equals(repassword))) {
			UserDTO userDto = null;
			try {
				userDto = userDao.addUser(email, password);
			} catch (SQLException e) {
				throw new UserWasNotCreated();
			}
			
			if(userDto != null) {
				HttpSession userSession = request.getSession();
				userSession.setAttribute("authUser", userDto);
				response.sendRedirect(request.getContextPath());
			} else {
				request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
		}
	}

}
