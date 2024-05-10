package com.intermediary.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.intermediary.Participant;
import com.intermediary.Project;
import com.intermediary.User;
import com.intermediary.auth.AuthenticationManager;
import com.intermediary.firebase.Firebase;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class ProjectController extends MainController {
	private static Firestore store = Firebase.getStore();

	@FXML
	private TextField name;
	@FXML
	private TextField repoName;
	@FXML
	private TextField link;

	private Alert alert = new Alert(AlertType.INFORMATION);
    
	public void addProject() throws Exception {
		String name = this.name.getText();
		String repoName = this.repoName.getText();
		String repoLink = this.link.getText();
		// gets the currently logged in user
		User user = AuthenticationManager.getAuthenticationManager().getUser();

		if (name.isEmpty() || repoName.isEmpty() || repoLink.isEmpty()) {
			alert.setContentText("Enter valid inputs");
			alert.show();
			return;
		}

		Participant participant = new Participant(user.getId());
		Project project = new Project(name, repoLink, repoName, user.getId());
		project.addParticipant(participant);

		ApiFuture<DocumentReference> ref = store.collection("project").add(project);

		if (ref.isCancelled()) {
			alert.setContentText("your project was not added, try again letter");
			alert.show();
		}

		manager.getStage("addProject").close(); // closes the window for adding project
	}

}
