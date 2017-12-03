package org.thinktanktutoringservice.people;
import org.thinktanktutoringservice.software.*;
import java.util.ArrayList;
import java.io.Serializable;
public abstract class Profile implements Serializable {
	protected String name;
	protected String email;
	protected String DOB;
	protected String username;
	private String password;
	public Profile(){
	this.name = "";
	this.email = "";
	this.DOB = "";
	this.password = "nopassword";
	this.username = "";
	
	
	}
	public Profile( String name , String email, String DOB , String username , String password){
	this.name = name;
	this.email = email;
	this.DOB = DOB;
	this.username = username ;
	this.password = password;
	}
	public String getName(){
	return this.name;
	}
	public void setName( String newname){
	this.name = newname;
	}
	public String getEmail(){
	return this.email;
	}
	public void setEmail( String newEmail){
	this.email = newEmail;
	}
	public String getDOB(){
	return this.DOB;
	}
	public void setDOB ( String newDOB){
	this.DOB = newDOB;
	
	}
	public String getUsername(){
	return this.username;
	}
	public boolean checkPassword( String password){
	if( this.password.equals(password)) return true;
	else return false;

	
	}
	public boolean changePassword( String oldpassword, String newpassword ){
	if(this.password.equals(oldpassword)){
		this.password = newpassword;
		return true;
		
	}
else return false;

}
}



