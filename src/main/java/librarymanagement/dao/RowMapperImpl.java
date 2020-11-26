package librarymanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import librarymanagement.model.User;

public class RowMapperImpl implements RowMapper<User>{


	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user= new User();
		user.setName(rs.getString(1));
		user.setEmail(rs.getString(2));
		user.setMobileNumber(rs.getString(3));
		user.setPassword(rs.getString(4));
		user.setAddress(rs.getString(5));
		user.setUniversityName(rs.getString(6));
		user.setSex(rs.getString(7));
	
		
		
		return user;
	}


}
