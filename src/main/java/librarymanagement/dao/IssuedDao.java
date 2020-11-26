package librarymanagement.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import librarymanagement.model.Book;
import librarymanagement.model.Issued;

@Repository
public class IssuedDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//inserting book;
	public boolean insert(Issued issue) {
		String query= "INSERT INTO issued(student_email ,book_id, issued_date, due_date,stat) VALUES(?,?,?,?,?);";
		try {
			int r=this.jdbcTemplate.update(query,issue.getStudent_email(),
					issue.getBook_id(),issue.getIssued_date(),issue.getDue_date(),issue.getStatus());
			if(r==1)
			return true;
		}catch(Exception e) {
			System.out.println(e);
			
		}
		return false;

	}


	//removing issue transaction 
	public boolean remove(int id) {

		String query="delete from issued where id=?";
	
		try {
			this.jdbcTemplate.update(query,id);
			return true;
		}catch(Exception e) {
			return false;
		}

		
	}

	public Issued getissue(int id) {
		String query="select * from issued where id=?";
		RowMapper<Issued> map = new RowMapperIssued();
		Issued issue = this.jdbcTemplate.queryForObject(query,map,id);
		return issue;		
	}
	
	public boolean updatestatus(int id,Date d) {
		String query="update issued set stat= stat+ ? where id=?";
		int r=0;
		try {
			
			if(d!=null) {
				//return date is not null that means book is returned and this is called to make status returned
				
				query="update issued set stat=stat+?, return_date=? where id=? ";
				r=this.jdbcTemplate.update(query,1,d,id);
			}
			else
			r= this.jdbcTemplate.update(query,1,id);
			return true;
		}catch(Exception e) {
			System.out.println("update failed issued");
			return false;
		}
		
		
	}
	
	
	public List<Issued> issuedtransactions(String s){
		
		RowMapper<Issued> map = new RowMapperIssued();
		List<Issued> list;
		if(s==null) {
			String q="select * from issued";
			list= this.jdbcTemplate.query(q,map);
			
			return list;
		}
		String q="select * from issued where student_email=?";
		list=this.jdbcTemplate.query(q,map,s);
		
		
		return list;
	}
	
	

}
