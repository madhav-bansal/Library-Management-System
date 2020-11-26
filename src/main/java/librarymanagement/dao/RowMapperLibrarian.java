package librarymanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import librarymanagement.model.Librarian;

public class RowMapperLibrarian implements RowMapper<Librarian>{

	public Librarian mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		Librarian lib= new Librarian();
		lib.setName(rs.getString(1));
		lib.setEmail(rs.getString(2));
		lib.setMobileNumber(rs.getString(3));
		lib.setPassword(rs.getString(4));
		lib.setAddress(rs.getString(5));
		lib.setSex(rs.getString(6));
	
		
		return lib;
	}

}
