

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

import dao.PostDAO;
import db.DBUtil;
import dto.PostDTO;
import exceptions.DBException;
import exceptions.PostWasNotCreated;

//@WebServlet("/PostUpdateServlet")
public class PostUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostUpdateServlet() {
        super();
    }
    
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
	    
	    postDao = new PostDAO(conn);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postId = getArticlePageNoFromURL(request.getRequestURL().toString());
		PostDTO post = null;
		
		try {
			post = this.postDao.getPostById((long) postId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("myPosts", post);
		
		request.getRequestDispatcher("/WEB-INF/post_update.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postId = getArticlePageNoFromURL(request.getRequestURL().toString());
		PostDTO updatedPost = null;
		
		try {
			updatedPost = this.postDao.getPostById((long) postId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		updatedPost.setAddress(request.getParameter("address"));
		updatedPost.setArchived(false);
		updatedPost.setArea(Integer.parseInt(request.getParameter("area")));
		updatedPost.setCreationDate(new Timestamp(System.currentTimeMillis()));
		updatedPost.setDescription(request.getParameter("description"));
		updatedPost.setFloor(Integer.parseInt(request.getParameter("floor")));
		updatedPost.setHouse_type(request.getParameter("house_type"));
		updatedPost.setNum_rooms(Integer.parseInt(request.getParameter("rooms")));
		updatedPost.setPhone(request.getParameter("phone"));
		updatedPost.setPrice(Long.parseLong(request.getParameter("price")));
		updatedPost.setYear(Long.parseLong(request.getParameter("year")));
		
		
		try {
			postDao.updatePost(updatedPost);
		} catch (SQLException e) {
			throw new PostWasNotCreated();
		}
		
		response.sendRedirect(request.getContextPath());
	}
	
	private int getArticlePageNoFromURL(String url) {
        int postIndex = url.lastIndexOf("/");

        if (postIndex > -1) {
            String valStr = url.substring(postIndex + 1);

            try {
                return Integer.parseInt(valStr);
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        return 0;
    }
}
