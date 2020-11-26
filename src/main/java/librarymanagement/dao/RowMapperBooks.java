package librarymanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import librarymanagement.model.Book;

public class RowMapperBooks implements RowMapper<Book>{


	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Book book = new Book();
		book.setId(rs.getInt(1));
		book.setName(rs.getString(2));
		book.setAuthor(rs.getString(3));
		book.setPublisher(rs.getString(4));
		book.setAvailability(rs.getInt(5));
		book.setDesc(rs.getString(6));
		book.setStatus(rs.getBoolean(7));
		return book;
	}

}
