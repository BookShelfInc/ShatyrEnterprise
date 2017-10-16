import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import dao.Triplet;
import dao.UserDAO;
import db.DBUtil;
import dto.PostDTO;
import dto.UserDTO;
import exceptions.DBException;
import exceptions.PostWasNotCreated;
import exceptions.UserWasNotCreated;

//@WebServlet("/")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MainServlet() {
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
		
		ArrayList<PostDTO> allPosts = null;
		
		try {
			ArrayList<Triplet<Boolean, String, String>> m = new ArrayList<Triplet<Boolean, String, String>>();
			m.add(new Triplet(false, "address", "lenin"));
			m.add(new Triplet(true, "area", "150"));
			
			allPosts = postDao.getPostsByFilter(m);
		} catch (SQLException e) {
			throw new PostWasNotCreated();
		}
		
		request.setAttribute("allPosts", allPosts);
		
		HttpSession userSession = request.getSession();

        if (userSession == null || userSession.getAttribute("authUser") == null) {
        		request.setAttribute("authUser", null);
        } else {
        		request.setAttribute("authUser", userSession.getAttribute("authUser"));
        }
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
