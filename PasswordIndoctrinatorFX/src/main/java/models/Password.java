package models;

public final class Password {
	private transient String passwordToMatch;
	
	public Password() { }
	
	@Override
	public Password clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public void setPassword(String password) {
		if (null == password) {
			throw new IllegalArgumentException("A value is required to set the password.");
		}
		
		this.doSetPassword(password);
	}

	private void doSetPassword(String password) {
		String pw = this.sanitizedPassword(password);
		
		this.passwordToMatch = pw;
	}

	private String sanitizedPassword(String testPw) {
		if(null == testPw) {
			throw new IllegalArgumentException("A value is required for the password.");
		}
		
		return new String(testPw);
	}
	
	public boolean checkMatch(String testPw) {
		String test = this.sanitizedPassword(testPw);
		boolean result = this.matches(test);
		
		return result;
	}

	private boolean matches(String testPassword) {
		boolean pass = false;
		
		String testPw = this.sanitizedPassword(testPassword);
		
		if (this.passwordToMatch.equals(testPw)) {
			pass = true;
		}
		
		return pass;
	}
}
