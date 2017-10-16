

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

import dto.PostDTO;

//@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreatePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostDTO newPost = new PostDTO();
		newPost.setAddress(request.getParameter("address"));
		//newPost.setArchived(false);
		newPost.setArea(Integer.parseInt(request.getParameter("area")));
		newPost.setCreationDate(new Timestamp(System.currentTimeMillis()));
		newPost.setDescription(request.getParameter("description"));
		newPost.setFloor(Integer.parseInt(request.getParameter("floor")));
		newPost.setHouse_type(Integer.parseInt(request.getParameter("house_type")));
//		newPost.setNum_rooms(5);
//		newPost.setPhone("2678364");
//		newPost.setPrice(12000000L);
//		newPost.setYear(2017L);
	}

}
