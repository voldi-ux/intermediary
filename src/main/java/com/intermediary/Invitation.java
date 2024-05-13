package com.intermediary;

import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.WriteResult;
import com.intermediary.auth.AuthenticationManager;
import com.intermediary.firebase.Firebase;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Invitation extends HBox {
	private Invite invite;
	private static ListView<ProjectItem> items;
	private Firestore store = Firebase.getStore();
	private Button acceptBtn = new Button("Accep Invite");
	private Button decline = new Button("Decline Invite");

	public Invitation(Invite invite) {
		this.invite = invite;

		setSpacing(10);
		acceptBtn.setPrefHeight(25);
		decline.setPrefHeight(25);
		setPadding(new Insets(10));
		setAlignment(Pos.CENTER_LEFT);

		acceptBtn.setOnAction(e -> {
			try {
				accept();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		decline.setOnAction(e -> {
			try {
				decline();
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		this.getChildren().add(new Text("Join " + invite.getProjectName() + " "));
		this.getChildren().add(acceptBtn);
		this.getChildren().add(decline);
	}

	private void decline() throws InterruptedException, ExecutionException {
		String userId = AuthenticationManager.getAuthenticationManager().getUser().getId();
		CollectionReference users = store.collection("users");

		Query query = users.whereEqualTo("id", userId);

		for (DocumentSnapshot doc : query.get().get().getDocuments()) {
			var user = doc.toObject(User.class);

			var filteredInvites = user.getInvites().stream().filter(inv -> !inv.getId().equals(invite.getId()))
					.collect(Collectors.toList());
			ApiFuture<WriteResult> updateFuture = doc.getReference().update("invites", filteredInvites);

			if (updateFuture.isCancelled()) {
				// add a message to the log
				
				Logger.addAndDisplay("could not decline invitation, try again later");
			} 
		}

	}

	private void accept() throws Exception {
		String userId = AuthenticationManager.getAuthenticationManager().getUser().getId();
		CollectionReference users = store.collection("users");
		CollectionReference projects = store.collection("project");
		Query query = projects.whereEqualTo("id", invite.getProjectId());

		for (DocumentSnapshot doc : query.get().get().getDocuments()) {
			Project project = doc.toObject(Project.class);

			project.addParticipant(userId);

			ApiFuture<WriteResult> updateFuture = doc.getReference().update("participants", project.getParticipants());

			if (updateFuture.isCancelled()) {
				Logger.addAndDisplay("could not acception invitation, try again later");
			} else {
				Logger.addAndDisplay("You have accepted to join " + invite.getProjectName());
				decline(); // to remove the invitation
				NavigationManager.getManager().removeScene("main");
				NavigationManager.getManager().AddScene("main", new Scene(App.loadFXML("primary")));
				NavigationManager.getManager().addSceneToStage("main", "main");

			}
		}

	}

	public static void injectList(ListView<ProjectItem> itemsList) {
		items = itemsList;
	}
}
