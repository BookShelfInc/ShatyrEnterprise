package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.PostDTO;

public interface IPost {
	public ArrayList<PostDTO> getAllPosts() throws SQLException;
	public PostDTO getPostById(Long id) throws SQLException;
	public PostDTO addPost(PostDTO post) throws SQLException;
	
	public boolean updatePost(PostDTO post) throws SQLException;
	public boolean deletePost(Long post_id) throws SQLException;
	
	public ArrayList<String> getHouseTypes() throws SQLException;
}
