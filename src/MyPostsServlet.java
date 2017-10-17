

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
import db.DBUtil;
import exceptions.DBException;


//@WebServlet("/MyPostsServlet")
public class MyPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPostsServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String goBack = request.getContextPath();
		ArrayList<PostDTO> myPosts = null;
		HttpSession userSession = request.getSession();
		
		try {
			myPosts = userDao.getUsersPosts(((UserDTO)userSession.getAttribute("authUser")).getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(myPosts.size());
		for(PostDTO p: myPosts) {
			System.out.println(p.getImage_url());
		}
		request.setAttribute("myPosts", myPosts);
		request.setAttribute("rootPath", goBack);
		
		request.getRequestDispatcher("/WEB-INF/my_post.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
