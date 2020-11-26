package librarymanagement.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import librarymanagement.model.Librarian;


@Repository
public class LibrarianDao {

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


	public int register(Librarian librarian) {
		String query= "INSERT INTO LIBRARIAN VALUES(?,?,?,?,?,?)";
		try {

			
			int r= jdbcTemplate.update(query,librarian.getName(),librarian.getEmail(),
					librarian.getMobileNumber(),encode(librarian.getPassword()),
					librarian.getAddress(),librarian.getSex());
			return r;

		}catch(Exception e) {
			System.out.println(e);
			return 0;
		}
	}


	public Librarian getLibrarian(String id) {
		RowMapper<Librarian> map= new RowMapperLibrarian();
		String query="select * from librarian where email=?";
		List<Librarian> list = this.jdbcTemplate.query(query, map,id);
		if(list.isEmpty()) return null;
		else return list.get(0);
	}






	//update use profile
	public boolean update(Librarian lib) {

		String query="update librarian set name=?,mobilenumber=?,password=?,address=?  where email=?";
		try {
			this.jdbcTemplate.update(query,lib.getName(),lib.getMobileNumber(),lib.getPassword(),lib.getAddress(),lib.getEmail());
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}

	}


	public boolean remove(String s) {

		String query="delete from librarian where email=?";

		try {
			this.jdbcTemplate.update(query,s);
		}catch(Exception e) {
			//do exception handling
			return false;
		}

		return true;
	}


	//searching profile related to query;
	public List<Librarian> search(String q){
		RowMapper<Librarian> map = new RowMapperLibrarian();

		String q2="%"+q+"%";
		if(q==null) q2="%%";

		String query= "select * from librarian where name like ? ";
		List<Librarian> qs= this.jdbcTemplate.query(query,map,q2);

		return qs;
	}



}
