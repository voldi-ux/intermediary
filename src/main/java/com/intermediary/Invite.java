package com.intermediary;

import java.util.UUID;

public class Invite {
	private String projectId;
	private String sendId;
	private String receiverId;
	private String projectName;
    private String id;
    
	public Invite(String projectId, String sendId, String receiverId, String projectName) {
		super();
		this.projectId = projectId;
		this.sendId = sendId;
		this.receiverId = receiverId;
		this.projectName = projectName;
		this.id = UUID.randomUUID().toString();
		
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
