package com.intermediary.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.intermediary.Invite;
import com.intermediary.Project;
import com.intermediary.ProjectManager;
import com.intermediary.firebase.Firebase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class InviteController extends MainController implements Initializable {

	private ArrayList<String> emails = new ArrayList<>();
	private Firestore store  = Firebase.getStore();
	

	@FXML
	private TextField emailInput;
	@FXML
	private ListView<String> listInvites;

	@FXML
	public void addInvite() {

		String email = emailInput.getText();

		if (email.isEmpty()) {
			Alert a = new Alert(Alert.AlertType.INFORMATION);
			a.setContentText("Enter a valid email");
			a.show();
			return;
		}

		emails.add(email);
		listInvites.getItems().add(email);
	}

	@FXML
	public void invite() throws Exception {
		Project selectedProject  = ProjectManager.getSelectedProject();
		
		if (emails.isEmpty()) {
			Alert a = new Alert(Alert.AlertType.INFORMATION);
			a.setContentText("You need to specify at least one email");
			a.show();
			return;
		}
		
       for(String email: emails) {
    	   CollectionReference users  = store.collection("users");
    	   

   		Query emailPassQuery = users.whereEqualTo("email", email);// find a user

   		try {
   			List<QueryDocumentSnapshot> documents = emailPassQuery.get().get().getDocuments();
   			// we should not be able to have more than one with uers with the same email and
   			// password
   			
   			System.out.println(documents.size());
   			for(DocumentSnapshot doc: documents) {
   				   Invite inv = new Invite(selectedProject.getId(), authManager.getUser().getId(), selectedProject.getName());
   				   doc.getReference().update("invites", FieldValue.arrayUnion(inv));
   				break;
   			}
   			
   			// if code reaches here then a user does not exist with the current email
   			if(documents.size() == 0) {
   				Alert a = new Alert(Alert.AlertType.INFORMATION);
   				a.setContentText("User with email " + email + " does not exists");
   				a.show();
   			}

   		} catch (InterruptedException e) {
   			e.printStackTrace();
   		} catch (ExecutionException e) {
   			e.printStackTrace();
   		}
    	   
       }
       
       manager.getStage("addUser").close();
       emails.clear();
       listInvites.getItems().clear();
       emailInput.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
           
	}

}
