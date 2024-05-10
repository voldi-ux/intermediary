package com.intermediary.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.intermediary.Commit;
import com.intermediary.Invitation;
import com.intermediary.Project;
import com.intermediary.ProjectItem;
import com.intermediary.firebase.Firebase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PrimaryController extends MainController implements Initializable {
	private Firestore store = Firebase.getStore();
	@FXML
	private Button signoutButton;
	@FXML
	private Button inviteBtn;
	@FXML
	private Button addValidatorBtn;
	@FXML
	private Button commitChangesBtn;
	@FXML
	private ListView<ProjectItem> sideBarList;
	@FXML
	private ListView<Commit> incomingCommits;
	@FXML
	private ListView<Text> pendingCommits;
	@FXML
	private ListView<Invitation> invitations;
	@FXML
	private ListView<Text> logs;
	@FXML
	private Text selectedname;
	private ProjectItem selected;

	private void getProjects() throws InterruptedException, ExecutionException {

		ApiFuture<QuerySnapshot> future = store.collection("project")
				.whereEqualTo("ownerId", authManager.getUser().getId()).get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		sideBarList.getItems().clear(); // removes all the items
		for (DocumentSnapshot document : documents) {
			Project project = document.toObject(Project.class);
			ProjectItem projectItem = new ProjectItem(project);
			sideBarList.getItems().add(projectItem);
		}
	}

	@FXML
	private void switchToSecondary() throws Exception {
//        App.setRoot("secondary");  	
		manager.addSceneToStage("secondaryScene", "main");
	}

	@FXML
	private void signOut() throws Exception {
		manager.addSceneToStage("signIn", "authStage");
		manager.getStage("main").close();
		manager.showStage("authStage");
	}

	@FXML
	private void addProject() throws Exception {
//		manager.showStage("");
		manager.addSceneToStage("addProject", "addProject");
		manager.showStage("addProject");
		try {
			getProjects();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void addClient() throws Exception {
//		manager.showStage("");
		manager.addSceneToStage("addUser", "addUser");
		manager.showStage("addUser");
	}

	@FXML
	private void commit() throws Exception {
//		manager.showStage("");
		manager.addSceneToStage("commit", "commit");
		manager.showStage("commit");
	}

	@FXML
	private void addValidator() {
		// ask a user a select a java file
		// it must have a validate method
		// compile this file and then dynamically load it
		System.out.println("validator loaded successfully");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sideBarList.setOnMouseClicked(e -> {
			var model = sideBarList.getSelectionModel();
			selected = model.getSelectedItem();
			selectedname.setText(selected.getName().getText());
		});

	}

}
