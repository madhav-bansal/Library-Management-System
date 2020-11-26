package librarymanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import librarymanagement.model.Issued;

public class RowMapperIssued implements RowMapper<Issued> {

	public Issued mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		Issued issue= new Issued();
		issue.setId(rs.getInt(1));
		issue.setStudent_email(rs.getString(2));
		issue.setBook_id(rs.getInt(3));
		issue.setIssued_date(rs.getDate(4));
		issue.setDue_date(rs.getDate(5));
		issue.setReturn_date(rs.getDate(6));
		issue.setStatus(rs.getInt(7));
		issue.setFine(rs.getInt(8));
		
		return issue;
	}

}
