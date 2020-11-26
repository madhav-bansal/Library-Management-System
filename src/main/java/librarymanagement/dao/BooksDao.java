package librarymanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import librarymanagement.model.Book;

@Repository
public class BooksDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//inserting book;
	public boolean insert(Book book) {
		String query= "INSERT INTO books( name , author,publisher,availability,description,stat) VALUES(?,?,?,?,?,true);";
		try {
			int r=this.jdbcTemplate.update(query,book.getName(),
					book.getAuthor(),book.getPublisher(),
					book.getAvailability(),book.getDesc());
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}

	}
	
	
	//removing book
	public boolean remove(int id) {

		String query="delete from books where id=?";

		try {
			this.jdbcTemplate.update(query,id);
		}catch(Exception e) {
			System.out.println(e);
			//do exception handling
			return false;
		}

		return true;
	}
	
	public Book getBook(int id) {
		String query="select * from books where id=?";
		RowMapper<Book> map = new RowMapperBooks();
		List<Book> list=this.jdbcTemplate.query(query, map,id);
		
		if(list.isEmpty()) return null;
		else return list.get(0);		
	}
	
	public List<Book> search(String q){
		RowMapper<Book> map = new RowMapperBooks();
		
		String q2="%"+q+"%";
		if(q==null) q2="%%";
	
		String query= "select * from books where name like ? ";
		List<Book> qs= this.jdbcTemplate.query(query,map,q2);
	
		return qs;
	}
	
	public boolean updatestat(int id) {
		String query=" update books set stat=not stat where id=?";
		try{
			this.jdbcTemplate.update(query,id);
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	public boolean updateavail(int id, int value) {
		String query="update books set availability= ? where id=?";
		try {
		
			this.jdbcTemplate.update(query,value,id);
			return true;
		}catch(Exception e) {
			return false;
		}
			
	}
	
	
	//call this function to decrease the available count of book;
	public boolean change_count(int id,int v) {
		String query="update books set availability= availability+? where id=?";
		try {
			this.jdbcTemplate.update(query,v,id);
			return true;
			
		}catch(Exception e) {
			return false;
		}
		
	}
	
	
	
	
	
	
}