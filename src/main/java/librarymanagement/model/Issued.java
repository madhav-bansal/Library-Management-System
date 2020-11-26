package librarymanagement.model;

import java.sql.Date;

public class Issued {
	
	private int id;
	private String student_email;
	private int book_id;
	private Date issued_date;
	private Date due_date;
	private Date return_date;
	private int status;
	private int fine;
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudent_email() {
		return student_email;
	}
	public void setStudent_email(String student_email) {
		this.student_email = student_email;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public Date getIssued_date() {
		return issued_date;
	}
	public void setIssued_date(Date issued_date) {
		this.issued_date = issued_date;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	
	
	
}
