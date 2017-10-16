

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import dao.PostDAO;
import dao.UserDAO;
import db.DBUtil;
import dto.PostDTO;
import exceptions.DBException;
import exceptions.PostWasNotCreated;
import exceptions.UserWasNotCreated;

//@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO postDao;
	
    public CreatePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
	    
	    postDao = new PostDAO(conn);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/create_post.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		if (userSession == null || userSession.getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath());
		}
		
		PostDTO newPost = new PostDTO();
		
		newPost.setAddress(request.getParameter("address"));
		newPost.setArchived(false);
		newPost.setArea(Integer.parseInt(request.getParameter("area")));
		newPost.setCreationDate(new Timestamp(System.currentTimeMillis()));
		newPost.setDescription(request.getParameter("description"));
		newPost.setFloor(Integer.parseInt(request.getParameter("floor")));
		newPost.setHouse_type(request.getParameter("house_type"));
		newPost.setNum_rooms(Integer.parseInt(request.getParameter("rooms")));
		newPost.setPhone(request.getParameter("phone"));
		newPost.setPrice(Long.parseLong(request.getParameter("price")));
		newPost.setYear(Long.parseLong(request.getParameter("year")));
		
		try {
			newPost = postDao.addPost(newPost);
		} catch (SQLException e) {
			throw new PostWasNotCreated();
		}
		
		if(newPost != null) {
			response.sendRedirect(request.getContextPath());
			System.out.println("Post have created");
		}
		else {
			request.getRequestDispatcher("/WEB-INF/create_post.jsp").forward(request, response);
		}
	}

}
