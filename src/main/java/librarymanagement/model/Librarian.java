package librarymanagement.model;

public class Librarian {

	private String name;
	private String email;
	private String mobileNumber;
	private String password;
	private String address;
	private String sex;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Librarian [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + ", password="
				+ password + ", address=" + address + ", sex=" + sex + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
