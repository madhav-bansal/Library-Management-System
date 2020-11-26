package librarymanagement.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import librarymanagement.model.Book;
import librarymanagement.model.User;

@Repository
public class StudentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String bytesToHex(byte bytes[]) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}


	public String encode(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] result= md.digest(password.getBytes(StandardCharsets.UTF_8));
			String hex= bytesToHex(result);
			
			return hex;

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}


	//insert into student table
	public int registerStudent(User user) {
		String query= "INSERT INTO STUDENT VALUES(?,?,?,?,?,?,?)";
		try {
		int r= jdbcTemplate.update(query,user.getName(),user.getEmail(),
				user.getMobileNumber(),encode(user.getPassword()),
				user.getAddress(),user.getUniversityName(),user.getSex());
		return r;
		}catch(Exception e) {
			return 0;
		}
		
		
	}
	
	
	//selecting particular and validating for password
	public User valid(User user) {
		RowMapper<User> rmi=new RowMapperImpl();
		String query= "select * from student where email=? and password=?";
		List<User> user1;
	     user1= this.jdbcTemplate.query(query, rmi,user.getEmail(),encode(user.getPassword()));
	     if(user1.isEmpty()) return null;
	     return user1.get(0);
		
	}
	
	public User getUser(String id) {
		
			RowMapper<User> map = new RowMapperImpl();
			String query="select * from student where email=?";
			List<User> list= this.jdbcTemplate.query(query,map,id);
			if(list.isEmpty()) return null;
			else return list.get(0);
		
	}
	
	
	// remove operation
	public boolean remove(String email) {
	
		String query="delete from student where email=?";
		System.out.println(query);
		try {
			System.out.println(this.jdbcTemplate.update(query,email));
			
		}catch(Exception e) {
			System.out.println(e);
			//do exception handling
			return false;
		}
			
		return true;
	}
	
	//update use profile
	public boolean update(User user) {
		

		String query="update student set name=?,mobilenumber=?,password=?,address=?,university=?  where email=?";
		try {
			this.jdbcTemplate.update(query,user.getName(),user.getMobileNumber(),
					encode(user.getPassword()),user.getAddress(),user.getUniversityName(),user.getEmail());
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
		
	}
	
	
	public List<User> search(String q){
		RowMapper<User> map = new RowMapperImpl();
		
		String q2="%"+q+"%";
		if(q==null) q2="%%";
	
		String query= "select * from student where name like ? ";
		List<User> qs= this.jdbcTemplate.query(query,map,q2);
	
		return qs;
	}
	
	
	
	
	
	
	
	
	
	
}
