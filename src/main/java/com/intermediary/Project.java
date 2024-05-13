package com.intermediary;

import java.util.ArrayList;
import java.util.UUID;

import com.intermediary.blockchain.blockchainExtended;

import acsse.csc03a3.Blockchain;

public class Project {
	private String id;
	private String name;
	private String repoName;
	private String link;
	private String ownerId; // the person that created the project
	private ArrayList<String> participants = new ArrayList<>();
	private ArrayList<CommitDetails> commits = new ArrayList<CommitDetails>(); //contains unverified commits
	
	public Project() {
		
	}

	public Project(String name, String repoName, String link, String ownerId) {
		super();
		this.name = name;
		this.repoName = repoName;
		this.link = link;
		this.ownerId = ownerId;
		this.id = UUID.randomUUID().toString();
				
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<CommitDetails> getCommits() {
		return commits;
	}

	public void setCommits(ArrayList<CommitDetails> commits) {
		this.commits = commits;
	}

	public void setParticipants(ArrayList<String> participants) {
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

	public ArrayList<String> getParticipants() {
		return participants;
	}

	public void addParticipant(String p) {
		participants.add(p);
	}

}
