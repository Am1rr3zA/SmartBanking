package main.java.com.smartBanking.Exceptions;

public class UserNotFound extends Exception{
	
	public UserNotFound() {
		super();
	}
	
	public UserNotFound(String message) { 
		super(message); 
	}
}
