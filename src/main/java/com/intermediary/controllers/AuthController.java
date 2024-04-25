package com.intermediary.controllers;



import com.google.firebase.FirebaseApp;
import com.intermediary.auth.AuthenticationManager;
import com.intermediary.firebase.Firebase;

import javafx.fxml.FXML;

public class AuthController extends MainController {
     private FirebaseApp firebaseApp = Firebase.getFirebase();
      
     // this method may be called when signing in or signing up
	@FXML
	private void signIn() throws Exception {
//		manager.addSceneToStage("main", "main");
//		manager.getStage("authStage").close();
//		manager.showStage("main");
		
		authManager.signIn("voldimuyumba57@gmail.com", "password");
	}

	@FXML
	private void showSignInScene() throws Exception {
		manager.addSceneToStage("signIn", "authStage");
	}

	@FXML
	private void showSignUpScene() throws Exception {
		manager.addSceneToStage("signUp", "authStage");
	}
}
