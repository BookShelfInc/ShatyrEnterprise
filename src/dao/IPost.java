package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dto.PostDTO;

public interface IPost {
	public ArrayList<PostDTO> getAllPosts() throws SQLException;
	public PostDTO getPostById(Long id) throws SQLException;
	public PostDTO addPost(PostDTO post) throws SQLException;
	
	public boolean updatePost(PostDTO post) throws SQLException;
	public boolean deletePost(Long post_id) throws SQLException;
	
	public boolean archivePost(Long post_id, boolean val) throws SQLException;
	
	public ArrayList<String> getHouseTypes() throws SQLException;
	
	public ArrayList<PostDTO> getPostsByFilter(ArrayList<Triplet<Boolean, String, String>> filter) throws SQLException;
	public ArrayList<PostDTO> getPostsByOrder(String order) throws SQLException;
}
