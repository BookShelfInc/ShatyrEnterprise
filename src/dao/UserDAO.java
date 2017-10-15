package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

import dto.UserDTO;

public class UserDAO implements IUser{
	
	private Connection connection;
	
	public UserDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public UserDTO getUserById(Long user_id) throws SQLException {
		String sql = "select * from users where id=?";
		PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.setLong(1, user_id);

		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
            UserDTO userDto = new UserDTO();
            userDto.setId(rs.getLong("id"));
            userDto.setEmail(rs.getString("email"));
            userDto.setPassword(rs.getString("password"));
            return userDto;
		}
		
		return null;
	}

	@Override
	public UserDTO getUserByEmail(String email, String password) throws SQLException {
		String sql = "select * from users where email=? and password=?";
		PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.setString(1, email);
        stm.setString(2, password);

		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
            UserDTO userDto = new UserDTO();
            userDto.setId(rs.getLong("id"));
            userDto.setEmail(rs.getString("email"));
            userDto.setPassword(rs.getString("password"));
            return userDto;
		}
		
		return null;
	}

	@Override
	public UserDTO addUser(String email, String password) throws SQLException {
		String sql = "insert into users(email, password) values(?, ?)";
		PreparedStatement stm = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		stm.setString(1, email);
        stm.setString(2, password);
        
        int afRows = stm.executeUpdate();

        ResultSet rs = stm.getGeneratedKeys();

        if(rs.next() && rs != null){
            UserDTO userDto = new UserDTO();
            userDto.setId(rs.getLong("id"));
            userDto.setEmail(rs.getString("email"));
            userDto.setPassword(rs.getString("password"));
            return userDto;
        }
        
		return null;
	}

	@Override
	public boolean addPostToUser(Long user_id, Long post_id) throws SQLException {
		String sql = "insert into users_posts(user_id, post_id) values(?, ?)";
		PreparedStatement stm = this.connection.prepareStatement(sql);
		
		stm.setLong(1, user_id);
        stm.setLong(2, post_id);
        
        boolean afRows = stm.execute();
        return afRows;
	}

	@Override
	public boolean removePostFromUser(Long user_id, Long post_id) throws SQLException {
		String sql = "delete from users_posts where users_posts.id in (select id from users_posts where user_id=? and post_id=?)";
		PreparedStatement stm = this.connection.prepareStatement(sql);
		
		stm.setLong(1, user_id);
        stm.setLong(2, post_id);
        
        boolean afRows = stm.execute();
		return afRows;
	}

}
