
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import awss3.AmazonS3Manager;
import dao.PostDAO;
import db.DBUtil;
import dto.PostDTO;
import exceptions.DBException;
import exceptions.PostWasNotCreated;

//@WebServlet("/PostUpdateServlet")
@MultipartConfig
public class PostUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostUpdateServlet() {
        super();
    }
    
    private PostDAO postDao;
    private ArrayList<String> house_types;
    
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
	    try {
			house_types = postDao.getHouseTypes();
			for(String s: house_types) {
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		request.setAttribute("houseTypes", house_types);
		
		request.getRequestDispatcher("/WEB-INF/post_update.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long postId = (long)(getArticlePageNoFromURL(request.getRequestURL().toString()));
		PostDTO updatedPost = null;
		
		try {
			updatedPost = this.postDao.getPostById(postId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String selectedItem = "";
		if(request.getParameter("Points") != null){
		   selectedItem = request.getParameter("Points").toString();
		}
		
		updatedPost.setAddress(request.getParameter("address"));
		updatedPost.setArchived(false);
		updatedPost.setArea(Integer.parseInt(request.getParameter("area")));
		updatedPost.setCreationDate(new Timestamp(System.currentTimeMillis()));
		updatedPost.setDescription(request.getParameter("description"));
		updatedPost.setFloor(Integer.parseInt(request.getParameter("floor")));
		updatedPost.setHouse_type(selectedItem);
		updatedPost.setNum_rooms(Integer.parseInt(request.getParameter("rooms")));
		updatedPost.setPhone(request.getParameter("phone"));
		updatedPost.setPrice(Long.parseLong(request.getParameter("price")));
		updatedPost.setYear(Long.parseLong(request.getParameter("year")));
		
		
		System.out.println("update image");
		InputStream inputStream = null;
		Part filePart = request.getPart("photo");
		boolean withImage = false;
		String imageUrl = "";
        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            System.out.println(filePart.getContentType().split("/")[1]);
            
            inputStream = filePart.getInputStream();
            
            if(filePart.getContentType().split("/")[0].equals("application")) {
            		System.out.print("image input is empty");
            } else {
            		imageUrl = updatedPost.getAddress()+"."+filePart.getContentType().split("/")[1];
                System.out.print("imageUrl: " + imageUrl);
                
                withImage = true;
                updatedPost.setImage_url(imageUrl);
                AmazonS3Manager s3Manager = new AmazonS3Manager("shatyr-images");
                s3Manager.uploadFile(imageUrl, inputStream);
            }
        }
        
        if(withImage) {
			try {
				postDao.updatePostWithImage(updatedPost, imageUrl);
			} catch (SQLException e) {
				System.out.println("cant update post db");
				throw new PostWasNotCreated();
			}
		} else {
			try {
				postDao.updatePost(updatedPost);
			} catch (SQLException e) {
				System.out.println("cant update post with image db");
				throw new PostWasNotCreated();
			}
		}
        
		
		request.setAttribute("houseTypes", house_types);
		
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
