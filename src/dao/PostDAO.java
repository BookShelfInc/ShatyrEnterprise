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

import dto.PostDTO;
import dto.UserDTO;

public class PostDAO implements IPost {
	
	private Connection connection;
	
	public PostDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public ArrayList<PostDTO> getAllPosts() throws SQLException {
		ArrayList<PostDTO> allPosts = new ArrayList<PostDTO>();
		
		String sql = "select * from posts";
		PreparedStatement stm = this.connection.prepareStatement(sql);

		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
            PostDTO post = new PostDTO();
            post.setId(rs.getLong("id"));
            post.setAddress(rs.getString("address"));
            post.setArea(rs.getInt("area"));
            post.setHouse_type(rs.getString("house_type"));
            post.setFloor(rs.getInt("floor"));
            post.setPrice(rs.getLong("price"));
            post.setDescription(rs.getString("description"));
            post.setYear(rs.getLong("year"));
            post.setCreationDate(rs.getTimestamp("creation_date"));
            post.setPhone(rs.getString("phone"));
            post.setArchived(rs.getBoolean("archived"));
            
            allPosts.add(post);
		}
		
		return allPosts;
	}

	@Override
	public PostDTO getPostById(Long id) throws SQLException {
		return null;
	}
	
//	String _address, int _area, String _house_type, int _num_rooms, int _floor, Long _price,
//	String _description, Long _year, Date _creation_date, String _phone, boolean _archived

	@Override
	public PostDTO addPost(PostDTO post) throws SQLException {
		
		if(this.isDuplicate(post.getAddress(), post.getDescription())) {
			return null;
		}
		
		String sql = "insert into posts(address, area, house_type, num_rooms, floor, price, description, year, "
				+ "creation_date, phone, archived) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stm = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		stm.setString(1, post.getAddress());
		stm.setInt(2, post.getArea());
		stm.setString(3, post.getHouse_type());
		stm.setInt(4, post.getNum_rooms());
		stm.setInt(5, post.getFloor());
		stm.setLong(6, post.getPrice());
		stm.setString(7, post.getDescription());
		stm.setLong(8, post.getYear());
		stm.setTimestamp(9, post.getCreationDate());
		stm.setString(10, post.getPhone());
		stm.setBoolean(11, post.isArchived());
		
		int afRows = stm.executeUpdate();

        ResultSet rs = stm.getGeneratedKeys();

        if(rs.next() && rs != null) {
        		post.setId(rs.getLong("id"));
            return post;
        }
        
		return null;
	}

	@Override
	public boolean updatePost(PostDTO post) throws SQLException {
		String sql = "update posts set address=?, area=?, house_type=?, num_rooms=?, floor=?, price=?, description=?, year=?, "
				+ "creation_date=?, phone=?, archived=? where id=?";
		PreparedStatement stm = this.connection.prepareStatement(sql);
		
		stm.setString(1, post.getAddress());
		stm.setInt(2, post.getArea());
		stm.setString(3, post.getHouse_type());
		stm.setInt(4, post.getNum_rooms());
		stm.setInt(5, post.getFloor());
		stm.setLong(6, post.getPrice());
		stm.setString(7, post.getDescription());
		stm.setLong(8, post.getYear());
		stm.setTimestamp(9, post.getCreationDate());
		stm.setString(10, post.getPhone());
		stm.setBoolean(11, post.isArchived());
		stm.setLong(12, post.getId());
		
        
        boolean afRows = stm.execute();
        return afRows;
	}

	@Override
	public boolean deletePost(Long post_id) throws SQLException {
		String sql = "delete from posts where posts.id=?";
		PreparedStatement stm = this.connection.prepareStatement(sql);
		
		stm.setLong(1, post_id);
        
        boolean afRows = stm.execute();
        return afRows;
	}
	
	public boolean isDuplicate(String address, String description) throws SQLException {
		String sql = "select * from posts where address=? and description=?";
		PreparedStatement stm = this.connection.prepareStatement(sql);
		
		stm.setString(1, address);
		stm.setString(2, description);
		
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			return true;
		}
		
		return false;
	}
	
}