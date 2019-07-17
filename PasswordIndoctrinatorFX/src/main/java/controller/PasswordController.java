package controller;

import models.Password;

public final class PasswordController {
	
	private transient final Password pw;
	private PasswordController(String pw) {
		this.sanitizePassword(pw);
		
		this.pw = new Password();
		this.pw.setPassword(pw);
	}

	public static PasswordController setPassword(String pw) {
		checkValidInput(pw);
		return new PasswordController(pw);
	}
	
	public boolean checkPasswordMatch(String testPw) {
		String sanitizedTestPassword = this.sanitizePassword(testPw);
		
		return Boolean.valueOf(this.pw.checkMatch(sanitizedTestPassword));
	}

	private String sanitizePassword(String testPw) {
		checkValidInput(testPw);
		
		return String.valueOf(testPw);
	}

	private static void checkValidInput(String pw) {
		if(null == pw) {
			throw new IllegalArgumentException("A value is required for the password.");
		}
	}

	@Override
	public PasswordController clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}