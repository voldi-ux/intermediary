package com.intermediary;

import java.util.ArrayList;
import java.util.UUID;

public class User {
	private String email;
	private String password;
	private String id;
	private ArrayList<Invite> invites = new ArrayList<Invite>();

	public User() {
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.id = UUID.randomUUID().toString();
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Invite> getInvites() {
		return invites;
	}

	public void setInvites(ArrayList<Invite> invites) {
		this.invites = invites;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", id=" + id + "]";
	}

}
