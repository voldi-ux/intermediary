package com.intermediary.auth;

import com.intermediary.User;

//class to manager all authenticatio related stuff
public class AuthenticationManager {
	private User user;
	private boolean isLogedIn = false;
	private static AuthenticationManager manager = null;

	private AuthenticationManager() {
	}

	private static AuthenticationManager getAuthenticationManager() {
		if (manager == null)
			new AuthenticationManager();
		return manager;
	}

	public boolean isLoggedIn() {
		return isLogedIn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogedIn() {
		return isLogedIn;
	}

	public void setLogedIn(boolean isLogedIn) {
		this.isLogedIn = isLogedIn;
	}

}
