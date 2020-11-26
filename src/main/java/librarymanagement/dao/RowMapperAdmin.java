package librarymanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import librarymanagement.model.Admin;

public class RowMapperAdmin implements RowMapper<Admin> {

	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin= new Admin();
		admin.setName(rs.getString(1));
		admin.setEmail(rs.getString(2));
		admin.setPassword(rs.getString(3));
		return null;
	}

}
