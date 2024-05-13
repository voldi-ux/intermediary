package com.intermediary.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.intermediary.App;
import com.intermediary.CommitDetails;
import com.intermediary.Logger;
import com.intermediary.ProjectManager;
import com.intermediary.firebase.Firebase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class CommitController extends MainController implements Initializable {
	private static ArrayList<String> fileChanged = new ArrayList<>();
	private static Firestore store = Firebase.getStore();

	@FXML
	private ScrollPane container;
	@FXML
	private TextArea message;
	@FXML
	private ListView<String> list;

	@FXML
	public void commit() throws Exception {
		String msg = message.getText();

		if (msg.isEmpty()) {
			Alert a = new Alert(Alert.AlertType.INFORMATION);
			a.setContentText("message must not be empty");
			a.show();
			return;
		}

		String details = "";
		for (String str : fileChanged) {
			details += str;
		}

		details += " " + msg;

		String projectId = ProjectManager.getSelectedProject().getId();
		CommitDetails commit = new CommitDetails(projectId, authManager.getUser().getId(), details);

		// save this to the database;

		Query projQuery = store.collection("project").whereEqualTo("id", projectId);
		ApiFuture<QuerySnapshot> querySnapshot = projQuery.get();

		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
			document.getReference().update("commits", FieldValue.arrayUnion(commit));
			Logger.addLog("commit with id : " + commit.getId() + " was added");
			manager.removeScene("main");
			manager.AddScene("main", new Scene(App.loadFXML("primary")));
			manager.addSceneToStage("main", "main");
			manager.getStage("commit").close();
			break;
		}

	}

	@FXML
	public void cancel() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// simulating git stages
		fileChanged.add("modified: src/main/java/com/intermediary/App.java");
		fileChanged.add("modified: src/main/java/com/intermediary/Commit.java");
		fileChanged.add("modified: src/main/java/com/intermediary/CommitDetails.java");
		fileChanged.add("modified: src/main/java/com/intermediary/NavigationManager.java");
		fileChanged.add("modified: src/main/java/com/intermediary/Project.java");
		fileChanged.add("modified: src/main/java/com/intermediary/blockchain/blockchainExtended.java");
		fileChanged.add("modified: src/main/java/com/intermediary/controllers/AuthController.java");
		fileChanged.add("modified: src/main/java/com/intermediary/controllers/MainController.java");
		fileChanged.add("modified: src/main/java/com/intermediary/controllers/PrimaryController.java");
		fileChanged.add("modified: src/main/java/com/intermediary/controllers/ProjectController.java");
		fileChanged.add("modified: src/main/resources/com/intermediary/commit.fxml");
		fileChanged.add("modified: target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst");
		fileChanged.add("modified: src/test/java/com/intermediary/TestApp.java");
		fileChanged.add("modified: src/main/java/com/intermediary/Utils.java");
		fileChanged.add("modified: src/main/resources/com/intermediary/config.properties");
		fileChanged.add("modified: src/main/java/com/intermediary/services/EmailService.java");
		fileChanged.add("modified: src/main/java/com/intermediary/controllers/AdminController.java");
		fileChanged.add("modified: src/main/java/com/intermediary/models/User.java");
		fileChanged.add("modified: src/main/resources/com/intermediary/README.md");
		fileChanged.add("modified: src/main/java/com/intermediary/security/SecurityConfig.java");
		fileChanged.add("modified: src/main/java/com/intermediary/utils/StringUtils.java");
		fileChanged.add("modified: src/main/java/com/intermediary/services/NotificationService.java");
		fileChanged.add("modified: src/test/resources/com/intermediary/testData.json");
		fileChanged.add("modified: src/main/java/com/intermediary/controllers/ApiController.java");
		fileChanged.add("modified: src/main/java/com/intermediary/utils/FileUtils.java");
		fileChanged.add("modified: src/main/java/com/intermediary/controllers/ApiController.java");
		fileChanged.add("modified: src/main/resources/com/intermediary/logging.properties");
		fileChanged.add("modified: src/main/java/com/intermediary/models/ProjectModel.java");
		fileChanged.add("modified: src/main/java/com/intermediary/services/DatabaseService.java");

		Collections.shuffle(fileChanged);
		list.getItems().addAll(fileChanged);

	}

}
