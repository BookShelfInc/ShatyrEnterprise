

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import dao.UserDAO;
import db.DBUtil;
import dto.PostDTO;
import dto.UserDTO;
import exceptions.DBException;
import exceptions.PostWasNotCreated;

//@WebServlet("/PostDetailServlet")
public class PostDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostDetailServlet() {
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
		
		request.setAttribute("post", post);
		System.out.println(post.getImage_url());
		
		request.getRequestDispatcher("/WEB-INF/post_detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
