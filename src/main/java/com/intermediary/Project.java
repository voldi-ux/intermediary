package com.intermediary;

import java.util.ArrayList;

import acsse.csc03a3.Blockchain;

public class Project {
	private String name;
	private String repoName;
	private String link;
	private String ownerId;
	private ArrayList<Participant> participants = new ArrayList<>();
	private Blockchain<String> blockchain = new Blockchain<String>();

	public Project() {
	}

	public Project(String name, String repoName, String link, String ownerId, ArrayList<Participant> participants) {
		super();
		this.name = name;
		this.repoName = repoName;
		this.link = link;
		this.ownerId = ownerId;
		this.participants = participants;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public ArrayList<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Participant> participants) {
		this.participants = participants;
	}

	public Blockchain<String> getBlockchain() {
		return blockchain;
	}

	public void setBlockchain(Blockchain<String> blockchain) {
		this.blockchain = blockchain;
	}

}
