package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.PostDTO;
import dto.UserDTO;

public interface IUser {
	public UserDTO getUserById(Long user_id) throws SQLException;
	public UserDTO getUserByEmail(String email) throws SQLException;
	public UserDTO addUser(String email, String password) throws SQLException;
	public UserDTO loginUser(String email, String password) throws SQLException;
	
	public ArrayList<PostDTO> getUsersPosts(Long user_id) throws SQLException;
	public boolean addPostToUser(Long user_id, Long post_id) throws SQLException;
	public boolean removePostFromUser(Long user_id, Long post_id) throws SQLException;
}
