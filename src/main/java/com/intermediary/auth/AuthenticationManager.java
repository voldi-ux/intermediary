package com.intermediary.auth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.intermediary.User;
import com.intermediary.firebase.Firebase;

import at.favre.lib.crypto.bcrypt.BCrypt;

//class to manager all authenticatio related stuff
public class AuthenticationManager {
	private User user;
	private boolean isLogedIn = false;
	private int COST = 10;
	private static AuthenticationManager manager = null;
	private static Firestore store = Firebase.getStore();

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

	public boolean signIn(String email, String password) {

		CollectionReference users = store.collection("users");

		Query emailPassQuery = users.whereEqualTo("email", email);// find a user

		try {
			List<QueryDocumentSnapshot> documents = emailPassQuery.get().get().getDocuments();
			// we should not be able to have more than one with uers with the same email and
			// password
			if (documents.size() > 0) {
				User firstUser = documents.get(0).toObject(User.class);
				if (BCrypt.verifyer().verify(password.getBytes(), firstUser.getPassword().getBytes()).verified) {
					this.user = firstUser;
					isLogedIn = true;
					return true;
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean signUp(String email, String password) {

		String passHashed = BCrypt.withDefaults().hashToString(COST, password.toCharArray());
		CollectionReference users = store.collection("users");

		Query emailQuery = users.whereEqualTo("email", email);
		try {
			// a user with this email already exist, try another one
			if (emailQuery.get().get().getDocuments().size() > 0) {
				return false;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		User user = new User(email, passHashed);

		ApiFuture<DocumentReference> results = users.add(user);

		if (!results.isCancelled()) {
			manager.setUser(user);
			return true;
		}

		return false;
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
