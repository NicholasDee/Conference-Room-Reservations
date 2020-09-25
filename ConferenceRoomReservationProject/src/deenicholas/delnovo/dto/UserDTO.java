package deenicholas.delnovo.dto;

import java.io.Serializable;

public class UserDTO implements Serializable
{
	private String name,surname,title,dept,tel,email,psw;
	/** 
	 * Empty constructor
	 */
	public UserDTO()
	{}
	/**
	 * Main constructor taking in parameters
	 */
	public UserDTO(String name,String surname,String title,String dept,String tel,String email,String psw) {
		this.name=name;
		this.surname=surname;
		this.title=title;
		this.dept=dept;
		this.tel= tel;
		this.email= email;
		this.psw=psw;
		
	}
	/**
	 * Overloaded constructor
	 * @param email
	 * @param psw
	 */
	public UserDTO(String email, String psw)
	{
		this.email = email;
		this.psw = psw;
	}
	
	/**
	 * Getter method for the name;
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter method for the name;
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Getter method for the surname;
	 * @return surname
	 */

	public String getSurname() { 
		return surname;
	}
	/**
	 * setter method for surname;
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * Getter method for title;
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Setter method title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Getter method for the department;
	 * @return department
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * Setter method for the department;
	 * @param dept
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * Setter method for the telephone;
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * Getter method for the telephone;
	 * @return telephone
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * Setter method for the email;
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Getter method for the email;
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Getter method for the password
	 * @return password
	 */
	public String getPsw() {
		return psw;
	}
	/**
	 * Setter method for the password
	 * @param psw
	 */
	public void setPsw(String psw) {
		this.psw = psw;
	}
	/**
	 * Overriden toString method
	 */
	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", surname=" + surname + ", title=" + title + ", dept=" + dept + ", tel=" + tel
				+ ", email=" + email + ", psw=" + psw + "]";
	}
	

}
