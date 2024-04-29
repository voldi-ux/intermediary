package com.intermediary.auth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.intermediary.User;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

//class to manager all authenticatio related stuff
public class AuthenticationManager {
	private User user;
	private boolean isLogedIn = false;
	private static AuthenticationManager manager = null;



	private AuthenticationManager() {
	

	}

	@SuppressWarnings("unused")
	 public static AuthenticationManager getAuthenticationManager() {
		if (manager == null)
			manager = new AuthenticationManager();
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

	public void signIn(String email, String pass) {
		
	}
	
	public void handleSignIn(boolean isSignedIn, String uid) {
		System.out.println(isSignedIn + uid);
	}

	private String readFileAsString(String path) {
		// TODO Auto-generated method stub
		String str = "";
		try {
			Scanner sn = new Scanner(new File(path));

			while (sn.hasNextLine()) {
				str += sn.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str;
	}
}
