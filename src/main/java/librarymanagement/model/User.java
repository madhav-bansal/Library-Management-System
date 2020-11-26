package librarymanagement.model;
public class User {

	private String name;
	private String email;
	private String mobileNumber;
	private String password;
	private String address;
	private String universityName;
	private String sex;

	public String getName() {
		return name;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", mobileNumber=" + mobileNumber
				+ ", address=" + address + ", universityName=" + universityName + ", sex=" + sex + "]";
	}
	
	
	
	
	
	
}
