package librarymanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import librarymanagement.model.Admin;

@Repository
public class AdminDao {

	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public boolean valid(Admin admin) {
		RowMapper<Admin> map= new RowMapperAdmin();
		String query="select * from admin  where email=?";
		List<Admin> list= this.jdbctemplate.query(query,map,admin.getEmail());
		System.out.println(list.size());
		if(list.isEmpty()) return false;
		else {
			System.out.println(list.get(0));
			return true;
		}
		
		
	}
	
	
	
	
	
	
	
}
