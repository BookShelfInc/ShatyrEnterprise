import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

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
import dto.PostDTO;
import dto.UserDTO;
import exceptions.DBException;
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
		
		System.out.println("main servlet");
		
//		PostDTO post = new PostDTO();
//		post.setAddress("moscow");
//		post.setArchived(false);
//		post.setArea(120);
//		post.setCreationDate(new Timestamp(System.currentTimeMillis()));
//		post.setDescription("cool house");
//		post.setFloor(10);
//		post.setHouse_type("monolit");
//		post.setNum_rooms(5);
//		post.setPhone("2678364");
//		post.setPrice(12000000L);
//		post.setYear(2017L);
//		
//		try {
//			post = postDao.addPost(post);
//			System.out.println(post==null);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		request.setAttribute("val", 123);
		
		HttpSession userSession = request.getSession();

        if (userSession == null || userSession.getAttribute("AUTHENTICATED") == null) {
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
