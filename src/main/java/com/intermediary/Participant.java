package com.intermediary;

public class Participant {
	private static final int defaultStake = 10;
	private String user_id;
	private int stake;

	public Participant() {
	}
	
	
	public Participant(String user_id) {
		this.user_id = user_id;
		this.stake = defaultStake;
	}

	public Participant(String user_id, int stake) {
		super();
		this.user_id = user_id;
		this.stake = stake;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getStake() {
		return stake;
	}

	public void setStake(int stake) {
		this.stake = stake;
	}

}
