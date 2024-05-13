package com.intermediary.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.intermediary.Commit;
import com.intermediary.CommitDetails;
import com.intermediary.Invitation;
import com.intermediary.Logger;
import com.intermediary.Project;
import com.intermediary.ProjectItem;
import com.intermediary.ProjectManager;
import com.intermediary.firebase.Firebase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
	private ListView<Commit> pendingCommits;
	@FXML
	private ListView<Invitation> invitations;
	@FXML
	private ListView<String> logsContainer;
	@FXML
	private Text selectedname;
	private ProjectItem selected;

	private void getProjects() throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> future = store.collection("project")
				.whereArrayContains("participants", authManager.getUser().getId()).get(); // gets all the projects
		// in which the current user is a participant of.

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
		manager.addSceneToStage("secondaryScene", "main");
	}

	@FXML
	private void signOut() throws Exception {
		Logger.reset();
		manager.removeScene("main");
		manager.addSceneToStage("signIn", "authStage");
		manager.getStage("main").close();
		manager.showStage("authStage");
	}

	@FXML
	private void addProject() throws Exception {
//		manager.showStage("");
		manager.addSceneToStage("addProject", "addProject");
		manager.showStage("addProject");
	}

	@FXML
	private void addClient() throws Exception {
		if (selected == null) {
			selectCreateProject();
			return;
		}
		manager.addSceneToStage("addUser", "addUser");
		manager.showStage("addUser");
	}

	@FXML
	private void commit() throws Exception {
		if (selected == null) {
			selectCreateProject();
			return;
		}
		manager.addSceneToStage("commit", "commit");
		manager.showStage("commit");
	}

	@FXML
	private void addValidator() throws Exception {
		if (selected == null) {
			selectCreateProject();
			return;
		}

		// ask a user a select a java file
		// it must have a validate method
		// compile this file and then dynamically load it
		Logger.addLog("validator loaded successfully");
		Logger.displayLogs();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Pick your validator class file");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("java source file", "*.java"));
		File selectedFile = fileChooser.showOpenDialog(manager.getStage("main"));

		if (selectedFile == null || (selectedFile != null && !selectedFile.isFile())) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("Pleas select a java file and makesure" + " it is named Validator and must"
					+ " contain a validate method that takes string data ");

			a.show();
			return;
		}

		saveValidatorFile(selectedFile);
		

	}

	public void saveValidatorFile(File selectedFile) {
		File dir = new File("validators/" + selected.getProject().getId());
		dir.mkdirs();
		File newFile = new File("validators/" + selected.getProject().getId() + "/" + "Validator.java");
		try (Scanner reader = new Scanner(selectedFile); PrintWriter pw = new PrintWriter(newFile);) { // try with
																										// resource
			while (reader.hasNextLine()) {
				pw.println(reader.nextLine());
				pw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selectCreateProject() {
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setContentText("You need to select a project or a create a new one");
		a.show();

	}

	private void printYourCommit() {

		incomingCommits.getItems().clear();
		pendingCommits.getItems().clear();
		
		try {
			for (CommitDetails detail : getCommitSnapshot()) {

				if (detail.getUserId().equals(authManager.getUser().getId())) {
					incomingCommits.getItems().add(new Commit(detail));
				} else {
					pendingCommits.getItems().add(new Commit(detail));
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<CommitDetails> getCommitSnapshot() throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> future = store.collection("project").whereEqualTo("id", selected.getProject().getId())
				.get(); // gets
		// all
		// the
		// projects
		// in which the current user is a participant of.
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		return documents.get(0).toObject(Project.class).getCommits();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Invitation.injectList(sideBarList);
		authManager.reloadUserinfo(invitations); // will check for new invitations every second

		try {
			getProjects();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sideBarList.setOnMouseClicked(e -> {
			var model = sideBarList.getSelectionModel();
			selected = model.getSelectedItem();
			if (selected != null) {
				selectedname.setText(selected.getName().getText());
				ProjectManager.setSelectedProject(selected.getProject());
				printYourCommit();
				ProjectManager.verifyCommits();
			}
		});

		Logger.setLoggerList(logsContainer);
		Logger.displayLogs();

	}

}
