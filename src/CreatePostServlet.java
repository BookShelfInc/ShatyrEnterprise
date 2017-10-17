import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import awss3.AmazonS3Manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.PostDAO;
import dao.UserDAO;
import db.DBUtil;
import dto.PostDTO;
import dto.UserDTO;
import exceptions.DBException;
import exceptions.PostWasNotCreated;
import exceptions.UserWasNotCreated;

//@WebServlet("/CreatePostServlet")
@MultipartConfig
public class CreatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PostDAO postDao;
	private UserDAO userDao;
	private ArrayList<String> house_types;
	
    public CreatePostServlet() {
        super();
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
	    userDao = new UserDAO(conn);
	    
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
		
		request.setAttribute("houseTypes", house_types);
		
		HttpSession userSession = request.getSession();
		if (userSession == null || userSession.getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath());
		} else {
			request.getRequestDispatcher("/WEB-INF/create_post.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PostDTO newPost = new PostDTO();
		
		String selectedItem = "";
		if(request.getParameter("Points") != null){
		   selectedItem = request.getParameter("Points").toString();
		}
		
		newPost.setAddress(request.getParameter("address"));
		newPost.setArchived(false);
		newPost.setArea(Integer.parseInt(request.getParameter("area")));
		newPost.setCreationDate(new Timestamp(System.currentTimeMillis()));
		newPost.setDescription(request.getParameter("description"));
		newPost.setFloor(Integer.parseInt(request.getParameter("floor")));
		newPost.setHouse_type(selectedItem);
		newPost.setNum_rooms(Integer.parseInt(request.getParameter("rooms")));
		newPost.setPhone(request.getParameter("phone"));
		newPost.setPrice(Long.parseLong(request.getParameter("price")));
		newPost.setYear(Long.parseLong(request.getParameter("year")));
		
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
            		imageUrl = newPost.getAddress()+"."+filePart.getContentType().split("/")[1];
                System.out.print("imageUrl: " + imageUrl);
                
                withImage = true;
                newPost.setImage_url(imageUrl);
                AmazonS3Manager s3Manager = new AmazonS3Manager("shatyr-images-test");
                s3Manager.uploadFile(imageUrl, inputStream);
            }
        }
		
		request.setAttribute("houseTypes", house_types);
		
		if(withImage) {
			try {
				newPost = postDao.addPostWithImage(newPost, imageUrl);
			} catch (SQLException e) {
				throw new PostWasNotCreated();
			}
		} else {
			try {
				newPost = postDao.addPost(newPost);
			} catch (SQLException e) {
				throw new PostWasNotCreated();
			}
		}
		
		if(newPost != null) {
			try {
				HttpSession userSession = request.getSession();
				userDao.addPostToUser(((UserDTO)userSession.getAttribute("authUser")).getId(), newPost.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect(request.getContextPath());
			System.out.println("Post have created");
		}
		else {
			request.getRequestDispatcher("/WEB-INF/create_post.jsp").forward(request, response);
		}
		
	}
}
