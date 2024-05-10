package com.intermediary;

import java.util.UUID;

public class CommitDetails {
	private String id;
	private String projectId;
	private String userId;
	private String verifierId;
	private String details;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public CommitDetails(String projectId, String userId, String details) {
		super();
		this.id = UUID.randomUUID().toString();
		this.projectId = projectId;
		this.userId = userId;
		this.details = details;
	}

	public String getVerifierId() {
		return verifierId;
	}

	public void setVerifierId(String verifierId) {
		this.verifierId = verifierId;
	}

}
